package org.ogi;

public class App {

	public static void main(String[] args) throws InterruptedException {
		String l1 = "lock1";
		String l2 = "lock2";

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (l1) {
					System.out.println("Inside t1 lock1");
					synchronized (l2) {
						System.out.println("Inside t1 lock2");

					}
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub   
				synchronized (l2) {
					System.out.println("Inside t2 lock2");
					synchronized (l1) {
						System.out.println("Inside t2 lock1");

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
