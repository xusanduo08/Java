package com.mengfansheng.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;
/**
 * 测试jdbc的批处理
 * **/
public class JDBC05 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		Result rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root","");
			
			conn.setAutoCommit(false);//设置自动提交事务为false
			stmt = conn.createStatement();
			long start = System.currentTimeMillis();
			for(int i = 0; i< 20000; i++){
				stmt.addBatch("insert into user(name,work,date) values ('fansheng" + i +"','IT', now())");
			}
			stmt.executeBatch();
			long end = System.currentTimeMillis();
			System.out.println("插入两万条数据耗时：" + (end - start) +"毫秒");
			conn.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
