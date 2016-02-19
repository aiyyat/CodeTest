package com.technicalyorker.threads;

/**
 * Dead Lock using intrinsic Object locks.
 * 
 * Simple solution to prevent dead lock? Just maintain the same order of a and b
 * in both the run methods.
 * 
 * @author achuth
 *
 */
public class Example15 {
	Object a = new Object();
	Object b = new Object();

	public static void main(String[] args) {
		new Example15().perform();
	}

	private void perform() {
		Thread producer = new Thread() {
			@Override
			public void run() {
				while (true) {
					synchronized (a) {
						synchronized (b) {
							System.out.println("hi there..");
						}
					}
				}
			}
		};
		Thread consumer = new Thread() {
			@Override
			public void run() {
				while (true) {
					synchronized (b) {
						synchronized (a) {
							System.out.println("hello there..");
						}
					}
				}
			}
		};
		producer.start();
		consumer.start();
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}