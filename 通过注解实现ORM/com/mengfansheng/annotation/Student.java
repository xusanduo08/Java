package com.mengfansheng.annotation;

/**
 * 使用注解实现ORM(Object Relationship Mapping)
 * 数据库使用的是面向关系的，而写代码的时候是面向对象的，这两者之间需要有一个映射
 * 使用注解将对象和数据关系进行映射，通过解析程序解析注解，生成SQL语句
 * **/

@Table(value="tb_student") //使用注解表示该类在数据库中对应哪张表
public class Student {
	//使用注解表示该属性对应数据库中的哪一列、数据长度以及数据类型
	@TableField(columnName = "id", length = 10, type = "int")
	private int id;
	@TableField(columnName = "name", length = 10, type = "varchar")
	private String name;
	@TableField(columnName = "age", length = 3, type = "int")
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
