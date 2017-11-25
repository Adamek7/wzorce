

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.awt.Desktop;
import java.io.*;

public class Server {

    static final String NATIVE_LIBRARY_SEARCH_PATH = "/usr/bin/vlc"; //MUSZISZ ZANSTALOWAĆ VLC I DODAĆ TU SCIEZKE DO NIEGO

static Tutorial tutorial;


    public static void main(String[] args) throws IOException, InterruptedException{

        int port = 8030;

        while(true) {
            try {
                    /*utworzenie na podanym porcir socketa serwera */

                ServerSocket serverSocket = new ServerSocket(port);
                System.out.print("Wating for connection..." + serverSocket.getLocalPort());
                    /*oczekiwanie na polaczenie i zaakceptowanie */
                Socket clientSocket = serverSocket.accept();
                System.out.println("Polaczono klienta");


					/*utworzenie strumienia wejsciowego */
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                ArrayList<String> odpowiedzSerwera = new ArrayList<String>();
                String ln;
                while (!(ln = in.readLine()).equals("")) {
                    System.out.println(ln);
                    odpowiedzSerwera.add(ln);
                }

                String podanyFilm = odpowiedzSerwera.get(0).split(" ")[1];
                podanyFilm = podanyFilm.substring(1);

                if (podanyFilm.length() >5 && podanyFilm.substring(0, 5).equals("https")) {
                    final String link = podanyFilm;
                    NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);
                    new NativeDiscovery().discover();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            tutorial = new Tutorial(link);                    }
                    });
                } else if (podanyFilm.equals("pause")) {
                    tutorial.pasue();
                }else if (podanyFilm.equals("play")) {
                    tutorial.play();
                }else{
                    tutorial.skip(podanyFilm);
                }

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                pw.print("HTTP/1.1 200 \r\n");
                pw.print("Content-Type: text/html\r\n");
                pw.print("Connection: close\r\n");
                pw.print("\r\n");
                pw.flush();

                serverSocket.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}
