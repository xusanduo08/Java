package com.mengfansheng.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
    TCP服务端
 */
public class TCPServer {

    public static void main(String[] args){
        server();
    }

    public static void server(){
        try {
            ServerSocket server = new ServerSocket(8888);
            Socket socket = server.accept();

           while(true){
               DataInputStream dis = new DataInputStream(socket.getInputStream());
               DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
               dos.writeUTF("server received" + dis.readUTF());
               dos.flush();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
