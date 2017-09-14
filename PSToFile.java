package com.mengfansheng.IO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class PSToFile {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream ps = System.out;
		ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("E:/Git/rr.txt"))), true);//true  ×Ô¶¯Ë¢ÐÂ
		ps.println("io is so easy");
		//ps.flush();
		ps.close();

	}

}
