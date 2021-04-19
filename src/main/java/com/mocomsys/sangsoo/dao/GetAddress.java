package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mocomsys.sangsoo.vo.Address;

public class GetAddress {
	
	private String getManyAddressSQL = "SELECT COUNT(*) "
			+ "FROM T_USER, T_PART, T_PARTMB, T_POSI, T_EMP "
			+ "WHERE T_USER.USERNO = T_PARTMB.USERNO "
			+ "AND T_USER.USERNO = T_EMP.USERNO "
			+ "AND T_PARTMB.PARTNO = T_PART.PARTNO "
			+ "AND T_EMP.POSINO = T_POSI.POSINO "
			+ "AND T_PARTMB.REPRESENTPARTYN=1 "
			+ "ORDER BY T_USER.NAME ASC";
	
	private String getAddressSQL = "SELECT T_USER.NAME, T_PART.PARTNM, T_POSI.POSINM, T_EMP.EMAIL, T_USER.HPNO, T_PARTMB.PARTNO, T_PARTMB.USERNO "
			+ "FROM T_USER, T_PART, T_PARTMB, T_POSI, T_EMP "
			+ "WHERE T_USER.USERNO = T_PARTMB.USERNO "
			+ "AND T_USER.USERNO = T_EMP.USERNO "
			+ "AND T_PARTMB.PARTNO = T_PART.PARTNO "
			+ "AND T_EMP.POSINO = T_POSI.POSINO "
			+ "AND T_PARTMB.REPRESENTPARTYN=1 "
			+ "ORDER BY T_USER.NAME ASC";
	public int doManyAddress(){
		int many=0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try{
			conn = JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(getManyAddressSQL);
			rs = stmt.executeQuery();
			rs.next();
			many=rs.getInt("COUNT(*)");
			System.out.println("Address Many is " + many);
		}catch(Exception e){
			System.out.println("doManyAddress ERROR : " + e);
		}finally{
			JDBCUtilGROUPWARE.close(rs, stmt, conn);
		}
		return many;
	}
	
	public Address[] doGetAddressAll(int many){
		
		Address[] addresses = new Address[many];
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int i=0;
		
		try{
			conn = JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(getAddressSQL);
			rs = stmt.executeQuery();
			while(rs.next()){
				addresses[i] = new Address();
				addresses[i].setName(rs.getString("NAME"));
				addresses[i].setPosition(rs.getString("POSINM"));
				addresses[i].setDept(rs.getString("PARTNM"));
				addresses[i].setMail(rs.getString("EMAIL"));
				addresses[i].setPhoneNumber(rs.getString("HPNO"));
				addresses[i].setPartNo(rs.getString("PARTNO"));
				addresses[i].setUserNo(rs.getString("USERNO"));
				addresses[i].setDept_user("{".concat(addresses[i].getDept().concat("} ").concat(addresses[i].getName())));
				
				i = i + 1;
			}
		}catch(Exception e){
			System.out.println("doGetAddressALL ERROR : " + e);
			
		}finally{
			JDBCUtilGROUPWARE.close(rs, stmt, conn);
		}
		
		return addresses;
		
	}

}
