package com.badwater.kamaia.EventSystem.Channel;

import com.badwater.kamaia.EventSystem.Events.Event;
import com.badwater.kamaia.EventSystem.Events.channelEmptyEvent;

import java.util.LinkedList;

/**
 *
 * Created by Krystal on 2015-05-18.
 * Channels are where events fire to, and are listened to by EventRecipients
 *
 **/
public class Channel {
	private String             name;
	private LinkedList<Event> eventQueue;

	/**
	 *
	 * Constructor
	 * @param channelName the name of this channel
	 *
	 **/
	public Channel(String channelName) {
		name = channelName;
		eventQueue = new LinkedList<Event>();
	}

	/**
	 *
	 * Returns the name of the channel as a string.
	 * @return name
	 *
	 **/
	public String getName() {
		return name;
	}

	/**
	 *
	 * Adds an event to the event queue.
	 * @param e The Event to add to the eventQueue on this channel.
	 *
	 **/
	public void addEvent(Event e) {
		eventQueue.add(e);
	}

	/**
	 *
	 * Returns the next event in the queue.  If the channel is empty returns a ChannelEmptyEvent.
	 * @return the next event in the queue
	 *
	 **/
	public Event getNextEvent() {
		if (!eventQueue.isEmpty()) {
			Event e = eventQueue.getFirst();
			eventQueue.remove(e);
			return e;
		}
		else {
			return new channelEmptyEvent("global");
		}
	}
	@Override
	public String toString(){
		return this.name;
	}

	public boolean isEmpty() {
		return eventQueue.isEmpty();
	}
}
