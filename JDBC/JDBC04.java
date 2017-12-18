package com.mengfansheng.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 测试结果集ResultSet的使用
 * **/
public class JDBC04 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			
			String sql = "select * from user where id > ?";
			 ps = conn.prepareStatement(sql);		
			ps.setObject(1, 9);
	
			 rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getInt(1) + "--" + rs.getString(2) + "---" + rs.getInt(3));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null){
				try {
					conn.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		

	}

}
