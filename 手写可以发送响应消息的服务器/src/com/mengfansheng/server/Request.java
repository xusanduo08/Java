package com.mengfansheng.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.mengfansheng.net.CloseUtil;

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
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Request(InputStream is){
		this();
		this.is = is;
		try {
			byte[] data = new byte[20480];
			int len;
			len = is.read(data);
			this.requestInfo = new String(data, 0, len);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("============");
			CloseUtil.closeAll(is);
		}
		this.parseRequestInfo();
	}
	
	/**
	 * @desc 分析请求信息,分理出请求入参、请求方法、以及请求路径
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
		
		//equalsIgnoreCase 忽略大小写
		if(this.method.equalsIgnoreCase("post")){
			this.url = urlString;
			//如果是post请求，那么参数处于请求报文的最后一段
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		} else {
			//get请求，参数处于?之后
			if(urlString.contains("?")){
				String[] urlArr = urlString.split("\\?");
				this.url = urlArr[0];
				paramString = urlArr[1];
			} else {
				this.url = urlString;
			}
		}
		
		if(!paramString.equals("")){
			this.parseParams(paramString);
		}
	}
	
	/**
	 * @desc 解析请求参数至map中   key=value&key1=value1&key2=value2
	 * @param  入参字符串
	 * **/
	private void parseParams(String paramString){
		StringTokenizer token = new StringTokenizer(paramString, "&");
		while(token.hasMoreTokens()){
			String keyValue = token.nextToken();
			String[] keyValues = keyValue.split("=");
			if(keyValues.length == 1){
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}
			
			String key = keyValues[0].trim();
			String value = null == keyValues[1] ? null : decode(keyValues[1].trim(), "gbk");
			
			if(!this.paramter.containsKey(key)){
				paramter.put(key, new ArrayList<String>());
			}
			
			List<String> values = paramter.get(key);
			values.add(value);
		}
	}
	
	/**
	 * @desc根据name获取对应的多个值
	 * @param String name 要获取的值的键
	 * @return 值
	 * **/
	public String[] getParamters(String name){
		List<String> values = null;
		if((values = this.paramter.get(name)) == null){
			return null;
		} else {
			return values.toArray(new String[0]);
		}
	}
	
	/**
	 * 根据name获取对应的单个值
	 * @param String name 要获取的值的键
	 * @return 对应name的值
	 * **/
	public String getParamter(String name){
		String[] values = this.getParamters(name);
		if(null == values){
			return null;
		} else {
			return values[0];
		}
	}
	
	/**
	 * @desc 处理中文编码
	 * @param String value待处理值, String code 编码
	 * @return 处理后的中文字符串 
	 * **/
	private String decode(String value, String code){
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
