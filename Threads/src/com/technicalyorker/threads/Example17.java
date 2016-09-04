package com.technicalyorker.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant locks are quite similar to Synchronized blocks.Reentrant locks come
 * with a flag of fairness. Setting it to true lets the longest waiting thread
 * get the lock.
 * 
 * A locked Reentrant lock must have its unlock called to release the lock. The
 * condition interface allow a reentrant lock release and acquire the locks.
 * 
 * Reentrant locks also allows to check if the lock is on with the isLocked()
 * method.
 * 
 * @author achuth
 *
 */
public class Example17 {
	ReentrantLock rl = new ReentrantLock(Boolean.TRUE);
	Condition c = rl.newCondition();
	Runnable r1 = () -> {
		try {
			rl.lock();
			System.out.println("Going to release lock and wait!");
			c.await();
			System.out.println("Got the signal!");
		} catch (Exception e) {

		} finally {
			rl.unlock();
		}
	};
	Runnable r2 = () -> {
		try {
			rl.lock();
			System.out.println("Got a lock!");
			Thread.sleep(5000);
			c.signal();
			System.out.println("Released it.");
		} catch (Exception e) {

		} finally {
			rl.unlock();
		}
	};

	public void perform() {
		new Thread(r1).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r2).start();
	}

	public static void main(String[] args) {
		new Example17().perform();
	}
}
