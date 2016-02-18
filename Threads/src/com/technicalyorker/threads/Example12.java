package com.technicalyorker.threads;

import java.util.Scanner;

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
