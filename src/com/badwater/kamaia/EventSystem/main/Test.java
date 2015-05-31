package com.badwater.kamaia.EventSystem.main;

import com.badwater.kamaia.EventSystem.Events.ChannelIsntEmptyEvent;
import com.badwater.kamaia.EventSystem.Events.Event;
import com.badwater.kamaia.EventSystem.Events.ShutdownEvent;

/**
 * Created by Krystal on 2015-05-18.
 */
public class Test implements Runnable {

	public void run() {
		int i = 1;
		while (i <= 100) {
			System.out.println("Firing Event: " + i + " of 100");
			Event e = new ChannelIsntEmptyEvent("global");
			fireNewEvent(e);
			i++;
		}
		fireNewEvent(new ShutdownEvent("global"));
	}


	public void fireNewEvent(Event e) {
		e.fire();
	}

}
