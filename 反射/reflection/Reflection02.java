package com.mengfansheng.reflection;
/**
 * 使用反射获取对象的属性、方法、构造器
 * **/
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection02 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		String path="com.mengfansheng.bean.User";
		Class clz = Class.forName(path);
		
		System.out.println(clz.getName());//获得包名+类名
		System.out.println(clz.getSimpleName());//获得类名
		
		//获得属性
		Field[] fields = clz.getFields();//只能获取public属性
		System.out.println(fields.length);
		Field[] fields2 = clz.getDeclaredFields(); //获取所有属性
		System.out.println(fields2.length);
		for(Field temp:fields2){
			System.out.println(temp);
		}
		try {
			Field f = clz.getDeclaredField("name");// 获取指定属性
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//获取方法
		Method[] methods = clz.getMethods();
		Method method = clz.getMethod("getName");
		Method method1 = clz.getMethod("setName", String.class);
		for(Method m:methods){
			System.out.println(m);
		}
		
		//获取构造器
		Constructor[] constructors = clz.getDeclaredConstructors();
		Constructor constructor = clz.getDeclaredConstructor(String.class, int.class, String.class);
		System.out.println(constructor);
		for(Constructor c:constructors){
			System.out.println(c);
		}
	}

}
