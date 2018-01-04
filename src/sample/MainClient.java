package sample;

import javafx.application.Application;

public class MainClient {
   public static void main(String[] args){
       //startujemy server odbiorczy
       Server serv = new Server();
       serv.setPort(7002);
       Thread thread = new Thread(serv);
       thread.start();
       Application.launch(ApplicationView.class, args);
       //System.out.println("aplikacja zakonczyla dzialanie, serwer nadal stoi!!!");
    }
}