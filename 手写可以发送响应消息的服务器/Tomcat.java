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

	//接收客户端信息
	private void receive(){
		try {
			Socket client = server.accept();
			//获取请求信息
			Request req = new Request(client.getInputStream());
			
			StringBuilder responseContent = new StringBuilder();
			//生成响应信息
			Response rep = new Response(client.getOutputStream());
			rep.print("<!DOCTYPE html><html><head><title>表单提交</title></head><body>Hello</body></html>");
			rep.pushToClient(404);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	//停止服务器
	public void stop(){
		
	}
}
