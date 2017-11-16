package com.mengfansheng.net;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 创建服务器，指定端口
 * 不同协议之间端口号可以重复
 * */
public class TCPServer {

	public static void main(String[] args) throws IOException {
		//创建服务器
		ServerSocket server = new ServerSocket(5555);
		//接收客户端连接，阻塞式
		Socket socket = server.accept();
		//接收数据+发送数据
		
		String msg = "欢迎使用";
		/*使用BufferedWriter发送数据
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bw.write(msg);
		bw.newLine();//注意这地方，加了这句话，接收的时候才能使用readLine()方法
		bw.flush();
		*/
		//使用DataOutputStream发送数据
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF(msg);
		System.out.println("一个客户端建立连接");
	}

}
