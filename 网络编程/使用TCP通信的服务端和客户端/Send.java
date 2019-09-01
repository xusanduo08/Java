package com.mengfansheng.net;

import java.io.*;
import java.net.Socket;

public class Send implements Runnable {
    private DataOutputStream dos = null; // 管道输出流
    private BufferedReader reader = null; // 控制台输入流
    private boolean isRunning = true; // 线程运行标识

    // 初始化
    public Send(){
        // 从控制台输入
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client){
        this(); // 调用无参构造
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
        }
    }

    // 从控制台接收数据
    public String getMessage(){
        try {
            String str = reader.readLine();
            return str;
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos, reader);
        }
        return "";
    }

    // 发送数据
    public void send(){
        String str = getMessage();
        try{
            if(str != null && !str.equals("")){
                dos.writeUTF(str);
                dos.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
            CloseUtil.closeAll(reader, dos);
            isRunning = false;
        }
    }

    @Override
    public void run() {
        while(isRunning){
            send();
        }
    }
}
