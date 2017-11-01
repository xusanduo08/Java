package com.mengfansheng.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) throws IOException {
		new Server().start();
	}
	
	//使用容器管理所有客户端
	private List<Channel> all = new ArrayList<Channel>();
	
	
	public void start() throws IOException {
		ServerSocket server = new ServerSocket(9999);
		while(true){	
			Socket client = server.accept();
			Channel channel = new Channel(client);
			all.add(channel); //将所有已连接的客户端添加到容器中便于管理
			new Thread(channel).start();
		}
	}

	/*
	 * 客户端类  非静态内部类 必须存在于一个外部类中
	 * 每个客户端独占一个线程
	 * */
	private class Channel implements Runnable{
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;
		private String name;
		
		public Channel(Socket client){
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				
				this.name = dis.readUTF();
				this.send(this.name + "欢迎进入聊天室");  //欢迎自己进入聊天室
				this.sendAll(this.name + "进入聊天室");  //告诉其他人有人进入聊天室
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(dis, dos);
			}
		}
		
		private String receive(){
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(dis, dos);
				all.remove(this);//出现异常，移出自身
			}
			
			return msg;
		}
		
		private void send(String msg){
			if(null != msg || !msg.equals("")){
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
					isRunning = false;
					CloseUtil.closeAll(dis, dos);
					all.remove(this);//出现异常，移出自身
				}
				
			}
		}
		
		private void sendAll(String msg){
			
			//遍历容器，把消息发送到其他客户端
			for(Channel other:all){
				if(other == this){
					continue;
				}
				other.send(msg);
			}
		}
		
		@Override
		public void run() {
			while(isRunning){
				String msg = this.receive();
				sendAll(msg);
			}
		}
		
	}
	
}
