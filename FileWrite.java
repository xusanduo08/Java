package com.mengfansheng.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileWrite {

	public static void main(String[] args) {
		//建立联系（选择目的地）
		File src = new File("E:/Git/ss.txt");
		OutputStream os = null;
		try {
			//选择流 
			 os = new FileOutputStream(src, false);//true 追加形式写入 false 以覆盖形式写入
			
			String str="孟凡胜 ";
			byte[] bytes = str.getBytes();//字符串转字节数组
			try {
				os.write(bytes, 0, bytes.length);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("文件写入失败");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件未找到");
		} finally {
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
