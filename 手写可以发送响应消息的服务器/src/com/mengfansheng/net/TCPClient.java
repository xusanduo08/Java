package com.mengfansheng.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 创建客户端  指定服务器和端口，创建的过程就是连接服务器的过程
 * new Socket(String host, int port)
 * */
public class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建客户端
		Socket client = new Socket("localhost", 5555);
		//发送数据+接收数据
		/*使用BufferedReader接收数据
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String echo = br.readLine();//注意，发送的时候要有newLine()才能使用该方法
		*/
		//使用DataInputStream接收数据
		DataInputStream dis= new DataInputStream(client.getInputStream());
		String echo = dis.readUTF();
		System.out.println(echo);
	}

}
