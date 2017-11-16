package com.mengfansheng.servlet;

import com.mengfansheng.server.Request;
import com.mengfansheng.server.Response;

/**
 * 登录响应信息的的工厂类
 * **/
public class LoginServlet extends Servlet {
	@Override
	public void doGet(Request req, Response rep) throws Exception {
		String name = req.getParamter("uname");
		String pwd = req.getParamter("pwd");
		
		if(login(name, pwd)){
			rep.print("<!DOCTYPE html><html><head><title>表单提交</title></head>");
			rep.print("<body>Hello,欢迎").print(req.getParamter("uname")).print("</body></html>");
		} else {
			rep.print("<!DOCTYPE html><html><head><title>表单提交</title></head>");
			rep.print("<body>登录失败</body></html>");
		}
		
	}

	/**
	 * @desc 判断用户名和密码是否正确
	 * @param String name, String pwd
	 * @return boolean true 登录成功  false登录失败
	 * **/
	public boolean login(String name, String pwd){
		if(null != name && null != pwd){
			return name.equals("admin") && pwd.equals("123456");
		}
		return false;
	}
	
	@Override
	public void doPost(Request req, Response rep) throws Exception {
		
	}
	
}
