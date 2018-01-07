package com.mengfansheng.JDBC;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 测试文本大对象CLOB的使用
 * **/
public class JDBC07 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
			
			
			ps = conn.prepareStatement("insert into user(name, text) values(?, ?)");
			ps.setString(1, "孟凡胜");
			ps.setClob(2, new FileReader(new File("D:/Git/LICENSE.txt")));//将文本内容直接输入到数据库中
			ps.execute();
		} catch(Exception e) {
			
		}

	}

}
