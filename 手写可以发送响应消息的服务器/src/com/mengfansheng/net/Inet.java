package com.mengfansheng.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Inet {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr.getHostName());
		System.out.println(addr.getHostAddress());
		InetAddress addr2 = InetAddress.getByName("www.baidu.com");
		System.out.println(addr2.getHostAddress());
		
		
		InetSocketAddress ins = new InetSocketAddress("192.168.1.100",80);
		System.out.println(ins.getPort());
		System.out.println(ins.getHostName());
		
		InetAddress addr3 = ins.getAddress();
		System.out.println(addr3.getHostName());
	}

}
