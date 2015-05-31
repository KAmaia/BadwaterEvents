package com.badwater.kamaia.EventSystem.main;

import com.badwater.kamaia.EventSystem.EventDispatcher.Dispatcher;

/**
 * Created by Krystal on 2015-05-18.
 */
public class main {
	public static void main(String args[]) throws InterruptedException {
		//Start the dispatcher in it's own thread, so that it is always running.
		Dispatcher d = Dispatcher.getInstance();

		//test is an event firer.
		Test test = new Test();

		//test2 is an event recipient
		Test2 test2 = new Test2();

		//get things started here.
		Thread t = new Thread(d);
		Thread t2 = new Thread(test);
		t.start();
		t2.start();



	}
}
