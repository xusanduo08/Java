package com.mengfansheng.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mengfansheng.bean.User;
/**
 * 使用反射动态构造对象
 * 动态调用类和对象的任意方法、构造器
 * 动态调用和处理属性
 * **/
public class Reflection03 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		String path="com.mengfansheng.bean.User";
		Class clazz = Class.forName(path);
		User u = (User)clazz.newInstance();//使用无参构造器初始化对象

		//使用指定构造器初始化对象
		Constructor<User> c = clazz.getDeclaredConstructor(String.class, int.class, String.class);
		User u2 = c.newInstance("meng", 27, "programmer");
		
		//使用反射API调用普通方法
		Method method = clazz.getDeclaredMethod("setName", String.class);
		
		method.invoke(u, "fan");
		System.out.println(u.getName());
		
		//通过反射API操作属性
		Field f = clazz.getDeclaredField("name");
		f.setAccessible(true);//设置私有属性可操作
		f.set(u2, "sheng");
		System.out.println(u2.getName());
	}
 
}
