package com.mengfansheng.net;

import com.mengfansheng.server.Request;
import com.mengfansheng.server.Response;

/**
 * @desc 一个抽象类，便于子类继承去生成对应不同上下文的返回信息
 * **/
public abstract class Servlet {
	
	/**
	 * @desc 拼接响应消息体
	 * @param Request req, Response rep
	 * @throws Exception 
	 * **/
	public void service(Request req, Response rep) throws Exception{
		this.doGet(req, rep);
		this.doPost(req, rep);
	}
	
	public abstract void doGet(Request req, Response rep) throws Exception;
	public abstract void doPost(Request req, Response rep) throws Exception;
}
