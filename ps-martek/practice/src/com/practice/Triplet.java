package com.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class Triplet {
	
	public static void main(String[] args) {
		int[] data = {12, 3, 4, 1, 6, 9};//sum = 24; 
		int target = 24;
		
		for (int i = 0; i < data.length; i++) {
			for (int j = i+1; j < data.length; j++) {
				for (int j2 = j+1; j2 < data.length; j2++) {
					if(data[i] + data[j] + data[j2] == target) {
						System.out.println(data[i] + " : " + data[j] + " : " +data[j2]);
					}
					
				}
				
			}
		}
		
		getTriplet(data, target);
	}

	private static void getTriplet(int[] data, int target) {
		
		Arrays.sort(data);
		System.out.println("sss "  + Arrays.toString(data));
		int first = data[0];
		
		int start =1 , end = data.length -1;
		System.out.println("first : " + first);
		while(start <= end) {
			int sum = first + data[start] + data[end];
			
			System.out.println("sum : " + sum);
					
			if(sum < target) {
				start++;
			} else if(sum > target) {
				end--;
			} else if(sum == target)  {
				System.out.println(first  + " :: " + data[start] + " : " + data[end]);
				break;
			}
			
			
		}
		
	}

}
