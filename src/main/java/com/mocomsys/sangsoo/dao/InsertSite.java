package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertSite {
	
	String insertSiteSQL = "INSERT INTO SITE(CUSTOMER_UID, SITE_NAME, SITE_STATE_UID, NOTE) VALUES(?, ?, ?, ?)";
	public String insertSiteMethod(int uid, String name, int state, String note){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(insertSiteSQL);
			stmt.setInt(1, uid);
			stmt.setString(2, name);
			stmt.setInt(3, state);
			stmt.setString(4, note);
			stmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("InsertSite ERROR : " + e);
			return "false";
		}finally {
			JDBCUtilWTS.close(stmt, conn);
		}
		return "success";
	}
	
}
