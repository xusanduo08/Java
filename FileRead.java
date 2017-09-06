package com.mengfansheng.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileRead {

	public static void main(String[] args) throws IOException {
		//建立联系（选择读取文件）
		File src = new File("E:/Git/README.md");
		//选择流
		InputStream is = new FileInputStream(src);
		//读取长度
		int len = 0;
		//建立字节数组
		byte[] car = new byte[4];
		while(-1 != (len = is.read(car))){
			String str = new String(car,0,len);
			System.out.println(str);
		}
		if(null != is){
			is.close();
		}
		
	}

}
