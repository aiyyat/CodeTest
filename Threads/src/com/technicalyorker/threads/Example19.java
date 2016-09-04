package com.technicalyorker.threads;

/**
 * Calling the thread interrupt method
 * 
 * @author achuth
 *
 */
public class Example19 {
	Runnable r = () -> {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			System.out.println("Thread Interrupted!");
		}
	};

	public void perform() {
		try {
			Thread t = new Thread(r);
			t.start();
			Thread.sleep(2000);
			t.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Example19().perform();
	}
}