package com.cg.ea.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.ea.beans.Employee;
import com.cg.ea.dao.EmployeeDaoImpl;
import com.cg.ea.dao.IEmployeeDao;

public class EmployeeServiceImpl implements IEmployeeService{
	IEmployeeDao iEmployeeDao=new EmployeeDaoImpl();
String namePattern="[A-Z]{1}[a-zA-Z]{1,19}";
String salaryPattern="\\d+[.]{1}[0-9]{2}";
	@Override
	public boolean checkName(String name) {
	boolean result=false;
	Pattern pattern=Pattern.compile(namePattern);
	Matcher matcher=pattern.matcher(name);
	if(matcher.matches())
		result=true;
		return result;
	}
	@Override
	public boolean checkSalary(String salary) {
		boolean result=false;
		Pattern pattern=Pattern.compile(salaryPattern);
		Matcher matcher=pattern.matcher(salary);
		if(matcher.matches())
			result=true;
			return result;
	}
	@Override
	public boolean checkDesignation(String name) {
		boolean result=false;
		Pattern pattern=Pattern.compile(namePattern);
		Matcher matcher=pattern.matcher(name);
		if(matcher.matches())
			result=true;
			return result;
	}
	@Override
	public int add(Employee employee) {
		
		return iEmployeeDao.add(employee);
	}

}
