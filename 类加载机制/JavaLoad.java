package com.mengfansheng.javaload;

public class JavaLoad {

	/**
	 * 先执行静态代码块
	 * 然后执行A类的构造器
	 * **/
	public static void main(String[] args) {
		A a = new A();
		System.out.println(A.width);
	}

}

class A{
	public static int width = 100;
	
	static{
		System.out.println("静态初始化类A");
		width = 300;
	}
	
	public A(){
		System.out.println("创建A类的对象");
	}
}