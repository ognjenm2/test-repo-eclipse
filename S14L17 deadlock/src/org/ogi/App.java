package org.ogi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Lock l1 = new ReentrantLock();
		Lock l2 = new ReentrantLock();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				Boolean flagLock1 = false;
				Boolean flagLock2 = false;
				while (true) {
					try {
						flagLock1 = l1.tryLock();
						flagLock2 = l2.tryLock();
					} finally {
						if (flagLock1) {
							System.out.println("Inside t1 lock1");
							l1.unlock();
						}
						if (flagLock2) {
							System.out.println("Inside t1 lock2");
							l2.unlock();
						}
						if (flagLock1 && flagLock2) {
							break;
						}
					}
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				Boolean flagLock1 = false;
				Boolean flagLock2 = false;
				while (true) {
					try {
						flagLock1 = l2.tryLock();
						flagLock2 = l1.tryLock();
					}  finally {
						if (flagLock1) {
							System.out.println("Inside t1 lock1");
							l2.unlock();
						}
						if (flagLock2) {
							System.out.println("Inside t1 lock2");
							l1.unlock();
						}
						if (flagLock1 && flagLock2) {
							break;
						}
					}
				}
			}

		});
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Inside main method");

	}

}
