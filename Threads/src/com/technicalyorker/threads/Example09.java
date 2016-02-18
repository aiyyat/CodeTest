package com.technicalyorker.threads;

/**
 * Now even though the Integer variable is used to lock it behaves wierd. Its
 * better to use a lock variable separate from the integer itself. This is a
 * better practice in all cases since you never know if java has done something
 * to optimize your original variable to something else while incrementing for
 * example in case of an integer etc. Java only ensures that the end result of
 * the variable is correct. Hence its always safer to use a variable explicitely
 * for locking.
 * 
 * @author achuth
 *
 */
public class Example09 {
	private Integer anInt = new Integer(0);

	private void addElement() {
		synchronized (anInt) {
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
		new Example09().perform();
	}
}
