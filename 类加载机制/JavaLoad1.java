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
	}

}
