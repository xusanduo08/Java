package com.mengfansheng.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {
		File src = new File("E:/Git/README.md");
		File dest = new File("E:/Git/README.md.cp");
		
		try {
			FileCopy("E:/Git/README.md","E:/Git/README.md.cp1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*文件拷贝
	 * srcPath 源文件路径
	 * destPath 目标文件路径
	 * */
	public static void FileCopy(String srcPath, String destPath) throws IOException{
		
		FileCopy(new File(srcPath), new File(destPath));
	}
	/*文件拷贝
	 * src 源文件对象
	 * dest 目标文件对象
	 * */
	public static void FileCopy(File src, File dest) throws IOException{
		if(!src.isFile()){
			throw new IOException("只能拷贝文件");
		}
		
		if(src.isDirectory()){
			throw new IOException("请输入一个文件的路径");
		}
		
		InputStream is = new FileInputStream(src);
		OutputStream os = new FileOutputStream(dest);
		
		byte[] data = new byte[10];
		int len = 0;
		
		while(-1 != (len = is.read(data))){
			//写出
			os.write(data,0, len);
		}
		os.flush();//冲刷管道
		os.close();//关闭流
		is.close();
	}
	
}
