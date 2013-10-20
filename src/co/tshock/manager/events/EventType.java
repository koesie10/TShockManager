package co.tshock.manager.events;

import android.text.InputType;
import co.tshock.manager.R;

/**
 * Represents a possible API request
 * 
 * @author koesie10
 */
public enum EventType {
	/**
	 * An event with this type will be broadcast when an error occurred with
	 * additional details in error and/or exception in {@link Event#getData()}
	 */
	ERROR,
	SERVER_LIST_CHANGED,
	/**
	 * This event will create a token and this will be broadcasted to the
	 * listeners with token in {@link Event#getData()}
	 * 
	 * @see co.tshock.manager.api.TShockApi#createToken(String, String)
	 */
	CREATE_TOKEN("/v2/token/create/%2$s?username=%1$s"),
	/**
	 * This event will destroy a token and this will be broadcasted to the
	 * listeners
	 * 
	 * @see co.tshock.manager.api.TShockApi#destroyToken()
	 */
	DESTROY_TOKEN("/token/destroy/%s"),
	TOKENTEST("/tokentest"),
	/**
	 * This event will create a token and this will be broadcasted to the
	 * listeners
	 * 
	 * @see co.tshock.manager.api.TShockApi#status()
	 */
	SERVER_STATUS("/v2/server/status"),
	SERVER_MOTD("/v3/server/motd"),
	SERVER_RULES("v3/server/rules"),
	/**
	 * This event will broadcast a message to the players on the TShock server
	 * and this will be broadcasted to the listeners with the message in the
	 * message entry of {@link Event#getData()}
	 * 
	 * @see co.tshock.manager.api.TShockApi#serverBroadcast(String)
	 */
	SERVER_BROADCAST("/v2/server/broadcast", new EventTypeInfo(
			R.string.server_broadcast, true,
			new EventParamList(new EventParam[] { new EventParam("msg",
					R.string.message) }))),
	SERVER_RELOAD("/v3/server/reload"),
	SERVER_OFF("/v2/server/off", new EventTypeInfo(R.string.server_off, true,
			new EventParamList(
					new EventParam[] {
							new EventParam("confirm", R.string.confirm,
									InputType.TYPE_CLASS_TEXT,
									R.string.boolean_true),
							new EventParam("nosave", R.string.nosave,
									InputType.TYPE_CLASS_TEXT,
									R.string.boolean_false) }))),
	SERVER_RESTART("/v3/server/restart", new EventTypeInfo(
			R.string.server_restart, true, new EventParamList(
					new EventParam[] {
							new EventParam("confirm", R.string.confirm,
									InputType.TYPE_CLASS_TEXT,
									R.string.boolean_true),
							new EventParam("nosave", R.string.nosave,
									InputType.TYPE_CLASS_TEXT,
									R.string.boolean_false) }))),
	SERVER_RAWCMD("/v3/server/rawcmd",
			new EventTypeInfo(R.string.rawcmd, true, new EventParamList(
					new EventParam[] { new EventParam("cmd", R.string.cmd,
							InputType.TYPE_CLASS_TEXT, R.string.slash) })));

	private String url;
	private EventTypeInfo info;

	private EventType(String url, EventTypeInfo info) {
		this.url = url;
		this.info = info;
	}

	private EventType(String url) {
		this(url, new EventTypeInfo());
	}

	private EventType() {
		this("");
	}

	/**
	 * Gets the URL that should be used for the API
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Gets the URL that should be used for the API
	 * 
	 * @param args
	 *            Arguments that would normally be inserted in
	 *            {@link String#format(String, Object...)}
	 * @return the url in which all parameters have been replaced
	 * @throws NullPointerException
	 *             if {@code url == null}
	 * @throws java.util.IllegalFormatException
	 *             if the url is invalid
	 * 
	 * @see String#format(String, Object...)
	 */
	public String getUrl(Object... args) {
		return String.format(url, args);
	}

	/**
	 * Gets the info associated with this event type
	 * 
	 * @return
	 */
	public EventTypeInfo getInfo() {
		return info;
	}

	/**
	 * Returns this object as string
	 * 
	 * @see #name()
	 */
	@Override
	public String toString() {
		return this.name();
	}
}
