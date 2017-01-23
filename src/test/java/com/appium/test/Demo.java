package com.appium.test;

public class Demo {

	public static void main(String args[]) throws Exception {

		for (int i = 0; i < 10; i++) {
			System.out.println("================");
			for (int j = 0; j < i ; j++) {
				System.out.println(j);
				if (j > 5) {
					break;
				}
				
			}
		}
	}


}
