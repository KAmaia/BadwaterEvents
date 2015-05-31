package com.badwater.kamaia.EventSystem.Events;

import com.badwater.kamaia.EventSystem.EventDispatcher.Dispatcher;

/**
 * Created by Krystal on 2015-05-18.
 */
public class Event implements IEvent {
	private String[] channels;
	public Event(String channels){
		this.channels = channels.split(".");
	}
	public void fire() {
		for (String channel : channels) {
			Dispatcher.getInstance().registerEvent(this, channel);
			System.out.println("Firing: " + this.toString());
		}
	}
}
