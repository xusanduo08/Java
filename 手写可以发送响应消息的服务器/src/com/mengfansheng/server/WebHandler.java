package com.mengfansheng.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler {
	private List<Entity> entityList;
	private List<Mapping> mappingList;
	private String beginTag;
	private Entity entity;
	private Mapping mapping;
	
	
	
	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Mapping> getMappingList() {
		return mappingList;
	}

	public void setMappingList(List<Mapping> mappingList) {
		this.mappingList = mappingList;
	}

	private boolean isMap;//true=>servlet-mapping false=>servlet
	
	@Override
	public void startDocument() throws SAXException {
		//开始解析文档,初始化变量
		entityList = new ArrayList<Entity>();
		mappingList = new ArrayList<Mapping>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//开始解析元素
		if(null != qName){
			beginTag = qName;
			if(qName.equals("servlet")){
				isMap = false;
				entity = new Entity();
			} else if(qName.equals("servlet-mapping")){
				isMap = true;
				mapping = new Mapping();
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(null != beginTag){
			String str = new String(ch, start, length);
			if(isMap){
				for(Mapping map:mappingList){
					if(map.getName().equals(str)){
						mapping = map;
						break;
					}
				}
				if(beginTag.equals("servlet-name")){
					mapping.setName(str);
				} else if(beginTag.equals("url-pattern")){
					mapping.getUrlPattern().add(str);
				}
			} else {
				if(beginTag.equals("servlet-name")){
					entity.setName(str);
				} else if(beginTag.equals("servlet-class")){
					entity.setClz(str);
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//元素解析结束
		if(null != qName){
			if(qName.equals("servlet")){
				entityList.add(entity);
			} else if(qName.equals("servlet-mapping")){
				if(mappingList.size() == 0){
					mappingList.add(mapping);
				} else {
					for(Mapping map:mappingList){ //判断，根据name判断是否已经有相同name的mapping，如果没有，再add
						if(!map.getName().equals(mapping.getName())){
							mappingList.add(mapping);
							break;
						}
					}
				}
			}
		}
		beginTag = null;
	}
	
	@Override
	public void endDocument() throws SAXException {
		//文档解析结束
	}

}
