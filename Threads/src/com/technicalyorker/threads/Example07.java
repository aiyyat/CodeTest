package com.technicalyorker.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Now it works since List is synchronized.
 * 
 * @author achuth
 *
 */
public class Example07 {
	private List<Double> list = new ArrayList<Double>();

	private void addElement() {
		synchronized (list) {
			list.add(Math.random());
		}
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
		new Example07().perform();
	}
}
