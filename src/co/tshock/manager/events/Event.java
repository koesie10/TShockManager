package co.tshock.manager.events;

import java.util.HashMap;
import java.util.Map;

public class Event {
	private EventType type;
	private Map<String, Object> data;
	private boolean cancelled;

	/**
	 * 
	 * @param type
	 * @param data
	 */
	public Event(EventType type, Map<String, Object> data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * @see Event#Event(EventType, Map)
	 * @param type
	 */
	public Event(EventType type) {
		this(type, new HashMap<String, Object>());
	}

	/**
	 * Returns the type of this Event
	 * 
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * Returns the data in a Map<String, Object>
	 * 
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @return the cancelled
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * 
	 * 
	 * @param cancelled
	 *            the cancelled to set
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@Override
	public String toString() {
		return this.type.toString() + ": " + this.data.toString();
	}

}
