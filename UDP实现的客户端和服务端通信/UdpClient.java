package com.mengfansheng.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpClient {

	public static void main(String[] args) throws IOException {
		//创建服务端+端口
		DatagramSocket client = new DatagramSocket(6666);
		//准备数据
		String msg = "UDP编程";
		byte[] data = msg.getBytes();
		//打包发送地址及端口
		DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost",8888));
		//发送数据
		client.send(packet);
		//释放
		client.close();
	}

}
