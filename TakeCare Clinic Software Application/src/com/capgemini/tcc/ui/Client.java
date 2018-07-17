package com.capgemini.tcc.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.tcc.bean.PatientBean;
import com.capgemini.tcc.exception.TakeCareClinicException;
import com.capgemini.tcc.service.IPatientService;
import com.capgemini.tcc.service.PatientService;

public class Client {
	
	//main class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int choice,age;
		String name,phone;
		boolean flag,flag1,flag2;
		LocalDate ConsultDate=null;
		Scanner sc = new Scanner(System.in);
		//try with resource
		try
		{
			PatientService service = new PatientService();
			int test=10;
			do											//do-while loop
			{
				System.out.println("\n************* TakeCare Clinic *************");
				System.out.println("Choose an operation");
				System.out.println("1. Add Patient Information");
				System.out.println("2. Search Patient by Id");
				System.out.println("3. Exit");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				choice = sc.nextInt();
				System.out.println("\n******************************");
				
				switch(choice)
				{
				
				
					case 1 :// Add patient Information
						{
							int patientId;
							//Patient Name
							do
							{
								int x=10;
								System.out.println("Enter the name of the Patient");
								name=sc.next();
								flag=service.validateName(name);
								if(flag==false)
									System.out.println("Name should be entered in proper format(eg.Matt)");	
							}while(flag==false);
							
						//	x=90;
							//Patient Age
							do
							{
							System.out.println("Enter Patient age : ");
							age = sc.nextInt();
							flag1=PatientService.validateAge(age);
							if(flag1==false)
								System.out.println("Age should be below 110");
							}while(flag1==false);
							
							//Patient Phone Number
							do
							{
								System.out.println("Enter Patient phone number : ");
								phone = sc.next();
								flag2=PatientService.validateContact(phone);
								if(flag2==false)
									System.out.println("Phone number should be of 10 digits");
							}while(flag2==false);
							
							//Description
							System.out.println("Enter Description : ");
							String des = sc.next();
							
						//	ArrayList patientIds = service.getPatientIds();
							
						//	System.out.println("Enter mobile id");
	//						String mobileid=scan.next();
							
		//					patientIds.contains(patientId);//valueentered byuser)
							
						//	int num=10;
							PatientBean patient = new PatientBean();
							
							patient.setPatient_name(name);
							patient.setAge(age);
							patient.setPhone(phone);
							patient.setDescription(des);
							patient.setConsultation_date(ConsultDate);
							
							try
							{
								//calling addPatientDetails method
								patientId=service.addPatientDetails(patient);
								System.out.println("Succesfull");
								System.out.println("Patient Information stored succesfully for "
								+patientId);
								
							}
							catch(TakeCareClinicException e)
							{
								System.out.println(e.getMessage());
							
							}
							break;
						}
						
				case 2 ://search patient info through on patient id
					{
						PatientBean patient = new PatientBean();
						System.out.print("\nEnter Patient Id : ");
						int patientId = sc.nextInt();
						
						try 
						{
							//calling getPatientDetails method
							patient = service.getPatientDetails(patientId);
							System.out.println("----------------------------------------------------------");
							System.out.println("Patient Id : "+patient.getPatient_id());
							System.out.println("Name : "+patient.getPatient_name());
							System.out.println("Age : "+patient.getAge());
							System.out.println("Phone No : "+patient.getPhone());
							System.out.println("Description : "+patient.getDescription());
							System.out.println("Consultation Date :"+patient.getConsultation_date());
							System.out.println("----------------------------------------------------------");
						}
						catch (TakeCareClinicException e) 
						{
						if(e.getMessage().contains("table")){
							System.out.println("technical issue with application ");
						}
						else
							System.out.println("There is no patient with this ID");
						}
					
						break;
					}
							
				}
				System.out.print("Do you want to continue? 1-Yes  0 - No : ");
				choice = sc.nextInt();
				
			}while(choice==1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		

	}

}
