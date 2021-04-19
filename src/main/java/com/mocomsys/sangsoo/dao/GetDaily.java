package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import com.mocomsys.sangsoo.vo.Daily;

public class GetDaily {
	
	private String getManyDailySQL = "select count(*) FROM emp, daily_report where emp.EMP_UID = daily_report.EMP_UID "
			+ "and daily_report.REG_DATE BETWEEN ? AND ? and daily_report.DEPT_UID=? and (emp.USERID=? or emp.LEVEL < ?)";

	
	private String getDailySQL ="select * FROM emp, daily_report where emp.EMP_UID = daily_report.EMP_UID "
			+ "and daily_report.REG_DATE BETWEEN ? AND ? and daily_report.DEPT_UID=? and (emp.USERID=? or emp.LEVEL < ?)";
	
	public int doGetManyDaily(String id, Integer level, Integer dept){
		System.out.println("***dogetManyDaily***");
		String [] today  = GetToday();
		//System.out.println("************************************" + today[0] + " , " +  today[1]);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		int many=0;
		try{
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getManyDailySQL);
			stmt.setString(1, today[0]);
			stmt.setString(2, today[1]);
			stmt.setInt(3, dept.intValue());
			stmt.setString(4, id);
			stmt.setInt(5, level.intValue());

			rst = stmt.executeQuery();
			
			rst.next();
			many=rst.getInt("COUNT(*)");
			//System.out.println("FIND MANY IS " + many);
			
		}catch(Exception e){
			System.out.println("doGetManyDaily ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		return many;
	}
	
public Daily[] doGetDaily(int many, String id, Integer level, Integer dept){
		//System.out.println("***dogetDaily***");

		String [] today  = GetToday();
	
		System.out.println("doGetDaily user_id : " + id);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Daily[] dailes = new Daily[many];
		int i=0;
		try{
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getDailySQL);
			stmt.setString(1, today[0]);
			stmt.setString(2, today[1]);
			stmt.setInt(3, dept.intValue());
			stmt.setString(4, id);
			stmt.setInt(5, level.intValue());
			
			rst = stmt.executeQuery();
			
			while(rst.next()){
				dailes[i] = new Daily();
				dailes[i].setLevel(rst.getInt("LEVEL"));
				dailes[i].setName(rst.getString("name"));
				dailes[i].setCo_req_dept_UID(rst.getInt("CO_REQ_DEPT_UID"));
				dailes[i].setReg_DATE(rst.getString("REG_DATE"));
				dailes[i].setNote(rst.getString("note"));
				i = i + 1;
			}
		}catch(Exception e){
			System.out.println("doGetManyDaily ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		return dailes;
	}
	public static final String[] GetToday(){
		String[] today = new String[2];
		today[0] = "";
		today[1] = "";
		
		int year =  Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int date;
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			date=31;
		}else if(month==2){
			date=28;
			if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				date=29;
		}else{
			date=30;
		}
		today[0] = Integer.toString(year) + String.format("%02d", month) + String.format("%02d", 1);
		today[1] = Integer.toString(year) + String.format("%02d", month) + String.format("%02d", date);

		//System.out.println("today [0], [1] : " + today[0] + " " + today[1]);
		return today;
	}

}
