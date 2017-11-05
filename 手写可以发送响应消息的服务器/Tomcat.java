package com.mengfansheng.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
 * 创建服务器，并启动
 * */
public class Tomcat {
	private ServerSocket server;
	private static final String CRLF = "\r\n";
	private static final String SPACE = " ";
	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		tomcat.start();
	}
	
	//启动服务器
	public void start(){
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//接收客户端
	private void receive(){
		try {
			Socket client = server.accept();
			
			byte[] data = new byte[20480];
			int len = client.getInputStream().read(data);
			String msg = new String(data, 0, len);//接收客户端请求信息
			
			//接收客户端请求信息
			System.out.println(msg);
			
			StringBuilder responseContent = new StringBuilder();
			responseContent.append("<!DOCTYPE html><html><head><title>表单提交</title></head><body>Hello</body></html>");
			//构建响应消息
			StringBuilder response = new StringBuilder();
			//HTTP协议版本、状态码、描述
			response.append("HTTP/1.1").append(this.SPACE).append("200 ok").append(CRLF);
			//响应头
			response.append("Server:BWS/1.1").append(CRLF);
			response.append("Date:").append(new Date()).append(CRLF);
			response.append("Content-Type:text/html;charset=GBK").append(CRLF);
			//正文长度--字节数
			response.append("Content-Length:").append(responseContent.toString().getBytes().length).append(CRLF);
			
			response.append(CRLF);//空一行，开始正文
			
			//正文
			response.append(responseContent).append(CRLF);
			
			//输出流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bw.write(response.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//停止服务器
	public void stop(){
		
	}
}
