package com.mengfansheng.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mengfansheng.bean.User;

/**
 * 反射效率的比较
 * 禁止安全检查可以提高反射的运行速度
 * **/
public class Reflection04 {

	public static void test01(){
		User u = new User();
		
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 1000000000L; i++){
			u.getName();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("普通方法调用，执行10亿次，耗时" + (endTime - startTime) + "ms");
	}
	
	public static void test02() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		User u = new User();
		Class clazz = u.getClass();
		Method m = clazz.getDeclaredMethod("getName", null);
		//m.setAccessible(true);//需要执行访问安全检查
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 1000000000L; i++){
			m.invoke(u, null);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("反射动态方法调用，执行10亿次，耗时" + (endTime - startTime) + "ms");
	}
	
	public static void test03() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		User u = new User();
		Class clazz = u.getClass();
		Method m = clazz.getDeclaredMethod("getName", null);
		m.setAccessible(true);//不需要执行访问安全检查
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 1000000000L; i++){
			m.invoke(u, null);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("反射动态方法调用，跳过安全检查，执行10亿次，耗时" + (endTime - startTime) + "ms");
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		test01();
		test02();
		test03();

	}

}
