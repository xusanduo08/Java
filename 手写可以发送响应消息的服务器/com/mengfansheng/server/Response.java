package com.mengfansheng.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.mengfansheng.net.CloseUtil;

/*
 * 封装响应消息
 * */

public class Response {
	public static final String CRLF = "\r\n";
	public static final String BLANK = " ";
	
	//输出流
	private BufferedWriter bw;
	
	//存储响应体
	private StringBuilder content;
	
	//存储响应头
	private StringBuilder headInfo;
	private int len;
	
	public Response(){
		this.headInfo = new StringBuilder();
		this.content = new StringBuilder();
		this.len = 0;
	}
	
	public Response(OutputStream os){
		this();
		this.bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	/*
	 * @desc 拼装响应消息体
	 * @params String info 响应消息字符串内容
	 * @returns Response 返回响应消息本身，这样可以流式不断添加响应消息
	 * */
	public Response print(String info){
		this.content.append(info).append(this.CRLF);
		this.len+=(info+this.CRLF).getBytes().length;
		return this;
	}
	
	/*
	 * @desc拼装响应信息头部行，私有方法，内部使用
	 * @params code 应答码
	 * */
	private void createHeadInfo(int code){
		headInfo.append("HTTP/1.1").append(this.BLANK).append(code).append(this.BLANK);
		switch(code){
		case 200:
			headInfo.append("OK");
			break;
		case 404:
			headInfo.append("Not Found");
			break;
		case 500:
			headInfo.append("Server Error");
		}
		headInfo.append(this.CRLF);
		//响应头
		headInfo.append("Server:BWS/1.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-Type:text/html;charset=GBK").append(CRLF);
		//正文长度--字节数
		headInfo.append("Content-Length:").append(Integer.toString(len)).append(CRLF);
		
		headInfo.append(CRLF);//空一行，开始正文
	}
	
	/*
	 * @desc 发送消息至客户端
	 * @params int code 状态码，在调用该方法时传入
	 * */
	void pushToClient(int code){
		this.createHeadInfo(code);
		this.print("");
		
		try {
			bw.append(this.headInfo.toString());
			bw.append(this.content.toString());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CloseUtil.closeAll(bw);
	}
}
