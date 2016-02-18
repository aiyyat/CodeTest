package com.technicalyorker.threads;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author achuth
 *
 */
public class Example05 {
	private List<Double> list = new CopyOnWriteArrayList<Double>();

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
