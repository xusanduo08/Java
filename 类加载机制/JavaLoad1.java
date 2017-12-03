package com.mengfansheng.javaload;
/**
 * 类加载器之间的关系
 * **/
public class JavaLoad1 {

	public static void main(String[] args) {
		System.out.println(ClassLoader.getSystemClassLoader());//sun.misc.Launcher$AppClassLoader@4e0e2f2a  应用类加载器
		System.out.println(ClassLoader.getSystemClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@2a139a55  扩展类加载器
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());//null，引导类加载器由C实现，java中获取不到，所以这地方是null

		//C:\Users\Administrator\workspace\JavaLoad\bin，也就是说，当前系统加载类的时候是按照这个路径加载的
		//这个路径各个项目是独立的，所以运行时项目之间不会相互影响
		System.out.println(System.getProperty("java.class.path"));
		
		System.out.println("*****************测试双亲加载机制******************");
		String a = "gogo";
		//获得a这个类的加载器
		//获取的类加载器是null，因为采用的是双亲委托加载机制，并且String这个类式在java的核心包rt.jar中，所以最终使用的是引导类加载器来加载这个类
		//我们自定义的String类并没有被加载
		System.out.println(a.getClass().getClassLoader());
		System.out.println(a);
	}

}
