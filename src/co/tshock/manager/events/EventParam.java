package co.tshock.manager.events;

import android.text.InputType;

/**
 * Represents a parameter for an API request
 * 
 * @author koesie10
 */
public class EventParam {
	private String paramName;
	private int paramString;
	private int type;
	private int defaultValue;

	/**
	 * Constructs a new event parameter
	 * 
	 * @param paramName
	 *            The name that should be used in the request
	 * @param paramString
	 *            The resource ID of the string that should be presented to the
	 *            user
	 * @param type
	 *            The type of parameter, constants are defined in
	 *            {@link android.text.InputType}
	 * @param defaultValue
	 *            The default value of this param
	 * 
	 * @see android.text.InputType
	 */
	public EventParam(String paramName, int paramString, int type,
			int defaultValue) {
		super();
		this.paramName = paramName;
		this.paramString = paramString;
		this.type = type;
		this.defaultValue = defaultValue;
	}

	/**
	 * Constructs a new event parameter
	 * 
	 * @param paramName
	 *            The name that should be used in the request
	 * @param paramString
	 *            The resource ID of the string that should be presented to the
	 *            user
	 * @param type
	 *            The type of parameter, constants are defined in
	 *            {@link android.text.InputType}
	 * 
	 * @see android.text.InputType
	 */
	public EventParam(String paramName, int paramString, int type) {
		this(paramName, paramString, type, 0);
	}

	/**
	 * Constructs a new event parameter with
	 * {@link android.text.InputType#TYPE_CLASS_TEXT} as type
	 * 
	 * @param paramName
	 *            The name that should be used in the request
	 * @param paramString
	 *            The resource ID of the string that should be presented to the
	 *            user
	 * 
	 * @see android.text.InputType
	 * @see #EventParam(String, int, int)
	 */
	public EventParam(String paramName, int paramString) {
		this(paramName, paramString, InputType.TYPE_CLASS_TEXT);
	}

	/**
	 * @return the name that should be used in the request
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @return the resource ID of the string that should be presented to the
	 *         user
	 */
	public int getParamString() {
		return paramString;
	}

	/**
	 * @return the type that should be used for inputs
	 * 
	 * @see android.text.InputType
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the defaultValue
	 */
	public int getDefaultValue() {
		return defaultValue;
	}

}
