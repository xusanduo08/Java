package com.mengfansheng.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 解析请求报文
 * */
public class Request {
	//请求方式
	private String method;
	//请求资源
	private String url;
	//请求参数
	private Map<String, List<String>> paramter;
	
	public static final String CRLF = "\r\n";
	public static final String BLANK = " ";
	
	//服务端输入流，从中获取客户端请求信息
	private InputStream is;
	//存储请求信息
	private String requestInfo;
	
	public Request(){
		this.method = "";
		this.url = "";
		this.paramter = new HashMap<String, List<String>>();
		this.requestInfo = "";
	}
	
	public Request(InputStream is){
		this();
		this.is = is;
		try {
			byte[] data = new byte[20480];
			int len;
			len = is.read(data);
			this.requestInfo = new String(data, 0, len);
			System.out.println(this.requestInfo);
		} catch (IOException e) {
			e.printStackTrace();
			CloseUtil.closeAll(is);
		}
		this.parseRequestInfo();
	}
	
	/**
	 * @desc 分析请求信息
	 * **/
	private void parseRequestInfo(){
		//防止空指针异常
		if(null == requestInfo || (requestInfo.trim()).equals("")){
			return;
		}
		
		/**
		 * 从首行解析出 请求方法 请求路径 请求参数
		 * post请求方式请求参数位于最后，或者没有
		 * **/
		//存储请求参数
		String paramString = "";
		
		//请求信息首行
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int index = firstLine.indexOf("/");
		this.method = firstLine.substring(0, index).trim();
		
		//存储资源路径
		String urlString = firstLine.substring(index, firstLine.indexOf("HTTP")).trim();
		
		if(this.method.equalsIgnoreCase("post")){
			this.url = urlString;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		} else {
			if(urlString.contains("?")){
				String[] urlArr = urlString.split("\\?");
				this.url = urlArr[0];
				paramString = urlArr[1];
			} else {
				this.url = urlString;
			}
		}
		
	}
}
