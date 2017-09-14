package com.mengfansheng.IO;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class PStream {

	public static void main(String[] args) throws FileNotFoundException {
		//输出到文件
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("E:/Git/tt.txt")),true));//自动刷新
		
		System.out.println("输出到文件");
		
		//设置输出到控制台
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)),true));
		
		//设置标准输入流从文件输入
		System.setIn(new BufferedInputStream(new FileInputStream(new File("E:/Git/tt.txt"))));
		Scanner sc = new Scanner(System.in);
		System.out.println(sc.nextLine());
		
		//设置标准输入流冲控制台输入
		System.setIn(new BufferedInputStream(new FileInputStream(FileDescriptor.in)));
		
	}

}
