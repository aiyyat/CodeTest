package com.technicalyorker.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * This example sometimes throws the following error:
 * 
 * Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 9369
 * at java.util.ArrayList.add(ArrayList.java:441) at
 * com.technicalyorker.threads.Example5.addElement(Example5.java:10) at
 * com.technicalyorker.threads.Example5.access$0(Example5.java:9) at
 * com.technicalyorker.threads.Example5$1.run(Example5.java:20) 13587
 * 
 * This is due to the ArrayList implementation. Internally ArrayList uses Arrays
 * which is initialized with a default size. When it exceeds the Array size is
 * more than doubled and elements copied into the new array, which gets used
 * thereafter. During this time of array resizing and copying + Non Thread safe
 * nature of ArrayList internal objects results in ArrayIndexOutOfBound
 * 
 * @author achuth
 *
 */
public class Example05 {
	private List<Double> list = new ArrayList<Double>();

	private void addElement() {
		list.add(Math.random());
	}

	private void perform() {
		Thread t1 = new Thread() {
			int i = 0;

			@Override
			public void run() {
				while (i++ < 10000) {
					addElement();
				}
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			int i = 0;

			@Override
			public void run() {
				while (i++ < 10000) {
					addElement();
				}
			}
		};
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(list.size());
	}

	public static void main(String[] args) {
		new Example05().perform();
	}
}
