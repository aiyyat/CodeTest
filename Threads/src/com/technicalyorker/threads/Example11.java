package com.technicalyorker.threads;

/**
 * Now this works.
 * 
 * @author achuth
 *
 */
public class Example11 {
	private Integer anInt = new Integer(0);
	private Object lock = new Object();

	private void addElement() {
		synchronized (lock) {
			anInt++;
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
		System.out.println(anInt);
	}

	public static void main(String[] args) {
		new Example11().perform();
	}
}
