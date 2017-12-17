package com.mengfansheng.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试Statement的使用，以及其自身容易带来的sql注入问题
 * **/

public class JDBC02 {

	public static void main(String[] args){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root", "");
			
			Statement stmt = conn.createStatement();//执行sql语句，并返回相关结果
			//String sql = "insert into user(id, name, age,work) values (null, 'mengfansheng', 25, 'IT')";
			//stmt.execute(sql);
			
			//模拟SQL注入
			String id = "5 or 1=1";//此时，不论是哪条记录，where后面得出的都是true，所有记录都会被删掉，这就是sql注入
			String sql = "delete from user where id=" + id;//待执行的sql语句是通过字符串拼接的方式产生的，这种方式就容易产生sql注入
			stmt.execute(sql);
			
			/**
			 * statement 在工作中并不常用，因为有sql注入问题
			 * 比较常用的是prepareStatement
			 * **/
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
