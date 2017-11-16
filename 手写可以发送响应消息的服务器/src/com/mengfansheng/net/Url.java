package com.mengfansheng.net;

import java.net.MalformedURLException;
import java.net.URL;

public class Url {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://download.java.net/jdk7/archive/b123/docs/api/java/net/InetSocketAddress.html");
		System.out.println(url.getProtocol());
		System.out.println(url.getAuthority());
		
		URL url2 = new URL(url, "/b.txt?uname=sdfdf#dfd");
		System.out.println(url2.getHost());
		System.out.println(url2.getFile());
		System.out.println(url2.getPath());
		System.out.println(url2.getRef());
		System.out.println(url2.getQuery());
	}

}
