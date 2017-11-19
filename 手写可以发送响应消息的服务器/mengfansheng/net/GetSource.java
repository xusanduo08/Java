package com.mengfansheng.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class GetSource {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.baidu.com");
		
		//获取资源网络流
		InputStream is = url.openStream();
		byte[] flush = new byte[1024];
		int len = 0;
		
		String msg = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidudu.html"),"utf-8"));
		while(null != (msg = br.readLine())){
			bw.append(msg);
			bw.newLine();
		}
		bw.close();
		
		bw.close();
		br.close();
		is.close();
	}

}
