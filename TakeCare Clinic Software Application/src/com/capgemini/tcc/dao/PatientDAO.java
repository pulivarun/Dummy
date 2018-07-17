package com.capgemini.tcc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.tcc.bean.PatientBean;
import com.capgemini.tcc.exception.TakeCareClinicException;
import com.capgemini.tcc.logger.DaoLogger;
import com.capgemini.tcc.util.DBUtil;

public class PatientDAO implements IPatientDAO{
	
	Connection con = null;
	public static Logger log= DaoLogger.log; 				//Reference of logger class pointing to Daologger log
	
	public PatientDAO() {//throws TakeCareClinicException {
		// TODO Auto-generated constructor stub
		try {
			con = DBUtil.getConnect();
		} catch (TakeCareClinicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					//Establishing connection
	}
	
	

	//Adding New Entries
	public int addPatientDetails(PatientBean patient) throws TakeCareClinicException {
		// TODO Auto-generated method stub
		
		int patientId = 0;
		try
		{
				patientId = getPatientId();			//Getting Unique Patient Id from Sequence
				String sql = "INSERT INTO Patient VALUES(?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, patientId);
				pstmt.setString(2, patient.getPatient_name());
				pstmt.setInt(3, patient.getAge());
				pstmt.setString(4, patient.getPhone());
				pstmt.setString(5, patient.getDescription());
				
				LocalDate today = LocalDate.now();
				Date sqlDate = Date.valueOf(today); //to convert todays local date to sql date
				
				pstmt.setDate(6, sqlDate);
				
				int row = pstmt.executeUpdate();
				if(row > 0)
				{
					

					//Logging the New Entry
					log.info("New Entry -> Patient ID : "+patientId
										+"\nName : "+patient.getPatient_name()
										+"\nAge : "+patient.getAge()
										+"\nPhone : "+patient.getPhone()
										+"\nDescription : "+patient.getDescription()
										+"\nConsultation Date : "+sqlDate);
				}
				else
				{
					log.error("System Error");
					throw new TakeCareClinicException("System Error. Try Again Later.");
					
				}
		}
		catch(SQLException e)
		{
			log.error(e.getMessage());
		}
	
		return patientId;
	}
	
	
	
	
	//Sequence to fetch next value of Patient ID
		private int getPatientId() 
		{
			// TODO Auto-generated method stub
			int patientId = 0;
			String sql = "SELECT Patient_Id_Sequence.NEXTVAL FROM DUAL";
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
				
				log.error(e.getMessage());
			}
			return patientId;		
			
		}
		
		
		// Getting Details on Entered Id
		public PatientBean getPatientDetails(int patientId)	throws TakeCareClinicException {
			// TODO Auto-generated method stub
			
			PatientBean patient = new PatientBean();
			if(validPatientID(patientId))			// Checking if Patient id Exists
			{
				try
				{
						String sql = "SELECT * FROM Patient WHERE patient_id = ?";
						PreparedStatement pstmt = con.prepareStatement(sql);	
						pstmt. setInt(1, patientId);
						ResultSet res = pstmt.executeQuery();
						if(res.next())
						{
							patient.setPatient_id(res.getInt(1));
							patient.setPatient_name(res.getString(2));
							patient.setAge(res.getInt(3));
							patient.setPhone(res.getString(4));	
							patient.setDescription(res.getString(5));
							
							Date sqlDate = res.getDate(6);
							LocalDate date = sqlDate.toLocalDate();

							patient.setConsultation_date(date);
							
							//Logging the Searched Entry
							log.info("New Entry -> Patient ID : "+patientId
												+"\nName : "+patient.getPatient_name()
												+"\nAge : "+patient.getAge()
												+"\nPhone : "+patient.getPhone()
												+"\nDescription : "+patient.getDescription()
												+"\nConsultation Date : "+sqlDate);
						}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					log.error(e.getMessage());
					throw new TakeCareClinicException(e.getMessage());
				}
			}
			else
			{
				log.error("No Details available for Patient Id : "+patientId);
				throw new TakeCareClinicException("\nSorry No Details Found.");
			}
			
			log.info(patient);
			return patient;
		}
		
		
		
		//Check if Patient Id exists in database
		private boolean validPatientID(int patientId)
		{
			// TODO Auto-generated method stub
			boolean flag = false;
			String query = "SELECT * FROM Patient WHERE patient_id = ?";
			try
			{
				PreparedStatement pstmt = con.prepareStatement(query);	
				pstmt. setInt(1, patientId);
				ResultSet res = pstmt.executeQuery();
				if(res.next())
					flag = true;
			}
			catch(SQLException e)
			{
				log.error(e.getMessage());
			}
			return flag;
		}



		@Override
		public ArrayList<String> getPatientIds()  {
			String query= "SELECT patient_id FROM patient";
			ArrayList patientsIds= new ArrayList(); 
			Statement stat;
			try {
				stat = con.createStatement();
				ResultSet rset= stat.executeQuery(query);
				while(rset.next()){
					String patientId = rset.getString(1);
					patientsIds.add(patientId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return patientsIds;
		}



		@Override
		public List<PatientBean> getPatientInfo(String address) {
			String query= "SELECT * FROM patient where address='"+address+"'";
			ArrayList patients= new ArrayList(); 
			Statement stat;
			try {
				stat = con.createStatement();
				ResultSet rset= stat.executeQuery(query);
			
				while(rset.next()){
			
					PatientBean patient = new PatientBean();
					int patientId = rset.getInt(1);
					patient.setPatient_id(patientId);
					patient.setPatient_name(rset.getString(2));
					String patientAge = rset.getString(3);
					String patientPhoneNumber = rset.getString(4);
					patients.add(patient);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return patients;
		}
}
