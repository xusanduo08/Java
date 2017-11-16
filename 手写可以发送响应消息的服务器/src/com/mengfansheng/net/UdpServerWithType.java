package com.mengfansheng.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServerWithType {

	public static void main(String[] args) throws IOException {
		//创建服务端+指定端口
		DatagramSocket server = new DatagramSocket(8888);
		//准备容器
		byte[] container = new byte[1024];
		//封装成包
		DatagramPacket packet = new DatagramPacket(container, 0, container.length);
		//接收数据
		server.receive(packet);
		//分析数据
		byte[] data = packet.getData();
		double num = convert(data);
		System.out.println(num);
		//释放资源
		server.close();

	}
	
	public static double convert(byte[] data) throws IOException{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		double num = dis.readDouble();
		dis.close();
		return num;
	}

}
