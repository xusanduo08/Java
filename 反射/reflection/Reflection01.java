package com.mengfansheng.reflection;

@SuppressWarnings("all")
public class Reflection01 {

	public static void main(String[] args) {
		String str = "com.mengfansheng.bean.User";
		try {
			/*
			 * 一个类被加载后，JVM会创建一个对应该类的Class对象，类的整个结构信息都会被放到对应的Class对象中
			 * 这个Class对象就像一面镜子一样，通过这面镜子就可以看到对应类的全部信息
			 * 同一个类只会被加载一次，同一个类只会有一个Class对象
			 * */
			Class clz = Class.forName(str);
			Class clz2 = Class.forName(str);//同一个类只会被加载一次，多次获取同一个类，获取的也只是同一个Class
			System.out.println(clz.hashCode());
			System.out.println(clz2.hashCode());
			
			int[] arr = new int[22];
			int[] arr1 = new int[33];
			double[] arr2 = new double[33];
			System.out.println(arr.getClass().hashCode());
			System.out.println(arr1.getClass().hashCode());//同类型、同维度的数组获取的Class对象相同
			System.out.println(arr2.getClass().hashCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
