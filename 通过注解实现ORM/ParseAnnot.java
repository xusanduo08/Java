package com.mengfansheng.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射读取注解信息，模拟处理注解信息的流程
 * @author mengfansheng
 * **/
public class ParseAnnot {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		try {
			Class clz = Class.forName("com.mengfansheng.annotation.Student");
			
			//获得类的所有注解
			Annotation[] annots = clz.getDeclaredAnnotations();
			for(Annotation a : annots){
				System.out.println(a);
			}
			//获得指定注解类在某个类中的注解(也可以说是获得类的指定的注解)
			Table st = (Table)clz.getAnnotation(Table.class);
			System.out.println(st.value());
			
			//获得类的属性的注解
			Field f = clz.getDeclaredField("name");//先通过反射获得某个属性
			TableField tb = f.getAnnotation(TableField.class);//然后获得这个属性的指定类的注解
			//这样就能获得指定属性在数据库中对应的字段名称、类型以及字段长度
			System.out.println(tb.columnName() +"--"+tb.type() +"--"+tb.length());
			
			//接下来就可以根据获得的表名、字段的信息，拼接出DDL语句，然后使用JDBC执行这个SQL，在数据库中生成相关的表
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
