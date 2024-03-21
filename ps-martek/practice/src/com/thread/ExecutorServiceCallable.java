package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCallable {
	
	public static void main(String[] args) {
	  
		Callable<String> callableImpl = ()-> {
			Thread.sleep(100);
			return Thread.currentThread().getName();
		};
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		/*int[] arr = new int[5];
		
		int[] arr1 = new int[]{1,2,3};
		
		int[] arr2 = {1,2,3};*/
		List<Future<String>> futureList = new ArrayList<>();
		int index  = 0;
		while(index < 5) {
			Future<String> future = executorService.submit(callableImpl);
			futureList.add(future);
			index ++;
		}
		futureList.forEach(obj -> {
			try {
				System.out.println(obj.get());
				System.out.println(obj.isDone());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		executorService.shutdown();
	}
	

}
