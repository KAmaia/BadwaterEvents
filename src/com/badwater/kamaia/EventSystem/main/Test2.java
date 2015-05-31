package com.badwater.kamaia.EventSystem.main;

import com.badwater.kamaia.EventSystem.EventRecipient.EventRecipient;
import com.badwater.kamaia.EventSystem.Events.Event;

/**
 * Created by Krystal on 2015-05-18.
 */
public class Test2 extends EventRecipient {
	public Test2() {
		register("global");
	}


	public void onEvent(Event E) {
		System.out.println(E.toString());
	}


}
