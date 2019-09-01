package com.mengfansheng.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/*
    TCP 客户端
 */
public class TCPClient {

    public static void main(String[] args){
        client();
    }

    public static void client(){
        try {
            Socket client = new Socket("localhost", 8888);
            Send send = new Send(client);
            Thread sendThread = new Thread(send);
            sendThread .start(); // 启动发送线程
            Receive receive = new Receive(client);
            Thread receiveThread = new Thread(receive);
            receiveThread.start(); // 启动发送线程
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
