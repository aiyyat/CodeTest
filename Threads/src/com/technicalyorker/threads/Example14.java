package com.technicalyorker.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Example14 {
	BlockingQueue<Double> i = new LinkedBlockingQueue<Double>(10);
	Object lock = new Object();

	public static void main(String[] args) {
		new Example14().perform();
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		consumer.start();
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void add() {
		try {
			i.put(Math.random());
			System.out.println("After Add Size: " + i.size());
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void remove() {
		try {
			i.remove();
			System.out.println("After Remove Size: " + i.size());
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}