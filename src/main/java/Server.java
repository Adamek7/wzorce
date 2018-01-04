
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server  implements Runnable {
    private static Forwarder_Interface controller;
    private int port;

    public static  void setController(Forwarder c){
        controller=c;
    }

    public void setPort(int p){
        port=p;
    }


    public static void sendGetRequest(String host, int port, String url) throws Exception{
        url = "|"+url;
        URL objURL = new URL("http", host, port, url);
        HttpURLConnection connection = (HttpURLConnection) objURL.openConnection();
        connection.setRequestMethod("GET");
        connection.getResponseCode();
    }

    public void run() {
        while(true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.print("Wating for connection..." + serverSocket.getLocalPort());
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");


                //utworzenie strumienia wejsciowego
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ArrayList<String> request = new ArrayList<String>();
                String ln;
                //odebranie odpowiedzi
                while (!(ln = br.readLine()).equals("")) {
                    request.add(ln);
                    //  System.out.println(ln);
                }
                controller.handleRequest(request.get(0));
                //wyciagamy konkretne zadanie np: link, play, pause
                // checkRequest((request.get(0).split(" ")[1]).substring(1));

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
    }

    public static void startServer(int port){

    }


}

