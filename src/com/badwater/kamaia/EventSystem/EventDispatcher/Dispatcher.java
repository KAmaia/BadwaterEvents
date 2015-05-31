package com.badwater.kamaia.EventSystem.EventDispatcher;

import com.badwater.kamaia.EventSystem.Channel.Channel;
import com.badwater.kamaia.EventSystem.EventRecipient.IEventRecipient;
import com.badwater.kamaia.EventSystem.Events.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Krystal on 2015-05-18.
 * This class is a singleton, there should only ever be one.
 * Event Dispatcher class.  Runs on it's own thread.
 * Handles dispatching events through channels.
 */
public class Dispatcher implements Runnable {

	private static Dispatcher instance = new Dispatcher();
	private HashSet<Channel>                              channels;
	private HashMap<IEventRecipient, LinkedList<Channel>> EventRecipients;
	private Channel GlobalChannel = new Channel("Global");
	private boolean running;

	/**
	 * private constructor to prevent multiple instantiations.
	 */
	private Dispatcher() {
		//add a new global channel to the dispatcher.
		channels = new HashSet<Channel>();
		channels.add(GlobalChannel);
		EventRecipients = new HashMap<IEventRecipient, LinkedList<Channel>>();
	}

	/**
	 * Returns the instance of this class.
	 *
	 * @return the instance of Dispatcher.
	 */

	public static synchronized Dispatcher getInstance() {
		return instance;
	}

	/**
	 * The Event Dispatch Loop.
	 * <p/>
	 * Iterates over the channels, and if a recipient is listening to a channel on which an event occurs
	 * calls the recipients onEvent() method.
	 * <p/>
	 * For some reason this is not working....
	 */
	public void run() {
		//start the event loop.
		running = true;
		System.out.println("Dispatcher Started!");
		while (running) {
			for (Channel c : channels) {
				if (!c.isEmpty()) {
					Event e = c.getNextEvent();
					for (IEventRecipient ier : EventRecipients.keySet()) {
						if (EventRecipients.get(ier).contains(c)) {
							System.out.println("Dispatching: " + e.toString() + " to: " + ier
								   .toString() + " On Channel: " + c.toString());
							ier.onEvent(e);
						}
					}
				}

			}
		}


		System.out.println("Dispatcher Ending!");
	}

	/**
	 * Returns a channel whose name matches the search pattern.  Names are not case sensitive.
	 *
	 * @param channelName the channel which matches the search pattern channelName
	 * @return a channel if found, else returns null.
	 */
	public synchronized Channel getChannelByName(String channelName) {
		for (Channel c : channels) {
			if (c.getName().equalsIgnoreCase(channelName)) {
				return c;
			}
		}
		return null;

	}


	/**
	 * Registers a new event to be dispatched.
	 *
	 * @param e           the event calling this method.
	 * @param channelName the channel name to register the event to.
	 * @return true if successful, false on failure.
	 */
	public synchronized boolean registerEvent(Event e, String channelName) {
		for (Channel c : this.channels) {
			if (c.getName().equalsIgnoreCase(channelName)) {
				c.addEvent(e);
				return true;
			}
		}
		return false;
	}

	/**
	 * Registers a new EventRecipient with the dispatcher.
	 *
	 * @param ier         an IEventRecipient to register with the dispatcher.
	 * @param channelName the channel to register the recipient to.
	 * @return true if successful, false if not.
	 */
	public synchronized boolean registerRecipient(IEventRecipient ier, String channelName) {
		//If the recipients list does NOT contain ier, add it with a new LinkedList<Channel>, then add the new
		// channel
		// and return true.
		if (!EventRecipients.keySet().contains(ier)) {
			EventRecipients.put(ier, new LinkedList<Channel>());
			EventRecipients.get(ier).add(GlobalChannel);
			EventRecipients.get(ier).add(new Channel(channelName.toLowerCase()));
			return true;
		}

		//If the recipients list DOES contain ier, and ier is already registered to that channel return false
		//and do nothing.
		else if (EventRecipients.keySet().contains(ier)) {
			for (Channel c : EventRecipients.get(ier)) {
				if (c.getName().equals(channelName.toLowerCase())) {
					return false;
				}
			}
		}
		//Else, add the new channel to ier's channel list, and return true.
		else {
			EventRecipients.get(ier).add(new Channel(channelName.toLowerCase()));
			return true;

		}

		return false;
	}

}


