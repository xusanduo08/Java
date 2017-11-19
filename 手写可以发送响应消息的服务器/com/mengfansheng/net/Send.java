package com.mengfansheng.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	//控制台输入流
	private BufferedReader console;
	//输出流
	private DataOutputStream dos;
	private boolean isRunning = true;
	private String name;
	
	
	/*
	 * 1 从控制台接收数据
	 * 
	 * */
	private String getMsgFromConsole() {
		try {
			String msg = console.readLine();
			
			return  msg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	/*
	 * 2 发送数据
	 * */
	public void send(String msg) {
		if(null != msg && !msg.equals("")){
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(dos, console);
			}
		}
	}
	
	public Send(){
		console = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public Send(Socket client, String name){
		this();
		try {
			this.dos = new DataOutputStream(client.getOutputStream());
			this.name = name;
			this.send(name);
		} catch (IOException e) {
			e.printStackTrace();
			this.isRunning = false;
		}
	}
	
	public Send(Socket client){
		this();
		try {
			this.dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			this.isRunning = false;
		}
	}
	
	@Override
	public void run() {
		while(isRunning){
			String msg = getMsgFromConsole();
			send(msg);
		}
	}
	
}
