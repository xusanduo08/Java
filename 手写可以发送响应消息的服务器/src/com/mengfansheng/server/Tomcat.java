package com.mengfansheng.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.mengfansheng.net.CloseUtil;

/*
 * 创建服务器，并启动
 * */
public class Tomcat {
	private ServerSocket server;
	private static final String CRLF = "\r\n";
	private static final String SPACE = " ";
	private boolean isShutdown = false;
	
	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		tomcat.start();
	}
	
	/**
	 * @desc 以默认端口启动服务器
	 * **/
	public void start(){
		start(8888);
	}
	
	/**
	 * @desc 启动服务器
	 * @param 指定端口
	 * */
	public void start(int port){
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			stop();
		}
	}

	/**
	 * @desc 接收客户端信息
	 * **/
	private void receive(){
		try {
			while(!isShutdown){
				Socket client = server.accept();
				//将消息的发送和接收封装到线程中
				Dispatcher dis = new Dispatcher(client);
				new Thread(dis).start();
			}
		} catch (IOException e) {
			this.stop();
		}
	}
	
	/**
	 * @desc 停止服务器 
	 * **/
	public void stop(){
		isShutdown = true;
		CloseUtil.closeAll(server);
	}
}
