package co.tshock.manager.events;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a list of parameters for a request
 * 
 * @author koesie10
 */
public class EventParamList extends ArrayList<EventParam> {
	private static final long serialVersionUID = -2198235567833758685L;

	/**
	 * Constructs a new instance of {@code EventParamList} containing the
	 * elements of the specified array
	 * 
	 * @param elements
	 *            the array of elements to add
	 */
	public EventParamList(EventParam[] elements) {
		this();
		for (EventParam element : elements) {
			this.add(element);
		}
	}

	/**
	 * Constructs a new instance of {@code EventParamList} with the specified
	 * initial capacity.
	 * 
	 * @param capacity
	 *            the initial capacity of this {@code EventParamList}.
	 */
	public EventParamList(int capacity) {
		super(capacity);
	}

	/**
	 * Constructs a new {@code EventParamList} instance with zero initial
	 * capacity.
	 */
	public EventParamList() {
		super();
	}

	/**
	 * Constructs a new instance of {@code EventParamList} containing the
	 * elements of the specified collection.
	 * 
	 * @param collection
	 *            the collection of elements to add.
	 */
	public EventParamList(Collection<? extends EventParam> collection) {
		super(collection);
	}
}
