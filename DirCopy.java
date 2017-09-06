package com.mengfansheng.IO;

import java.io.File;
import java.io.IOException;

/*
 * 文件夹拷贝
 * */

public class DirCopy {

	public static void main(String[] args) {
		String srcPath = "E:\\Git";
		String destPath = "E:\\dit";
		
		File src = new File(srcPath);
		File dest = new File(destPath);
		try {
			DirCopy(src, dest);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/*
	 * 拷贝文件
	 * @param src 源文件路径
	 * @param dest 目标文件路径
	 * */
	
	public static void DirCopy(String srcPath, String destPath) throws IOException{
		
		DirCopy(new File(srcPath),new File(destPath));
	}
	
	/*
	 * 拷贝文件
	 * @param src 源文件对象
	 * @param dest 目标文件对象
	 * */
	public static void DirCopy(File src, File dest) throws IOException{
		if(src.exists() && src.isFile()){
			try {
				FileCopy.FileCopy(src, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(src.exists() && src.isDirectory()){
			dest.mkdirs();
			for(File file:src.listFiles()){
				DirCopy(file, new File(dest,file.getName()));
			}
		} else {
			throw new IOException("源文件不存在,拷贝失败");
		}
	}

}
