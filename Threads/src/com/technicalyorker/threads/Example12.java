package com.technicalyorker.threads;

import java.util.Scanner;

/**
 * Wait and Notify example. When wait is called the thread hands over the lock
 * away and waits for the notify to get called. Once it gets notified it
 * resumes.
 * 
 * This is unlike Thread.sleep() which takes it lock along with it. Wait also
 * comes with a version which takes time as a parameter.
 * 
 * @author achuth
 *
 */
public class Example12 {
	Object account = new Object();

	public static void main(String[] args) {
		new Example12().perform();
	}

	private void perform() {
		new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					goingToSneeze();
				}
			};
		}.start();
		new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					confirmSneezing();
				}
			};
		}.start();
	}

	public void goingToSneeze() {
		synchronized (account) {
			try {
				System.out.println("Waiting for confirmation to Sneeze");
				account.wait();
				System.out.println("Achoooo");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Sneeze Completed");
			account.notify();
		}
	}

	public void confirmSneezing() {
		Scanner s = new Scanner(System.in);
		synchronized (account) {
			System.out.println("Sneeze Confirmation in 2 secs");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			account.notify();
		}
		s.close();
	}
}
