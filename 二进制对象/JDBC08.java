package com.mengfansheng.JDBC;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 测试BLOB的使用，包括：
 * BLOB存入数据库
 * BLOB从数据库中取出
 * **/
public class JDBC08 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
			
			//存储BLOB内容
			ps = conn.prepareStatement("insert into user (name, img) values (?, ?)");
			ps.setString(1, "郭沁");
			ps.setBlob(2, new FileInputStream("D:/qycache/picture/1.jpg"));
			ps.execute();
			
			//获取BLOB内容
			ps1 = conn.prepareStatement("select * from user where id= ?");
			ps1.setInt(1, 22923);
			
			rs = ps1.executeQuery();
			while(rs.next()){
				Blob b = rs.getBlob("img");
				is = b.getBinaryStream();
				//将获取的内容输出到文件
				os = new FileOutputStream("d:/a.jpg");
				int temp = 0;
				while((temp = is.read()) != -1){
					os.write(temp);
				}
				
			}
			
		} catch(Exception e){
			e.printStackTrace();
			
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
