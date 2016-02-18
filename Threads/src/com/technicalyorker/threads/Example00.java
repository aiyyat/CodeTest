package com.technicalyorker.threads;

/**
 * Need for Volatility. Some JVM tries to Optimize variables by caching it when
 * it comes to thread as if no other thread would be expected to be modifying
 * the variable. These thread cached variables are local copies to threads and
 * hence a change from outside the thread is not visible to the thread itself.
 * The following program without volatile could potentially go into an infinite
 * loop with some JVMs.
 * 
 * @author achuth
 *
 */
public class Example00 extends Thread {
	volatile boolean stop = false;

	public static void main(String[] args) {
		Example00 e = new Example00();
		e.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		e.stopNow();
	}

	private void stopNow() {
		stop = true;
	}

	public void run() {
		while (!stop) {
			System.out.println("Running");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}