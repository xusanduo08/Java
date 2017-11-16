package com.mengfansheng.servlet;

import com.mengfansheng.server.Request;
import com.mengfansheng.server.Response;

/**
 * 注册的工厂类
 * **/
public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request req, Response rep) throws Exception {
		
	}

	@Override
	public void doPost(Request req, Response rep) throws Exception {
		rep.print("<!DOCTYPE html><html><head><title>注册结果</title></head>");
		rep.print("<body>Hello,欢迎").print(req.getParamter("uname")).print(" 注册成功</body></html>");
		
	}

}
