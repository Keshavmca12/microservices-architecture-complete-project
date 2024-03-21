package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.pojo.Employee;

public class StreamWriter {

	public static void main(String[] args) {
		convertListToMap();
		findNonrepeatedCharacterInString();
	}

	private static void convertListToMap() {
		List<Employee> empList = new ArrayList<>();

		empList.add(new Employee(1, "Keshav", 13.2));
		empList.add(new Employee(2, "Keshav1", 213.2));
		empList.add(new Employee(3, "Keshav2", 323.2));
		empList.add(new Employee(4, "Keshav3", 423.2));

		Map<Integer, String> idNameMap = new HashMap<>();
		idNameMap = empList.stream().collect(Collectors.toMap(emp -> emp.getId(), emp1 -> emp1.getName()));
		System.out.println("idNameMap  : " + idNameMap);

		Map<Integer, Employee> idEmployeeMap = new HashMap<>();
		idEmployeeMap = empList.stream().filter(emp -> "Keshav".equals(emp.getName()))
				.collect(Collectors.toMap(emp -> emp.getId(), Function.identity()));
		System.out.println("idEmployeeMap  : " + idEmployeeMap);

	}
	
	public static void findNonrepeatedCharacterInString() {

        String input = "gainjavaknowledge";
        System.out.println("input : " + input);

        String output = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1)
                .map(e -> e.getKey()).findFirst().get();
        
        System.out.println("First Non Repeated Character : " + output);
    }

}
