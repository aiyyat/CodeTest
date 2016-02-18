package com.technicalyorker.threads;

public class ExampleA {
	Object account = new Object();

	public static void main(String[] args) {
		new ExampleA().perform();
	}

	private void perform() {
		new Thread() {
			public void run() {
				for (int i = 0; i < 1000; i++) {
					deposit();
				}
			};
		}.start();
		new Thread() {
			public void run() {
				for (int i = 0; i < 1000; i++) {
					withdraw();
				}
			};
		}.start();
	}

	public void deposit() {
		synchronized (account) {
			try {
				System.out.println("Going to Deposit");
				account.wait();
				System.out.println("Deposited");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Deposit Completed");
			account.notify();
		}
	}

	public void withdraw() {
		synchronized (account) {
			try {
				System.out.println("Going to Withdraw");
				account.notify();
				account.wait();
				System.out.println("Withdrawn");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Withdraw Completed");
		}
	}
}
