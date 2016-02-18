package com.technicalyorker.threads;

import java.util.ArrayList;
import java.util.List;

public class Example04 {
	private List<Double> list = new ArrayList<Double>();

	public void perform() {
		Thread t = new Thread() {
			@Override
			public void run() {
				addElement();
			}
		};
		t.start();
	}

	private void addElement() {
		list.add(Math.random());
	}

	public static void main(String[] args) {
		Example04 e = new Example04();
		for (int i = 0; i < 1000; i++) {
			e.perform();
		}
		System.out.println("Size:" + e.list.size());
	}
}
