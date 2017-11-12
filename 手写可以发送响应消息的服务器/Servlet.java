package com.mengfansheng.net;
/**
 * @desc 进一步封装，将具体的响应内容封装到类中
 * **/
public class Servlet {
	public void service(Request req, Response rep){
		rep.print("<!DOCTYPE html><html><head><title>表单提交</title></head>");
		rep.print("<body>Hello,欢迎").print(req.getParamter("uname")).print("</body></html>");
		rep.pushToClient(404);
	}
}
