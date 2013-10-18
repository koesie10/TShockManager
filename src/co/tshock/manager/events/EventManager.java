package co.tshock.manager.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {
	private static EventManager instance;

	public static EventManager getInstance() {
		if (instance == null) {
			instance = new EventManager();
		}
		return instance;
	}

	private HashMap<EventType, ArrayList<EventListener>> listeners;

	public void addListener(EventType type, EventListener listener) {
		if (listeners.get(type) == null) {
			this.listeners.put(type, new ArrayList<EventListener>());
		}
		this.listeners.get(type).add(listener);
	}

	public void addListener(EventType[] types, EventListener listener) {
		for (EventType type : types) {
			this.addListener(type, listener);
		}
	}

	public void addListeners(EventType type, EventListener[] listeners) {
		for (EventListener listener : listeners) {
			this.addListener(type, listener);
		}
	}

	public void addListeners(EventType[] types, EventListener[] listeners) {
		for (EventListener listener : listeners) {
			this.addListener(types, listener);
		}
	}

	private EventManager() {
		this.listeners = new HashMap<EventType, ArrayList<EventListener>>();
	}

	public void notify(Event event) {
		ArrayList<EventListener> typeListeners = listeners.get(event.getType());
		if (typeListeners == null) {
			return;
		}
		for (EventListener listener : typeListeners) {
			listener.onEvent(event);
			if (event.isCancelled()) {
				return;
			}
		}
	}
}
