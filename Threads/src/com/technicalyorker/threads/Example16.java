package com.technicalyorker.threads;

import java.util.concurrent.CountDownLatch;

/**
 * Countdown latch.
 * 
 * @author achuth
 *
 */
public class Example16 {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cl = new CountDownLatch(20);
		MyThread t1 = new MyThread(cl);
		MyThread t2 = new MyThread(cl);
		MyThread t3 = new MyThread(cl);
		t1.setDaemon(true);
		t2.setDaemon(true);
		t3.setDaemon(true);
		t1.start();
		t2.start();
		t3.start();
		cl.await();
		System.out.println("Dong..");
	}
}

class MyThread extends Thread {
	private CountDownLatch cl;

	public MyThread(CountDownLatch cl) {
		this.cl = cl;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cl.countDown();
			System.out.println("Ding..");
		}
	}
}
