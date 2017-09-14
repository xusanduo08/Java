package com.mengfansheng.IO;


import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class PStream {

	public static void main(String[] args) throws FileNotFoundException {
		//输出到文件
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("E:/Git/tt.txt")),true));//自动刷新
		
		System.out.println("输出到文件");
		
		//设置输出到控制台
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)),true));
		
	}

}
