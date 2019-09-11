package com.mengfansheng.Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    private List<Channel> channelList = new ArrayList<>(); // 初始化容器
    public static void main(String[] args){
        TCPServer Server = new TCPServer();
        Server.server();
    }

    public void server(){
        try {
            ServerSocket server = new ServerSocket(8888);
            while(true){
                Socket socket = server.accept(); // 等待连接
                Channel channel = new Channel(socket);
                channelList.add(channel);
                new Thread(channel).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Channel implements Runnable{ // 保存每次连接通道，通过通道可以向指定的客户端发送消息
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private boolean isRunning = true;

        public Channel(Socket server){
            try{
                dis = new DataInputStream(server.getInputStream());
                dos = new DataOutputStream(server.getOutputStream());
            } catch(IOException e){
                CloseUtils.close(dis, dos);
                isRunning = false;
            }
        }

        public String receive(){
            try {
                String msg = dis.readUTF();
                return msg;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        public void send(String msg){
            if(msg != null && !msg.equals("")){
                try {
                    dos.writeUTF(msg);
                    dos.flush();
                } catch (IOException e) {
                    CloseUtils.close(dis, dos);
                    isRunning = false;
                }
            }
        }

        public void transfer(String msg){
            for(Channel channel: channelList){ // 遍历容器，向其他容器转发消息
                if(channel == this){
                    continue;
                }
                channel.send(msg);
            }
        }

        public void run(){
            while(isRunning){
//                send(receive());
                transfer(receive());
            }
        }
    }
}
