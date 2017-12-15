package com.mengfansheng.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * JDBC连接测试
 * **/
public class JDBC01 {

	public static void main(String[] args){
		//加载驱动类
		try {
			Class.forName("com.mysql.jdbc.Driver");
			long start = System.currentTimeMillis();
			//建立连接（连接对象内部其实包含了Socket对象，是一个远程的连接。比较耗时，这是Connection对象管理的一个要点！）
			//真正开发中，为了提高效率，都会使用连接池来管理连接对象
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			System.out.println(conn);
			long end = System.currentTimeMillis();
			//建立连接比较耗时
			System.out.println("建立连接，耗时：" + (end - start) + "毫秒");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
