package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mocomsys.sangsoo.vo.Emp;

public class GetEmp {
	
	private String getEmpSQL = "SELECT COUNT(*) FROM EMP WHERE IS_ENABLE=1";
	private String getEmpInfo = "SELECT emp.EMP_UID, USERID, NAME, work.level, DEPT_UID FROM emp, work "
			+ "WHERE emp.EMP_UID=work.EMP_UID AND work.END_DATE IS NULL";
	
	public int doGetManyEmp(){
		System.out.println("***dogetManyEmp***");

		int many=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		
		try {
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getEmpSQL);
			rst = stmt.executeQuery();
			rst.next();
			many = rst.getInt("COUNT(*)");
			System.out.println("getEmp Many is " + many);
		} catch (Exception e) {
			System.out.println("GetManyEmp ERROR : " + e);
		} finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		return many;
	}
	
	public Emp[] doEmpInfo(int many){
		System.out.println("doEmpInfo");
		Emp[] emps = new Emp[many];
		int i=0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		
		try {
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getEmpInfo);
			rst = stmt.executeQuery();
			while(rst.next()){
				emps[i] = new Emp();
				emps[i].setEmp_UID(rst.getInt("emp_uid"));
				emps[i].setEmp_ID(rst.getString("USERID"));
				emps[i].setEmp_level(rst.getInt("LEVEL"));
				emps[i].setEmp_name(rst.getString("NAME"));
				emps[i].setDept_UID(rst.getInt("DEPT_UID"));
				
				i = i + 1;
			}
		}catch(Exception e){
			System.out.println("DoEmpInfo ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		return emps;
	}
}
