package com.badwater.kamaia.EventSystem.EventRecipient;

import com.badwater.kamaia.EventSystem.EventDispatcher.Dispatcher;
import com.badwater.kamaia.EventSystem.Events.IEvent;

/**
 *
 * Created by Krystal on 2015-05-18.
 * Abstraction of the EventRecipient.  Where events finally end up.
 *
 **/
public abstract class EventRecipient implements IEventRecipient {


	protected void register(String channelName) {
		Dispatcher.getInstance().registerRecipient(this, channelName);
	}
	@Override
	public void onEvent(IEvent E){

	}

}
