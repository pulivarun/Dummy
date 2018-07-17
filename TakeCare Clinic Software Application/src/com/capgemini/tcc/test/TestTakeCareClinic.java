package com.capgemini.tcc.test;

import java.time.LocalDate;
import java.time.Month;





import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.capgemini.tcc.bean.PatientBean;
import com.capgemini.tcc.dao.IPatientDAO;
import com.capgemini.tcc.dao.PatientDAO;
import com.capgemini.tcc.exception.TakeCareClinicException;
import com.capgemini.tcc.service.PatientService;

public class TestTakeCareClinic {
	
	IPatientDAO dao = new PatientDAO();
	
	
//	@Test			//Test to Pass 
	public void testAddDetailsPass() 
	{
		PatientBean patient = new PatientBean();
		patient.setPatient_name("Robb");
		patient.setAge(20);
		patient.setPhone("1234567890");
		patient.setDescription("Cough");
		patient.setConsultation_date(LocalDate.of(2016, Month.SEPTEMBER,20));
		
		int patientId;
		try 
		{
			patientId = dao.addPatientDetails(patient);
			assertEquals(1004, patientId);			//add Next Value of sequence
		} 
		catch (TakeCareClinicException e) 
		{
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
		
	}
	
//	@Test			//Test to Fail
	public void testAddDetailsFail() 
	{
		PatientBean patient = new PatientBean();
		patient.setPatient_name("Ronny");
		patient.setAge(25);
		patient.setPhone("9876543210");
		patient.setDescription("Fever");
		patient.setConsultation_date(LocalDate.of(2016, Month.SEPTEMBER,20));
		
		
		int patientId;
		try 
		{
			patientId = dao.addPatientDetails(patient);
			assertEquals(0, patientId);					//Patient Id is  always generated. So test will fail!
		} 
		catch (TakeCareClinicException e) 
		{
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
	
	// Test to pass. Will expect a Exception on unavailable Patient ID.
		//@Test(expected = TakeCareClinicException.class)
		public void testGetEnquiryDetailsPass() 
		{
			PatientBean patient = new PatientBean();
			try {
				patient = dao.getPatientDetails(1101);
				Assert.assertNotNull(patient);
			} catch (TakeCareClinicException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Test to fail. Will expect a Exception on unavailable Patient ID. But Id exists. So test fails
		//@Test(expected = TakeCareClinicException.class)
		public void testGetEnquiryDetailsFail()  {
			PatientBean patient = new PatientBean();
			try {
				patient = dao.getPatientDetails(1000);
				assertNotNull(patient);
			} catch (TakeCareClinicException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * writing a test case to test wehther 
		 * the validateAge method of the service class
		 * works or not
		 * 
		 * identify successful test case scenarios
		 * identify failure test cases scenarios
		 * 
		 * u will be invoking the service class method
		 * from within the test case class 
		 * 
		 * what coudl be the possible return types 
		 * from a method ?
		 * int		Assert.assertEquals()
		 * String
		 * boolean	Assert.assertTrue;assertFalse
		 * Object eg Employee ; searching for particular employee
		 * Assert.assertNull, assertNotNull
		 * List<Employee>
		 * 
		 */
		@Test
		public void testNegativeAge(){
			
			PatientService service =
					new PatientService();
			boolean actualReturnValue = 
					service.validateAge(-19);
			Assert.assertEquals(false, actualReturnValue);
			//ensure that the method returns false
			//if it does then we say that
			//test case passed
			
			
		}

public void testAgeValueWithinRange(){
			
			PatientService service =
					new PatientService();
			
			service.validateAge(120);
			
			
			
		}

}
