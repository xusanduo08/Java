package com.mengfansheng.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * UDP服务端
 * */
public class UdpServer {

	public static void main(String[] args) throws IOException {
		//创建服务端+指定端口
		DatagramSocket server = new DatagramSocket(8888);
		//准备容器
		byte[] container = new byte[1024];
		//封装成包
		DatagramPacket packet = new DatagramPacket(container, container.length);
		//接收数据
		server.receive(packet);
		//分析数据
		byte[] data = packet.getData();
		int len = packet.getLength();
		System.out.println(new String(data, 0 , len));
		//释放资源
		server.close();
	}

}
