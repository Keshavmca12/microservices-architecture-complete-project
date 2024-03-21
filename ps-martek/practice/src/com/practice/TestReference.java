package com.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestReference {
	
	public static void main(String[] args) {
		TestObjectByReference tByReference = new TestObjectByReference(0, "main", "type", "address");
		System.out.println("reference before call " + tByReference.hashCode());
		
		System.out.println("name " + tByReference.getName());
		int data = -1;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		new TestReference().test1(tByReference, data);
		System.out.println("list before : " + list);
		testList(list);
		System.out.println("list after : " + list);
		System.out.println("reference after call " + tByReference.hashCode());
		
		System.out.println(" after name:   ::  " + tByReference.getName());
		System.out.println("data : " + data);
		
		
		
	}
	
	
	private static void testList(List<Integer> list) {
		list.add(2);
		list = new  ArrayList<>();
		System.out.println("list in method after reinit : " + list);
		
	}


	private void test1(TestObjectByReference tByReference, int data) {
		System.out.println(tByReference.hashCode());
		System.out.println(" data 1 " + data);
		data = 50;
		tByReference.setName("name 1");
		test2(tByReference, data);
		Collections.sort(null);
		
		System.out.println(" data 1 after " + data);
	}
	
	
	private void test2(TestObjectByReference tByReference, int data) {
		System.out.println(tByReference.hashCode());
		System.out.println(" data 2 :" + data);
		data = 60;
		tByReference.setName("name 2");
	}


}
