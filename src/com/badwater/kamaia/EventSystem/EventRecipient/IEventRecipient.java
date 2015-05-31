package com.badwater.kamaia.EventSystem.EventRecipient;

import com.badwater.kamaia.EventSystem.Events.Event;

/**
 * Created by Krystal on 2015-05-18.
 * Empty Interface to define an Event Recipient.
 */
public interface IEventRecipient {


	<e extends Event> void  onEvent(e Event);
}
