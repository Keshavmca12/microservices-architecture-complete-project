package com.practice;

public class UniqueCharacter {

	
	public static void main(String[] args) {
		
		String inputString ="aaabbbcabddaabcaaacccabbbdab";
		String outputString = "cabbcada";
		
		
		StringBuilder testStr = new StringBuilder();
		
		String matchStr = String.valueOf(inputString.charAt(0));
		for (int i = 1; i < inputString.length(); i++) {
			String str = String.valueOf(inputString.charAt(i));
		//	String str1 = String.valueOf(inputString.charAt(i));
			if(!matchStr.equals(str)) {
				System.out.println(str);
				matchStr = str;
				testStr.append(str);
			}
		}
		
		System.out.println(testStr.toString());
		
	}
	
}
