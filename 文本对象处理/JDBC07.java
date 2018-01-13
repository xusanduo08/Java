package com.mengfansheng.JDBC;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Clob;

/**
 * 测试文本大对象CLOB的使用
 * 将字符串、文本文件插入数据库中的CLOB字段
 * 将CLOB字段值取出来
 * **/
public class JDBC07 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
			
			/*
				ps = conn.prepareStatement("insert into user(name, text) values(?, ?)");
				ps.setString(1, "孟凡胜");
				ps.setClob(2, new FileReader(new File("D:/Git/LICENSE.txt")));//将文本内容直接输入到数据库中
				ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("aabbbccc".getBytes()))));
				ps.execute();
			*/
			ps = conn.prepareStatement("select * from user where id= ?");
			ps.setObject(1, 22921);
			rs = ps.executeQuery();
			while(rs.next()){
				Clob c = (Clob) rs.getClob("text");
				Reader r = c.getCharacterStream();
				int temp = 0;
				while((temp = r.read()) != -1){
					System.out.println((char)temp);
				}
			}
		} catch(Exception e) {
			
		}

	}

}
