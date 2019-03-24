package com.company;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main{

    public String s, s2;
    public String toSend, toSend2;
    public PrintWriter writer, writer2;
    public Socket socket, socket2;
    public BufferedReader reader, reader2;
    public InputStreamReader stream, stream2;
    public ServerSocket serverSocket, serverSocket2;


    public static void main(String[] args) {
        new Main().startServer();
    }


    public void startServer() {
        try {

                serverSocket2 = new ServerSocket(6001);
                socket2 = serverSocket2.accept();
                stream2 = new InputStreamReader(socket2.getInputStream());
                reader2 = new BufferedReader(stream2);
                writer2 = new PrintWriter(socket2.getOutputStream(), true);
                Runnable run = new MyRun();
                Thread thread = new Thread(run);
                thread.start();

            serverSocket = new ServerSocket(6000);
            socket = serverSocket.accept();
            stream = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(stream);
            writer = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                if (((s = reader.readLine()) != null)) {
                    toSend = s;
                    try {

                        while (toSend != null) {
                            System.out.println(toSend);
                            writer.println("You: " + toSend);
                            writer2.println("Client 1: " + toSend);
                            System.out.println("инфа ушла");
                            toSend = null;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("2 поток запущен");
            try {

                while(true) {
                    if (((s2 = reader2.readLine()) != null)) {
                        toSend2 = s2;
                        try {

                            while (toSend2 != null) {
                                System.out.println(toSend2);
                                writer2.println("You: " +toSend2);
                                writer.println("Client 2: " + toSend2);
                                System.out.println("инфа 2 ушла");
                                toSend2 = null;
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


}
