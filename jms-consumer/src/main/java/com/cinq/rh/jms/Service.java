package com.cinq.rh.jms;

import java.util.Properties;



public class Service implements Runnable {
	public static void main(String args[]) {
		System.out.println("Send the messages....");
		Thread thread = new Thread(new Service());
		thread.start();
	}

	public void run() {
		// Loop receiving messages
	}
}
