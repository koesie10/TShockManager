package co.tshock.manager.api;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import co.tshock.manager.events.EventType;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Class to make requests to the TShock API
 * 
 * @author koesie10
 */
public class TShockApi {
	private static int port = 7878;
	private static String ip = "";
	private static final String TAG = TShockApi.class.getSimpleName();
	private static String token;

	private static AsyncHttpClient client = new AsyncHttpClient();

	/**
	 * Sets the server to which all request will be targeted
	 * 
	 * @param newIp
	 *            The IP to use to redirect requests to
	 * @param newPort
	 *            The port to redirect requests to
	 */
	public static void setServer(String newIp, int newPort) {
		ip = newIp;
		port = newPort;
	}

	/**
	 * Creates a token
	 * 
	 * This token is needed to authenticate most of the other calls, so this
	 * should be called first.
	 * 
	 * @param username
	 *            The username of the server account authenticating to
	 * @param password
	 *            The password of the server account authenticating to
	 */
	public static void createToken(final String username, final String password) {
		get(EventType.CREATE_TOKEN, new TShockResponseHandler.DataProcessor() {
			@Override
			public void parseResponse(JSONObject object,
					Map<String, Object> data) throws JSONException {
				token = object.getString("token");
				data.put("token", "token");
				data.put("username", username);
				data.put("password", password);
				Log.i(TAG, String.format("Successfully authenticated with %s",
						token));
			}
		}, username, password);
	}

	/**
	 * This call destroys the token that is currently in use
	 * 
	 * The token has been made with {@link #createToken(String, String)}
	 */
	public static void destroyToken() {
		get(EventType.DESTROY_TOKEN, new TShockResponseHandler.DataProcessor() {

			@Override
			public void parseResponse(JSONObject object,
					Map<String, Object> data) throws JSONException {
				token = null;
				Log.i(TAG, "Successfully destroyed token");
			}
		}, token);
	}

	/**
	 * Asks the server for the status of the server.
	 * 
	 * 
	 * This returns the following values in the data map of the {@link Event}:
	 * <ul>
	 * <li>String name</li>
	 * <li>int port</li>
	 * <li>int playerCount</li>
	 * <li>int maxPlayer</li>
	 * <li>String world</li>
	 * <li>String uptime</li>
	 * <li>boolean|String serverPassword</li>
	 * </ul>
	 * 
	 * @see co.tshock.manager.events.Event#getData()
	 */
	public static void status() {
		get(EventType.STATUS, new TShockResponseHandler.DataProcessor() {

			@Override
			public void parseResponse(JSONObject object,
					Map<String, Object> data) throws JSONException {
				data.put("name", object.getString("name"));
				data.put("port", object.getInt("port"));
				data.put("playerCount", object.getInt("playercount"));
				data.put("maxPlayer", object.getInt("maxplayers"));
				data.put("world", object.getString("world"));
				data.put("uptime", object.getString("uptime"));
				data.put("serverPassword", object.getBoolean("serverpassword"));
			}
		});
	}

	/**
	 * Broadcasts a message to everyone on the server.
	 * 
	 * @param message
	 *            The message to broadcast
	 */
	public static void serverBroadcast(final String message) {
		RequestParams params = new RequestParams();
		params.put("msg", message);
		get(EventType.SERVER_BROADCAST, params,
				new TShockResponseHandler.DataProcessor() {

					@Override
					public void parseResponse(JSONObject object,
							Map<String, Object> data) throws JSONException {
						data.put("message", message);

					}
				});
	}

	/**
	 * Checks whether the token has been set and is not empty
	 * 
	 * @return whether the api is authenticated
	 */
	public static boolean isAuthenticated() {
		return (token != null && token != "");
	}

	/**
	 * Gets the current IP
	 * 
	 * @return the ip
	 */
	public static String getIp() {
		return ip;
	}

	/**
	 * Gets the current port
	 * 
	 * @return the port
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * Gets the current base URL generated on the fly with the help of the IP
	 * and port
	 * 
	 * @return the base URL to make requests to without trailing slash
	 * @see #setServer(String, int)
	 * @see #getIp()
	 * @see #getPort()
	 */
	public static String getBaseUrl() {
		return String.format("http://%s:%d", ip, port);
	}

	public static void get(EventType type,
			TShockResponseHandler.DataProcessor dataProcessor,
			RequestParams params, Object... args) {
		get(type.getUrl(args), params, new TShockResponseHandler(type,
				dataProcessor));
	}

	public static void get(EventType type,
			TShockResponseHandler.DataProcessor dataProcessor, Object... args) {
		get(type.getUrl(args), null, new TShockResponseHandler(type,
				dataProcessor));
	}

	public static void get(EventType type,
			TShockResponseHandler.DataProcessor dataProcessor,
			RequestParams params) {
		get(type.getUrl(), params, new TShockResponseHandler(type,
				dataProcessor));
	}

	public static void get(EventType type,
			TShockResponseHandler.DataProcessor dataProcessor) {
		get(type.getUrl(), null, new TShockResponseHandler(type, dataProcessor));
	}

	public static void get(EventType type, RequestParams params) {
		get(type.getUrl(), params, new TShockResponseHandler(type));
	}

	public static void get(EventType type, Object... args) {
		get(type.getUrl(args), null, new TShockResponseHandler(type));
	}

	protected static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		url = getAbsoluteUrl(url);
		if (params == null) {
			params = new RequestParams();
		}
		if (isAuthenticated()) {
			params.put("token", token);
		}
		Log.i(TAG,
				"Sending a new request: (url=" + url + ", params="
						+ params.toString() + ",responseHandler="
						+ responseHandler.getClass().getSimpleName() + ")");
		client.get(url, params, responseHandler);
	}

	@SuppressLint("DefaultLocale")
	private static String getAbsoluteUrl(String relativeUrl) {
		return getBaseUrl() + relativeUrl;
	}
}
