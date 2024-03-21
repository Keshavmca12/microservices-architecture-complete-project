package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeSort implements Comparable<EmployeeSort> {

	private String name;
	private int age;
	private String gender;

	

	public EmployeeSort(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}



	/**
	 * gender - f will first and male after and Age 
	 */
	@Override
	public int compareTo(EmployeeSort o) {
		int nameCompare = this.gender.compareTo(o.gender);
		return nameCompare != 0 ? nameCompare : this.age - o.age;
	}

	
	
	public static void main(String[] args) {
		EmployeeSort employeeSort1 = new EmployeeSort("aa", 30, "F");
		EmployeeSort employeeSort11 = new EmployeeSort("aa", 40, "F");
		EmployeeSort employeeSort2 = new EmployeeSort("bb", 300, "M");
		EmployeeSort employeeSort3 = new EmployeeSort("cc", 305, "F");
		EmployeeSort employeeSort4 = new EmployeeSort("dd", 35, "M");
		List<EmployeeSort> list = new ArrayList<>();
		list.add(employeeSort1);
		list.add(employeeSort2);
		list.add(employeeSort3);
		list.add(employeeSort4);
		list.add(employeeSort11);
		
		System.out.println("List before sorting" + list);
		System.out.println("*****************");
		Collections.sort(list);
		System.out.println("List after sorting" + list);
	}



	@Override
	public String toString() {
		return "EmployeeSort [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	/**
	 * select e1.employeeId, count(*) from Employee e1, Employee e2 
	 * where e1.name = e2.name and e1.age = e2.age
	 * group by  e1.employeeId having count(*) > 1
	 */
	
	

	

}
