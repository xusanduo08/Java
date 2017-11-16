package com.mengfansheng.net;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpClientWithType {

	public static void main(String[] args) throws IOException {
		//创建服务端+端口
		DatagramSocket client = new DatagramSocket(6666);
		//准备数据
		double num = 89.12;
		byte[] data = convert(num);
		//打包发送地址及端口
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 8888);
		//发送数据
		client.send(packet);
		//释放
		client.close();
		
		//read(write());

	}
	
	public static byte[] convert(double num) throws IOException{
		byte[] data = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeDouble(num);
		dos.flush();
		data = bos.toByteArray();
		dos.close();
		return data;
	}
	
	public static byte[] write() throws IOException{
		byte[] dest;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String msg = "字节数组";
		byte[] info = msg.getBytes();
		bos.write(info, 0, info.length);
		dest = bos.toByteArray();
		bos.close();
		
		return dest;
	}
	
	public static void read(byte[] data) throws IOException{
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
		byte[] flush = new byte[1024];
		int len = 0;
		while(-1 != (len = is.read(flush, 0, flush.length))){
			System.out.println(new String(flush, 0, flush.length));
		}
		is.close();
		
	}

}
