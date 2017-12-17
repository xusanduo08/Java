package com.mengfansheng.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试PreparedStatement的使用，以及其对sql注入的防止
 * **/
public class JDBC03 {

	public static void main(String[] args){
		
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
		
			String sql = "insert into user(name, age, work) values (?, ? ,?)";//使用占位符
			PreparedStatement ps = conn.prepareStatement(sql);
			//使用setXXX()方法设置字段值，这地方的索引从1开始
			ps.setString(1, "孟凡胜");
			ps.setInt(2, 27);
			ps.setString(3, "IT");
			ps.execute();
			
			//或者直接用setObj()方法设置字段值
			ps.setObject(1, "fansheng");
			ps.setObject(2, 27);
			ps.setObject(3, "IT");
			ps.execute();
			
			//测试sql注入问题
			String deleSql = "delete from user where id = ?";
			PreparedStatement deletePs = conn.prepareStatement(deleSql);
			deletePs.setString(1, "1 or 2=2");//这时候即使我这样传入参数，也不会删除记录，preparedStatement对待执行的sql有一个预编译的过程
			deletePs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
