package com.mengfansheng.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
    TCP服务端
 */
public class TCPServer {
    private List<Channel> list = new ArrayList<Channel>(); // 管理链接

    public static void main(String[] args){
        new TCPServer().server();
    }

    public void server(){
        try {
            ServerSocket server = new ServerSocket(8888);

           while(true){
               Socket socket = server.accept();
               Channel channel = new Channel(socket);
               list.add(channel);// 统一存储每条链接，便于后续的转发
               new Thread(channel).start();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class Channel implements Runnable{ // 内部类，抽象客户端和服务器端的连接，内部类便于获取父类的一些内部数据
        DataOutputStream dos = null;
        DataInputStream dis = null;
        Boolean isRunning = true;

        public Channel(Socket server){ // 初始化一次连接
            try {
                dis = new DataInputStream(server.getInputStream());
                dos = new DataOutputStream(server.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
                CloseUtil.closeAll(dos, dis);
            }
        }

        public void send(String msg){ // 发送数据
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos, dis);
            }
        }

        public String receive(){
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return msg;
        }

        public void sendOthers(){ // 发送到其他链接
            String msg = receive();
            for(Channel c: list){
                if(c == this){
                    continue;
                }
                c.send(msg);
            }
        }

        @Override
        public void run() {
            while(isRunning){
                sendOthers();
            }
        }
    }
}
