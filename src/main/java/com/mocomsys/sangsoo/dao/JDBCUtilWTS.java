package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtilWTS {
	public static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("db연결 시작");
			
			return DriverManager.getConnection("jdbc:mysql://10.10.1.61:3306/wts_test_copy", "wts", "wts");
		}catch(Exception e){
			System.out.println("error : " + e);
		}
		return null;
	}
	public static void close(PreparedStatement stmt, Connection conn) {
		try {
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		try {
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
		System.out.println("db연동 닫았다.");

	}

	//select용 닫기 메소드.

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			if(rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
		try {
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		try {
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
		System.out.println("db연동 닫았다.");

	}

	
}
