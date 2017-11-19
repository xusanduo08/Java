package com.mengfansheng.net;

import java.io.Closeable;
import java.io.IOException;
/*
 * 关闭工具
 * 将所有流进行关闭
 * */
public class CloseUtil {

	public static void closeAll(Closeable... io) {  //...可变参数
		for(Closeable temp:io){
			if(null != temp){
				try {
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
