package com.badwater.kamaia.EventSystem.main;

import com.badwater.kamaia.EventSystem.EventRecipient.EventRecipient;
import com.badwater.kamaia.EventSystem.Events.ChannelIsntEmptyEvent;
import com.badwater.kamaia.EventSystem.Events.Event;
import com.badwater.kamaia.EventSystem.Events.ShutdownEvent;

/**
 * Created by Krystal on 2015-05-18.
 */

public class Test2 extends EventRecipient {
	public Test2() {
		register("global");
	}

	public <e extends Event> void onEvent(ChannelIsntEmptyEvent e){
		System.out.println("===\nCaught a new Event!\n===");
	}

	//This *SHOULD handle shutdown events, but for some reason it's not!
	public <e extends ShutdownEvent> void onEvent(ShutdownEvent e) {
		System.out.println("---\nCaught Shutdown Event, exiting!\n---");
		System.exit(0);
	}

	// So this method is catching ALL of my event types.
	// Not Sure how to break it down further.

	@Override
	public <e extends Event> void onEvent(e Event ) {

		System.out.println("Caught an: " + Event.getClass().toString() + " event!");
	}
}
