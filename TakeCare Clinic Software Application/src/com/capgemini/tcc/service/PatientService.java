package com.capgemini.tcc.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.tcc.bean.PatientBean;
import com.capgemini.tcc.dao.IPatientDAO;
import com.capgemini.tcc.dao.PatientDAO;
import com.capgemini.tcc.exception.TakeCareClinicException;

public class PatientService implements IPatientService {
	
	//PatientService class has a property
	//called dao
	//one class having properties as other Class 
IPatientDAO dao; // example of has a relation 
	
	public PatientService(){// throws TakeCareClinicException {
		// TODO Auto-generated constructor stub
		dao =new PatientDAO();
	}
	

	public IPatientDAO getDao() {
		return dao;
	}



	public void setDao(IPatientDAO dao) {
		this.dao = dao;
	}
	
	static String namePattern = "[A-Z]{1}[a-z]{2,}";		//name format pattern
	static String contactPattern = "[0-9]{10}";				//phone no format pattern


	@Override
	//public int addPatientDetails(String patientName,String description, int age,String phoneNumber)
	public int addPatientDetails(PatientBean patient) throws TakeCareClinicException {
		// TODO Auto-generated method stub
		return dao.addPatientDetails(patient);
	}


	@Override
	public PatientBean getPatientDetails(int patientId)
			throws TakeCareClinicException {
		// TODO Auto-generated method stub
		return dao.getPatientDetails(patientId);
	}

	
	//validating input name format
	public static boolean validateName(String name)
	{
		boolean flag = false;
		if(name.matches(namePattern))
		{
			flag = true;
		}
		
		
		return flag;
	}
	
	//validating age
	public static boolean validateAge(int Age)
	{
		boolean flag = false;
		if(Age>1 && Age<=110)
		{
			flag = true;
		}
		return flag;
	}
	
	//validating input phone number format
	public static boolean validateContact(String contact)
	{
		boolean flag = false;
		if(contact.matches(contactPattern))
		{
			flag = true;
		}
		return flag;
	}


	@Override
	public ArrayList<String> getPatientIds() {
		
		return dao.getPatientIds();
	}


	@Override
	public List<PatientBean> getPatientInfo(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
