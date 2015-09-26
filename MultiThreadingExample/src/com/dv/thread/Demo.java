package com.dv.thread;

import org.apache.log4j.Logger;

public class Demo {

	public static final Logger logger = Logger.getLogger(Demo.class);
	
	public static void main(String[] args) {
		Counter ct = new Counter();
		Thread thread1 = new CounterThread(ct,"thread1");
		Thread thread2 = new CounterThread(ct,"thread2");
		Thread thread3 = new CounterThread(ct,"thread3");
		Thread thread4 = new CounterThread(ct,"thread4");
		Thread thread5 = new CounterThread(ct,"thread5");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		

	}

}
