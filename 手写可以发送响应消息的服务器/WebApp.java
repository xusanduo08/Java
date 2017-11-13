package com.mengfansheng.net;

import java.util.Map;
/**
 * 用来通过请求的url返回对应的响应消息
 * **/
public class WebApp {
	private static ServletContext context;
	//静态代码块，只在构造函数前执行一次，只执行一次
	static {
		context = new ServletContext();
		
		Map<String, String> mapping = context.getMapping();
		mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/reg", "register");
		
		Map<String, String> servlet = context.getServlet();
		servlet.put("login", "com.mengfansheng.net.LoginServlet");
		servlet.put("register", "com.mengfansheng.net.RegisterServlet");
	}
	
	/**
	 * @desc 通过url返回对应的响应消息
	 * @param String url 请求的url
	 * @return Servlet 响应消息内容
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * **/
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(null == url || (url = url.trim()).equals("")){
			return null;
		}
		//根据字符串创建对象，使用时创建对象，反射
		String name = context.getServlet().get(context.getMapping().get(url));
		
		return (Servlet) Class.forName(name).newInstance();
	}
}
