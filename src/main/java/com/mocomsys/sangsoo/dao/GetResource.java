package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import com.mocomsys.sangsoo.vo.Address;
import com.mocomsys.sangsoo.vo.Resource;
import com.mocomsys.sangsoo.vo.Resource2;

public class GetResource {
	
	private String manyResourceSQL = "SELECT count(*) FROM T_RESRC_RENT "
			+"WHERE STARTDATE between to_date('2016-07-01','YYYY-MM-DD') and to_date('2016-07-28','YYYY-MM-DD') and RESRCNO=?";
	private String getResourceSQL = "SELECT * FROM T_RESRC_RENT "
			+"WHERE STARTDATE between to_date('2016-07-01','YYYY-MM-DD') and to_date('2016-07-28','YYYY-MM-DD') and RESRCNO=?";
	
	private String getAllStateResourceSQL  = "SELECT RESRCNO FROM T_RESRC_RENT WHERE STARTDATE=to_date(?, 'YYMMDDHH24MISS')";
	
	private String manyAllStateResourceSQL  = "SELECT count(*) FROM T_RESRC_RENT WHERE STARTDATE=to_date(?, 'YYMMDDHH24MISS')";

	private String bookResourceSQL = "insert into T_RESRC_RENT(COMPNO, RESRCNO, TITLE, STARTDATE, ENDDATE, STATUS, REGDATE, WRITER, RESER, "
			+ "RESPARTNO, RESUSERNO, REPEATTYPE, RENTNO) "
			+ "VALUES('1000', ?, ?, to_date(?, 'YYMMDDHH24MISS'), to_date(?, 'YYMMDDHH24MISS'), 2, ?, ?, ?, ?, ?, 1, ?)";
	
	private String maxRENTNOSQL = "SELECT MAX(RENTNO) FROM T_RESRC_RENT";
	String today = null;
	String beforeWeekend = null;
	
	public int doGetManyResource(int roomNo){
		int many=0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		
		try {
			conn=JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(manyResourceSQL);
			stmt.setInt(1, roomNo);
			//stmt.setString(2, beforeWeekend);
			rst = stmt.executeQuery();
			rst.next();
			many = rst.getInt("COUNT(*)");
			System.out.println("getResource is " + many);
			
		} catch (Exception e) {
			System.out.println("doGetManyResource ERROR : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtilGROUPWARE.close(rst, stmt, conn);
		}
		return many;
		
	}
	public Resource[] doGetResource(int roomNo, int many){
		
		Resource[] resources = new Resource[many];
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(getResourceSQL);
			stmt.setInt(1, roomNo);
			rs = stmt.executeQuery();
			int i=0;
			while(rs.next()){
				resources[i]= new Resource();
				resources[i].setCompNo(rs.getInt("COMPNO"));
				resources[i].setResourceNo(rs.getInt("RESRCNO"));
				resources[i].setRentNo(rs.getInt("RENTNO"));
				resources[i].setTitle(rs.getString("TITLE"));
				resources[i].setStatus(rs.getInt("STATUS"));
				resources[i].setRegDate(rs.getString("REGDATE"));
				resources[i].setStartTime(rs.getString("STARTDATE"));
				resources[i].setEndTime(rs.getString("ENDDATE"));
				resources[i].setWriter(rs.getString("WRITER"));
				resources[i].setReserver(rs.getString("RESER"));
				resources[i].setReserverPartNo(rs.getString("RESPARTNO"));
				resources[i].setReserverUserNo(rs.getString("RESUSERNO"));
				resources[i].setRepeatType(rs.getInt("REPEATTYPE"));
				
				i = i + 1;
			}
			/*
			for(int j=0; j<many; j++){
				System.out.println("resources" + j + " : " + resources[j].getCompNo() + ", " + resources[j].getResourceNo() + ", " + 
						resources[j].getRentNo() + ", " + resources[j].getRegDate() + ", " + resources[j].getWriter() + ", " +
						resources[j].getReserver() + ", " + resources[j].getReserverPartNo() + ", " + resources[j].getReserverUserNo());
			}
			*/		
		} catch (Exception e) {
			System.out.println("doGetResource ERROR : " + e);

			e.printStackTrace();
		} finally {
			JDBCUtilGROUPWARE.close(rs, stmt, conn);
		}
		return resources;
	}
	public String doBookResource(Address address, int roomNo, String name, String title, String startDate, String endDate, String regDate){
		String result = "false";
		System.out.println("doBookResource!!!");
		System.out.println("received data : " + roomNo + ", " + name);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int RENTNO = 0;

		
		try {
			conn = JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(maxRENTNOSQL);
			rs = stmt.executeQuery();
			if(rs.next()){
				RENTNO = rs.getInt("MAX(RENTNO)");
			}
		}catch(Exception e){
			System.out.println("max ERROR : " + e);
		}
		finally{
			JDBCUtilGROUPWARE.close(rs, stmt, conn);
			System.out.println("max end");
		}
		try{
			System.out.println("RENTNO : " + RENTNO);
			conn = JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(bookResourceSQL);
			stmt.setInt(1, roomNo);
			stmt.setString(2, title);
			stmt.setString(3, startDate);
			stmt.setString(4, endDate);
			stmt.setString(5, regDate);
			stmt.setString(6, address.getDept_user());
			stmt.setString(7, address.getDept_user());
			stmt.setString(8, address.getPartNo());
			stmt.setString(9, address.getUserNo());
			stmt.setInt(10, ++RENTNO);
			if(stmt.executeUpdate()!=0){
				result="success";
			}else{
				result="fail";
			}
			
		}catch(Exception e){
			System.out.println("doBookResource ERROR : " + e);
		}finally {
			JDBCUtilGROUPWARE.close(stmt, conn);
			System.out.println("BookResource result : " + result);
		}
		return result;
	}

	public int doGetManyAvailable(String time){
		int many=0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		
		try {
			conn=JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(manyAllStateResourceSQL);
			stmt.setString(1, time);
			rst = stmt.executeQuery();
			rst.next();
			many = rst.getInt("COUNT(*)");
			System.out.println("getResource is " + many);
			
		} catch (Exception e) {
			System.out.println("doGetManyResource ERROR : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtilGROUPWARE.close(rst, stmt, conn);
		}
		return many;
		
	}
	public Resource2[] getAllStateResource(String startTime){
		
		int many = doGetManyAvailable(startTime);
		
		Resource2[] resources = new Resource2[many];
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtilGROUPWARE.getConnection();
			stmt = conn.prepareStatement(getAllStateResourceSQL);
			stmt.setString(1, startTime);
			rs = stmt.executeQuery();
			int i=0;
			while(rs.next()){
				resources[i]= new Resource2();

				resources[i].setResourceNo(rs.getString("RESRCNO"));
				
				i = i + 1;
			}
		} catch (Exception e) {
			System.out.println("doGetResource ERROR : " + e);

			e.printStackTrace();
		} finally {
			JDBCUtilGROUPWARE.close(rs, stmt, conn);
		}
		return resources;
	}
	
}
