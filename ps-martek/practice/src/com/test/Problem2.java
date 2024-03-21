package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem2 {

	/**
	 * Given an array of strings, group the anagrams together Input: strs =
	 * ["eat","tea","tan","ate","nat","bat","tab"] Output:
	 * [["bat","tab"],["nat","tan"],["ate","eat","tea"]]
	 */

	public static void main(String[] args) {
		List<String> stringList = new ArrayList<>();
		stringList.add("eat");
		stringList.add("tea");
		stringList.add("tan");
		stringList.add("ate");
		stringList.add("nat");
		stringList.add("bat");
		stringList.add("tab");
		System.out.println("stringList : " + stringList);
		// Collections.sort(stringList);
		
		//Arrays.sort(stringList.get(0).toCharArray());
		
		

		Map<String, List<String>> anagramMap = new HashMap<>();
		for (int i = 0; i < stringList.size(); i++) {
			String firstString = stringList.get(i);

			for (int j = 0; j < stringList.size(); j++) {
				if (i != j) {
					String secondString = stringList.get(j);
					char[] chararray1 = firstString.toCharArray();
					Arrays.sort(chararray1);
					
					char[] chararray2 = secondString.toCharArray();
					Arrays.sort(chararray2);
				//	System.out.println("chararray1 : " + Arrays.toString(chararray1));
				//	System.out.println("chararray2 : " + Arrays.toString(chararray2));
					if (String.valueOf(chararray1).equals(String.valueOf(chararray2))) {
						String key = String.valueOf(chararray1);
						List<String> list = anagramMap.get(key);
						if (!anagramMap.containsKey(key)) {
							list = new ArrayList<>();
						}

						list.add(secondString);
						anagramMap.put(key, list);
					}

				}

			}
		}
		
		
		System.out.println(anagramMap);

	}

}
