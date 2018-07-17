package com.capgemini.tcc.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.tcc.bean.PatientBean;
import com.capgemini.tcc.exception.TakeCareClinicException;

public interface IPatientDAO {

	public int addPatientDetails(PatientBean patient)throws TakeCareClinicException;
	public PatientBean getPatientDetails(int patientId)throws TakeCareClinicException;
	ArrayList<String> getPatientIds();
	List<PatientBean> getPatientInfo(String address);
}
