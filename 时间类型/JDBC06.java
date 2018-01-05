package com.mengfansheng.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 查询指定时间范围的数据
 * **/
public class JDBC06 {

	/**
	 * 将字符串代表的日期转为long类型数值  字符串格式为yyyy-MM-dd HH:mm:ss
	 * **/
	public static long str2Date(String str){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(str).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
			
			ps = conn.prepareStatement("select * from user where time > ? and time < ? order by time desc");
			
			java.sql.Timestamp start = new java.sql.Timestamp(str2Date("2018-1-7 9:44:22"));
			java.sql.Timestamp end = new java.sql.Timestamp(str2Date("2018-1-7 12:59:22"));
			ps.setTimestamp(1, start);
			ps.setTimestamp(2, end);
			
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name") + "==" + rs.getTimestamp("time"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
