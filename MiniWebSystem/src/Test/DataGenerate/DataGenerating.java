package Test.DataGenerate;

import java.sql.*;
import java.util.*;

public class DataGenerating {
	public static void main(String[] args) {
		Connection conn;
		try {
			conn = new DbClass().getConn(DataPath.url);

			Statement smt = conn.createStatement();
			String sql1 = "create table if not exists product(proid varchar(64) PRIMARY KEY,proname varchar(64),proprice varchar(64),proaddress varchar(64),proimage varchar(128))";																											// KEY(username)
			smt.executeUpdate(sql1);

			String sql2 = "create table if not exists userinfo(id integer PRIMARY KEY AUTOINCREMENT,username varchar(64),pswd varchar(64))"; 
			smt.executeUpdate(sql2);
			
			
			 String sql3="insert into userinfo(username,pswd) values('xinxingwu','123456')";
		     smt.executeUpdate(sql3);
			
			ResultSet rs = smt.executeQuery("select * from userinfo");
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				
				System.out.println(rs.getString("username"));
				
				System.out.println(rs.getString("pswd"));
				
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
