package com.Season;

import java.io.*;
import java.net.*;
import java.util.*;

public class chatserver{
    
    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket sock;
        PrintWriter writer;
        public ClientHandler(Socket clientSOcket){
            try{
                sock = clientSOcket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                writer = new PrintWriter(clientSOcket.getOutputStream());
            } catch (Exception ex){ ex.printStackTrace(); }
        }
        
        public ClientHandler(){}
        
        public void run(){
            String message;
            try{
                while ((message = reader.readLine()) != null){
                    System.out.println("read" + message);
                    
                    writer.println(message);
                    writer.flush();
                    System.out.println("send");
                }
            } catch(Exception ex){ ex.printStackTrace(); }
        }
    }
    
    public static void main(String[] args){
        chatserver server = new chatserver();
        server.go();
    }
    
    public void go(){
        try{
            ServerSocket serverSock = new ServerSocket(5000);
            
            while(true){
                Socket clientSocket = serverSock.accept();

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        } catch (Exception ex){ ex.printStackTrace(); }
    }
    
}
