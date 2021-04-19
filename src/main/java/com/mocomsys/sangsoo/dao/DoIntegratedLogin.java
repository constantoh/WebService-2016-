package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mocomsys.sangsoo.vo.User;

public class DoIntegratedLogin {
	
	public User doIntegratedLogin(String id, String pwd){
		
		System.out.println("(2) id : " + id + ", pwd : " + pwd);
		String result="false";
		User user = new User();
		
		String loginSQL = "SELECT * FROM integrateduser where id=? and pwd=?";
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		
		try{
			conn = JDBCUtiIntegrated.getConnection();
			stmt = conn.prepareStatement(loginSQL);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getString("id"));
				user.setPwd(rs.getString("pwd"));
				user.setName(rs.getString("name"));
				user.setWts_id(rs.getString("wts_id"));
				user.setWts_pwd(rs.getString("wts_pwd"));
				user.setGroupware_id(rs.getString("groupware_id"));
				user.setGroupware_pwd(rs.getString("groupware_pwd"));
				user.setNaver_id(rs.getString("naver_id"));
				user.setNaver_pwd(rs.getString("naver_pwd"));
				user.setEmail_id(rs.getString("email_id"));
				user.setEmail_pwd(rs.getString("email_pwd"));
				user.setFavorite1(rs.getString("favorite1"));
				user.setFavorite2(rs.getString("favorite2"));
				user.setFavorite3(rs.getString("favorite3"));

				result = "success";
				//System.out.println("(DoIntegreatedDAO) user.setName : " + user.getName());
			}
		}catch(Exception e){
			System.out.println("doIntegratedLogin ERROR " + e);
			e.printStackTrace();
		}
		
		return user;
		
	}
	
	public String modifyFavorite(String f1, String f2, String f3, String id){
		String result="";
		String updateSQL = "UPDATE integrateduser SET favorite1=?, favorite2=?, favorite3=? WHERE id=?";
		
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
			conn = JDBCUtiIntegrated.getConnection();
			stmt = conn.prepareStatement(updateSQL);
			stmt.setString(1, f1);
			stmt.setString(2, f2);
			stmt.setString(3, f3);
			stmt.setString(4, id);

			if(stmt.executeUpdate()!=0){
				result="success";
			}else{
				result="fail";
			}
				
		}catch(Exception e){
			System.out.println("doUpdate ERROR " + e);
			e.printStackTrace();
		}
		
		return result;
	}

}
