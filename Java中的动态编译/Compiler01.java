package com.mengfansheng.Compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Java的动态的编译功能
 * **/

public class Compiler01 {
	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//如何编译一个字符串？=》通过IO流，将字符串存储到文件中，然后调用动态编译方法
		String str="public class Hi{public static void main(String[] args){System.out.println(\"haha\");}}";
		String path = "e:"+java.io.File.separator+"gg"+java.io.File.separator+"Hi.java";
		OutputStream os = new FileOutputStream(path);
		byte[] bytes = str.getBytes();
		try {
			os.write(bytes, 0, bytes.length);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件写入失败");
		}
		
		int result = compiler.run(null, null, null, "c:"+java.io.File.separator+"gg" + java.io.File.separator +"HelloWorld.java");
		int result1 = compiler.run(null, null, null, path);
		System.out.println("文件1编译结果 " + result);
		System.out.println("文件2编译结果 " + result1);
		
		//通过Runtime.getRuntime()动态运行编译好的类
		Runtime run = Runtime.getRuntime();
		Process process = run.exec("java -cp c:/gg  HelloWorld");
		
		InputStream in = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String info = "";
		while(null !=(info = reader.readLine())){
			System.out.println(info);
		}
		
		//通过反射运行编译好的类
		URL[] urls = new URL[] {new URL("file:/" + "c:/gg/")};
		URLClassLoader loader = new URLClassLoader(urls);
		Class c = loader.loadClass("HelloWorld");
		//调用所加载类的main方法
		Method m = c.getMethod("main", String[].class);
		m.invoke(null, (Object)new String[]{"aa", "bb"});//main方法是个静态方法，可以不用对象去调用，所以这地方可以传个null
		//需要使用Object强转一下参数类型，如果不加，会被编译成m.invoke(null, "aa", "bb")，这就发生了参数个数不匹配的问题
		
	}
}
