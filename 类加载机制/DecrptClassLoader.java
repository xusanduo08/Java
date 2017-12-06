package com.mengfansheng.javaload;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将class字节码加密（取反）后，使用正常类加载器无法加载，会提示java.lang.ClassFormatError类格式不正确
 * 使用下面这个类可以正常加载，原理就是读取字节码后又进行了一次取反操作
 * 加载文件系统中加密后的class字节码的类加载器
 * */
public class DecrptClassLoader extends ClassLoader {

	private String rootDir;//存储文件的根目录
	
	public DecrptClassLoader(String rootDir){
		this.rootDir = rootDir;
	}
	/**
	 * @param String com.mengfansheng.test.User 一个java类全称
	 * @return Class
	 * */
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);//先确认要加载的类是否已经加载
		
		if(null != c){
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				c = parent.loadClass(name);
			} catch (ClassNotFoundException e) {
				//
			}
			if(null != c){
				return c;
			} else {
				byte[] classData = getClassData(name);
				if(null ==  classData){
					throw new ClassNotFoundException();
				} else {
					c = defineClass(name, classData, 0, classData.length);
				}
			}
		}
		return c;
	}
	
	/**
	 * @param String com.mengfansheng.test.User 一个java类全称
	 * @return byte[]
	 * */
	private byte[] getClassData(String classname){
		String path = rootDir + "/" + classname.replace('.', '/') + ".class";
		
		InputStream is = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			is = new FileInputStream(path);
			
			int temp = -1;
			while((temp = is.read()) != -1){
				bos.write(temp^0xff);//取反操作
			}
			return bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}
	
}
