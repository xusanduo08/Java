package com.mengfansheng.net;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseXml {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//1、获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse = factory.newSAXParser();
		//3、加载文档处理器
		//4、编写处理器
		PersonHandler handler = new PersonHandler();
		parse.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("com/mengfansheng/net/person.xml"), handler);
		//获得处理结果
		List<Person> persons = handler.getPersons();
		for(Person person:persons){
			System.out.println(person.getName()+"===="+person.getAge());
		}
	}
}
