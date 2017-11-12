package com.mengfansheng.net;

import java.io.IOException;
import java.net.Socket;

/**
 * 实现接收与响应的多线程
 * **/
public class Dispatcher implements Runnable{
	private Request req;
	private Response rep;
	private Socket client;
	private int code = 200;
	
	public Dispatcher(Socket client){
		this.client = client;
		try {
			req = new Request(this.client.getInputStream());
			rep = new Response(this.client.getOutputStream());
		} catch (IOException e) {
			code = 500;
			return;
		}
	}
	
	@Override
	public void run() {
		//将具体响应消息生成动作封装到类中
		Servlet servlet = new Servlet();
		servlet.service(req, rep);
	
		rep.pushToClient(code);
		CloseUtil.closeAll(client);
		
	}

}
