package com.mengfansheng.javaload;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义文件系统类加载器
 * **/
public class JavaLoader extends ClassLoader{
	//com.mengfansheng.test.User ==>D:/myjava/com/mengfansheng/test/User.class
	
	private String rootDir;
	
	public JavaLoader(String rootDir){
		this.rootDir = rootDir;
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException{
		Class<?> c = findLoadedClass(name);
		//先查询是否加载过该类，如果已经加载，直接返回；没有，则加载该类。
		if(null != c){
			return c;
		} else {
			//委托加载：使用父类加载
			ClassLoader parent = this.getParent();
			try{
				c = parent.loadClass(name);
			} catch(Exception e){
				//e.printStackTrace();
			}
			
			if(c != null){
				return c;
			} else {
				byte[] classData = null;
				try {
					classData = getClassData(name);//获取类的字节码
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(classData == null){
					throw new ClassNotFoundException();
				} else {
					c = defineClass(name, classData, 0, classData.length);
				}
			}
			
		}
		return c;
	}
	
	/**
	 * 获得类的字节码
	 * **/
	private byte[] getClassData(String className) throws IOException{
		String path = this.rootDir + "/" + className.replace('.', '/') + ".class";
		InputStream is = new FileInputStream(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		int temp = 0;
		while((temp = is.read(buffer)) != -1){
			baos.write(buffer, 0, temp);
		}
		is.close();
		return baos.toByteArray();
	}
	
	public static void main(String[] args){
		JavaLoader loader = new JavaLoader("E:/gg");
		JavaLoader loader2 = new JavaLoader("E:/gg");
		try {
			Class<?> c = loader.findClass("com.mengfansheng.test.Hi");
			Class<?> c1 = loader.findClass("com.mengfansheng.test.Hi");
			Class<?> c2 = loader2.findClass("com.mengfansheng.test.Hi");
			Class<?> c3 = loader2.findClass("java.lang.String");
			Class<?> c4 = loader2.findClass("com.mengfansheng.javaload.JavaLoader");
			
			System.out.println(c.hashCode());
			System.out.println(c1.hashCode());//同一个类加载器加载的同一个类相同
			System.out.println(c2.hashCode());//不同加载器加载同一个类不同
			
			//比较各类的加载器
			System.out.println(c2.getClassLoader());//自定义的加载器 com.mengfansheng.javaload.JavaLoader
			System.out.println(c3.getClassLoader());//引导类加载器
			System.out.println(c4.getClassLoader());//应用程序类（系统类）加载器 sun.misc.Launcher$AppClassLoader
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
