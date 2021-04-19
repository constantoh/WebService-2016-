package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mocomsys.sangsoo.vo.Site;

public class GetSite {

	String getManySiteSQL = "SELECT COUNT(*) FROM SITE";
	String getAllSiteSQL = "SELECT * FROM SITE";
	
	
	public Site[] doGetAllSiteInfo(int many){
		Site[] sites = new Site[many];
		//System.out.println("#### many is " + many);
		int i=0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		
		try{
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getAllSiteSQL);
			rst = stmt.executeQuery();
			while(rst.next()){
				sites[i] = new Site();
				sites[i].setSite_UID(rst.getInt("SITE_UID"));
				sites[i].setCustomer_UID(rst.getInt("CUSTOMER_UID"));
				sites[i].setSite_Name(rst.getString("SITE_NAME"));
				sites[i].setSite_state_UID(GetSite.siteStatusString(rst.getInt("SITE_STATE_UID")));
				sites[i].setCharge_Dept(rst.getString("CHARGE_DEPT"));
				sites[i].setNote((rst.getString("NOTE")==null)? "" : rst.getString("NOTE"));
				
				i = i + 1;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("GetAllSite ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		return sites;
		
	}
	
	public int doGetManySite() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		int many=0;
		try{
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getManySiteSQL);
			rst = stmt.executeQuery();

			rst.next();
			many=rst.getInt("COUNT(*)");
			System.out.println("FIND MANY IS " + many);
			
		}catch(Exception e){
			System.out.println("GetManySite ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		
		return many;	
	}
	
	public static String siteStatusString(int status){
		switch (status) {
		case 1:
			return "이행 예정";
		case 2:
			return "이행 중";
		case 3:
			return "인수인계 진행";
		case 4:
			return "무상-유지보수";
		case 5:
			return "유상-유지보수";
		case 6:
			return "만료";
		case 7:
			return "종료";
		case 8:
			return "미계약";
		case 9:
			return "ps제안";
		case 10:
			return "ps실패";
		default:
			return "error";
		}
	}
}
