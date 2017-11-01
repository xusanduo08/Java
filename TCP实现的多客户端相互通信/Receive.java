package com.mengfansheng.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
	private DataInputStream dis;
	private boolean isRunning = true;
	public Receive(){}
	
	public Receive(Socket client){
		try {
			this.dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			this.isRunning = false;
			CloseUtil.closeAll(dis);
		}
	}

	public String receive(){
		String msg = "";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
			this.isRunning = false;
			CloseUtil.closeAll(dis);
		}
		return msg;
	}
	
	@Override
	public void run() {
		while(isRunning){
			//this.receive();
			System.out.println(" ’µΩ" + receive());
		}
	}
}
