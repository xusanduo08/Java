package com.mengfansheng.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
    private DataInputStream dis = null; // 管道输入流
    private boolean isRunning = true; // 线程运行标识
    public Receive(Socket client){
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 接收数据
    public void receive(){
        String str = null;
        try {
            str = dis.readUTF();
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dis);
        }

    }

    @Override
    public void run() {
        while(isRunning){
            receive();
        }
    }
}
