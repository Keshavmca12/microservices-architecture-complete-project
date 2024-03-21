package com.practice;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		System.out.println("Hello world");
		
		String input1 = "sTate";
		String input2 = "tasTE";
		
		char[] input1Array = input1.toCharArray();
		Arrays.sort(input1Array);
		
		char[] input2Array = input2.toCharArray();
		Arrays.sort(input2Array);
		
		System.out.println(String.valueOf(input1Array));
		
		if(String.valueOf(input1Array).equalsIgnoreCase(String.valueOf(input1Array))) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
	}
}
