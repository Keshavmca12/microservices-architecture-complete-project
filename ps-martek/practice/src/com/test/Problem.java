package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
	
	/*
	 * 
	 * 10,15,8,49,25,98,98,32,15 Print all the duplicate elements in a given integers
	 *  list in java using Stream output - 15,15,98,98(print all the occurrence of duplicate elements)

	 */
	
	public static void main(String[] args) {
		List<Integer> numberList = new ArrayList<>();
		numberList.add(10);
		numberList.add(10);
		numberList.add(30);
		numberList.add(20);
		numberList.add(30);
		numberList.add(40);
		
		Map<Integer, Integer> numMap = new HashMap<>();
		
		for (Integer data : numberList) {
			int count = 0;
			if(!numMap.containsKey(data)) {
				count = 1;
			} else {
				count = numMap.get(data) + 1;
			}
			numMap.put(data, count);
		}
		
	//System.out.println("num map " + numMap);
		//System.out.println("Key " + key + " val : " + numMap.get(key))
		
		numMap.keySet().stream().filter(key -> numMap.get(key) > 1)
		.forEach(key -> printOcccurence(key, numMap) 
		);
		
	}
	
	private static void printOcccurence(int key, Map<Integer, Integer> numMap) {
		//System.out.println("num map " + numMap);
		int count = numMap.get(key);
		for (int i = 0; i < count; i++) {
			System.out.println(key);
		}
	}

}
