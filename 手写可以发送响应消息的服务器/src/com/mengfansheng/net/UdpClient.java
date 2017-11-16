package com.mengfansheng.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UdpClient {

	public static void main(String[] args) throws IOException {
		//创建服务端+端口
		DatagramSocket client = new DatagramSocket(8888);
		//准备数据
		String msg = "文件输入流一直";
		byte[] data = msg.getBytes();
		
		//打包发送地址及端口
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 6666);
		//发送数据
		client.send(packet);
		//释放
		client.close();
		
		msg = "操作与文件输入流一直";
		byte[] src = msg.getBytes();
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(src));
		byte[] flush = new byte[6];
		int len = 0;
		while(-1 != (len = is.read(flush))){
			//System.out.println(new String(flush,0,flush.length));
		}
		is.close();
		
		
		byte[] datad = getBytesFromFile("E:\\STUDY\\bind.js");
		//copyFile(datad, "E:\\STUDY\\bind.js.bak");
		//read(datad);
	}
	
	public static byte[] getBytesFromFile(String srcPath) throws IOException{
		File src = new File(srcPath);
		
		byte[] dest = null;
		InputStream is = new BufferedInputStream(new FileInputStream(src));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] flush = new byte[1024];
		int len = 0;
		while(-1 != (len = is.read(flush, 0, flush.length))){
			bos.write(flush, 0, len);
		}
		bos.flush();
		dest = bos.toByteArray();
		bos.close();
		is.close();
		return dest;		
	}
	
	public static void copyFile(byte[] data, String destPath) throws IOException{
		System.out.println(data.length);
		File dest = new File(destPath);
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
		OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
		
		byte[] flush = new byte[10];
		int len = 0; 
		while(-1 != (len = is.read(flush,0, flush.length))){
			os.write(flush, 0, len);
			System.out.println(new String(flush, 0, len));
			
		}
		os.flush();
		os.close();
		is.close();
		
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
