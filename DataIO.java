package com.mengfansheng.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIO {

	public static void main(String[] args) throws IOException {
		//数据类型处理流：基本数据类型+String
		
		byte[] data = write();
		read(data);
	}
	
	//从字节数组读取数据+类型
	public static void read(byte[] src) throws IOException {
		
		DataInputStream dis = new DataInputStream(
					new BufferedInputStream(new ByteArrayInputStream(src))
				);
		//读取顺序与写出顺序一致 且必须存在才能读取
		
		double num = dis.readDouble();
		long num1 = dis.readLong();
		String str = dis.readUTF();
		dis.close();
		System.out.println(num+" "+num1+" "+str);
	}

	/*
	 * 数据+类型输出到字节数组
	 * */
	
	public static byte[]  write() throws IOException{
		byte[] dest = null;
		double point = 2.3;
		long num = 100L;
		String str = "数据类型";
		
		//创建源
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(
								bos
							)
				);
		
		dos.writeDouble(point);
		dos.writeLong(num);
		dos.writeUTF(str);
		dos.flush();
		dest = bos.toByteArray();
		bos.close();
		return dest;
	}
}
