package com.capgemini.tcc.bean;

import java.time.LocalDate;

public class PatientBean {
//
	private int patient_id;
	//Patient has a name
	private String patient_name;
	private int age;
	private String phone;
	private String description;
	LocalDate consultation_date;
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getConsultation_date() {
		return consultation_date;
	}
	public void setConsultation_date(LocalDate consultation_date) {
		this.consultation_date = consultation_date;
	}
	
	
	
	
	
}
