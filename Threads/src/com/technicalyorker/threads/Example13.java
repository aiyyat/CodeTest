package com.technicalyorker.threads;

public class Example13 {
	int i = 0;
	Object lock = new Object();

	public static void main(String[] args) {
		new Example13().perform();
	}

	private void perform() {
		Thread producer = new Thread() {
			@Override
			public void run() {
				while (true)
					add();
			}
		};
		Thread consumer = new Thread() {
			@Override
			public void run() {
				while (true)
					remove();
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

	private void add() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>add wait for lock");
		synchronized (lock) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>add starting" + i);
			if (i == 10) {
				try {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>add going to wait");
					lock.wait();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>add resuming");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				double e = Math.random();
				try {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>Adding " + i);
					i++;
					lock.notify();
					Thread.sleep(0);
				} catch (InterruptedException w) {
					w.printStackTrace();
				}

			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>add quits");
		}
	}

	private void remove() {
		System.out.println("remove wait for lock");
		synchronized (lock) {
			System.out.println("remove starting" + i);
			if (i == 1) {
				try {
					System.out.println("remove going to wait");
					lock.wait();
					System.out.println("remove waiting");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Removed:" + i);
			lock.notify();
			try {
				i--;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("remove quits");
		}
	}
}