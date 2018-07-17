package com.cg.ea.pl;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.ea.beans.Employee;
import com.cg.ea.service.EmployeeServiceImpl;
import com.cg.ea.service.IEmployeeService;

public class EmployeeMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		IEmployeeService iEmployeeService=new EmployeeServiceImpl();
	Scanner scanner=new Scanner(System.in);
	System.out.println("1) Add Employee \n2) display employee \n3)delete employee \n4) exit");
	int choice;
	do
	{
	System.out.println("enter your option");
	choice=scanner.nextInt();
	switch(choice)
	{
	case 1:
		try
		{
		String name,designation;
		String salary;
		int id=0;
		Employee employee=new Employee();
		boolean flag;
		do
		{
		System.out.println("enter name");
		 name=scanner.next();
		flag=iEmployeeService.checkName(name);
		}while(flag=false);
		employee.seteName(name);
		do
		{
		System.out.println("enter salary");
		 salary=scanner.next();
		flag=iEmployeeService.checkSalary(salary);
		}while(flag=false);
		employee.setSalary(Double.parseDouble(salary));
		
		System.out.println("enter designation");
		 designation=scanner.next();
		employee.setDesignation(designation);
		 id=iEmployeeService.add(employee);
		 if(id!=0)
			 System.out.println("employee created with id"+id);
		 else
			 System.out.println("employee not created");
		}
		catch(InputMismatchException exception)
		{
		System.out.println("enter valid data");
		}
		break;
	case 2:
		
	}
	}while(choice!=4);
	}

}
