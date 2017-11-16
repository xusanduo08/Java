package com.mengfansheng.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 为不同请求，生成不同上下文
 * **/
public class ServletContext {
	private Map<String, String> servlet;
	private Map<String, String> mapping;
	
	public ServletContext(){
		//name -->包名.类名
		this.servlet = new HashMap<String, String>();
		//url -->name
		this.mapping = new HashMap<String, String>();
	}

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
}
