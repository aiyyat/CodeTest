package com.technicalyorker.threads;

public class Example02 extends Thread {
	int amount = 0;

	public static void main(String[] args) {
		new Example02().perform();
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
		// Guaranteed to be 20000
		System.out.println(amount);
	}

	private synchronized void incrementAmount() {
		amount++;
	}
}
