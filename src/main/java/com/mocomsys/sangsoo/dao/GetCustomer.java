package com.mocomsys.sangsoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mocomsys.sangsoo.vo.Customer;

public class GetCustomer {
	
	private String getAllCustomerSQL = "SELECT * FROM CUSTOMER";
	private String getManyCustomerSQL = "SELECT COUNT(*) FROM CUSTOMER";
	
	//int many=0;

	
	public Customer[] doGetAllCustomerInfo(int many){
		Customer[] customers = new Customer[many];
		System.out.println("customer many is " + many);
		int i=0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		
		try{
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getAllCustomerSQL);
			rst = stmt.executeQuery();
			while(rst.next()){
				customers[i] = new Customer();
				customers[i].setCustomer_UID(rst.getInt("CUSTOMER_UID"));

				customers[i].setCustomer_NAME(rst.getString("CUSTOMER_NAME"));
				if(rst.getString("BUSINESS_CATEGORY")!= null)
					customers[i].setBusiness_CATEGORY(rst.getString("BUSINESS_CATEGORY"));
				else
					customers[i].setBusiness_CATEGORY("");

				if((Integer)rst.getObject("GRADE")!= null)
					customers[i].setGrade(rst.getInt("GRADE"));
				else
					customers[i].setGrade(null);

				if(rst.getString("SALES_PERSON")!= null)
					customers[i].setSales_Person(rst.getString("SALES_PERSON"));
				else
					customers[i].setSales_Person("");

				i = i + 1;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("GetAllCustomer ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		return customers;
		
	}

	public int doGetManyCustomer() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		int many=0;
		try{
			conn = JDBCUtilWTS.getConnection();
			stmt = conn.prepareStatement(getManyCustomerSQL);
			rst = stmt.executeQuery();

			rst.next();
			many=rst.getInt("COUNT(*)");
			System.out.println("FIND MANY IS " + many);
			
		}catch(Exception e){
			System.out.println("GetManyCustomer ERROR : " + e);
		}finally {
			JDBCUtilWTS.close(rst, stmt, conn);
		}
		
		return many;	
	}

}
