package com.cg.ea.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.ea.beans.Employee;
import com.cg.ea.util.DButil;

public class EmployeeDaoImpl implements IEmployeeDao {

	Connection con=null;
	public EmployeeDaoImpl() {
		con=DButil.getConnection();
	}
	@Override
	public int add(Employee employee) {
		int id=getPatientId();
		
		try {
			String sql="insert into employee values (?,?,?,?)";
			PreparedStatement ptst=con.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2,employee.geteName() );
			ptst.setDouble(3, employee.getSalary());
			ptst.setString(4,employee.getDesignation());
			ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
			}
	private int getPatientId() 
	{
		// TODO Auto-generated method stub
		int patientId = 0;
		String sql = "SELECT Patient.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement pstmt= con.prepareStatement(sql);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
				{
					patientId = res.getInt(1);
				}
		}
		catch(SQLException e)
		{
			
			System.out.println(e.getMessage());
		}
		return patientId;		
		
	}
	
}
