package co.tshock.manager.events;

/**
 * Represents data for an API request
 * 
 * @author koesie10
 */
public class EventTypeInfo {
	private int titleResId;
	private boolean visible;
	private EventParamList params;

	/**
	 * Constructs a new {@code EventTypeInfo}
	 * 
	 * @param name
	 *            The resource ID of the name that must be used in a list of
	 *            requests
	 * @param visible
	 *            Whether this event type is visible in a list of requests
	 * @param params
	 *            A list with params that can be used for this event type
	 */
	public EventTypeInfo(int titleResId, boolean visible, EventParamList params) {
		super();
		this.titleResId = titleResId;
		this.visible = visible;
		if (params == null) {
			params = new EventParamList();
		}
		this.params = params;
	}

	/**
	 * Constructs a new {@code EventTypeInfo}
	 * 
	 * @param visible
	 *            Whether this event type is visible in a list of requests
	 * @param params
	 *            A list with params that can be used for this event type
	 */
	public EventTypeInfo(boolean visible, EventParamList params) {
		this(0, visible, params);
	}

	/**
	 * Constructs a new {@code EventTypeInfo}
	 * 
	 * @param params
	 *            A list with params that can be used for this event type
	 */
	public EventTypeInfo(EventParamList params) {
		this(0, false, params);
	}

	/**
	 * Constructs a new {@code EventTypeInfo}
	 */
	public EventTypeInfo() {
		this(0, false, null);
	}

	/**
	 * Gets the resource ID of the title. This should be given to
	 * {@link android.content.Context#getString(int)}
	 * 
	 * @return the titleResId
	 * @see android.content.Context#getString(int)
	 */
	public int getTitleResId() {
		return titleResId;
	}

	/**
	 * Whether this item should be visible in a list of items
	 * 
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Returns a list of all params that can be used for this event type
	 * 
	 * @return the params
	 */
	public EventParamList getParams() {
		return params;
	}

}
