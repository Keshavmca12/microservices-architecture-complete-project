package stream;

import java.util.Arrays;
import java.util.List;

public class StreamCount {
	
	
		// Driver code 
		public static void main(String[] args) 
		{ 

			// creating a list of Integers 
			List<Integer> list = Arrays.asList(0, 2, 4, 6, 
											8, 10, 12); 

			// Using count() to count the number 
			// of elements in the stream and 
			// storing the result in a variable. 
			long total = list.stream().count() + list.stream().count(); 

			// Displaying the number of elements 
			System.out.println(total); 
		} 


}
