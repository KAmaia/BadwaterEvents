package com.badwater.kamaia.EventSystem.EventRecipient;

import com.badwater.kamaia.EventSystem.Events.IEvent;

/**
 * Created by Krystal on 2015-05-18.
 * Empty Interface to define an Event Recipient.
 */
public interface IEventRecipient {

	public <E extends IEvent> void onEvent(E IEvent);
}
