package com.cg.ea.service;

import com.cg.ea.beans.Employee;

public interface IEmployeeService {

	boolean checkName(String name);

	boolean checkSalary(String salary);

	boolean checkDesignation(String name);

	int add(Employee employee);

}
