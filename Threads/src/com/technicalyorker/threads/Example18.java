package com.technicalyorker.threads;

import java.util.concurrent.Semaphore;

/**
 * Semaphores.
 * 
 * @author achuth
 *
 */
public class Example18 {
	final int poolSize = 10;
	final Semaphore s = new Semaphore(poolSize);
	final boolean[] connections = new boolean[poolSize];

	Runnable r = () -> {
		try {
			s.acquire();
			// Attain the connection from the connection pool and perform some
			// operation
			int conn = -1;
			for (int i = 0; i < poolSize; i++) {
				if (!connections[i]) {
					conn = i;
				}
			}
			connections[conn] = true;
			print(connections);
			Thread.sleep(2000);
			s.release();
			connections[conn] = false;
			print(connections);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	public void perform() {
		for (int i = 0; i < 30; i++) {
			new Thread(r).start();
		}
	}

	private synchronized void print(boolean[] connections) {
		System.out.println("Pool's Connection Availability Status:");
		for (boolean c : connections) {
			System.out.print((c ? 0 : 1) + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		new Example18().perform();
	}
}
