package com.technicalyorker.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Example03 extends Thread {
	AtomicInteger amount = new AtomicInteger(0);

	public static void main(String[] args) {
		new Example03().perform();
	}

	public void perform() {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					incrementAmount();
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					incrementAmount();
				}
			}
		};
		t1.start();
		t2.start();
		// Join so that the amount is only printed after the threads t1 and t2
		// completes
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// guaranteed to be 20000
		System.out.println(amount);
	}

	// Atomic Integer is guaranteed to be thread safe
	private void incrementAmount() {
		amount.incrementAndGet();
	}
}
