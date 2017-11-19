package com.mengfansheng.net;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * person.xml的处理器
 * **/
public class PersonHandler extends DefaultHandler {
	private List<Person> persons;
	private Person person;
	private String tag;
	
	

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		persons = new ArrayList<Person>();//开始解析文档，这时候初始化 persons 容器
	}

	//开始解析一个元素，此时判空并记录元素标签名，并开始创建对象
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(null != qName){
			tag = qName;
		}
		if(null != qName && qName.equals("person")){
			person = new Person();
		}
	}

	//获取元素值，并根据标签名设置成对象对应属性的值
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String str = new String(ch, start, length);
		if(null != tag && tag.equals("name")){
			person.setName(str);
		} else if(null != tag && tag.equals("age")){
			Integer age = Integer.valueOf(str);
			person.setAge(age);
		}
	}
	
	//元素解析结束，
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(qName.equals("person")){
			this.persons.add(person);
		}
		tag = null;
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("处理文档结束");
		
	}
	

}
