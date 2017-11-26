package com.mengfansheng.Compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Java的动态的编译功能
 * **/

public class Compiler01 {
	public static void main(String[] args) throws FileNotFoundException{
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
	}
}
