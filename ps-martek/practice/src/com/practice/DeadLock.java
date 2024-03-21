package com.practice;

public class DeadLock {

	private static Object obj = new Object();

	private static Object obj1 = new Object();

	public static void main(String[] args) {

		Runnable a = () -> {
			System.out.println("Inside a");
			synchronized (obj) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (obj1) {
					System.out.println("here");
				}

			}
		};

		Runnable b = () -> {
			System.out.println("Inside b");
			synchronized (obj1) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (obj) {
					System.out.println("here");
				}
			}
		};

		Thread ta = new Thread(a);

		Thread tb = new Thread(b);
		
		ta.start();
		tb.start();
	}

}
