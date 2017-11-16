package com.mengfansheng.server;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类，存储
 * <servlet-name>login</servlet-name>
	<url-pattern>/login</url-pattern>
 * **/
public class Mapping {
	private String name;
	//一个name可能对应多个url，所以这地方用个List容器存储
	private List<String> urlPattern;
	
	public Mapping(){
		urlPattern = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
	
}
