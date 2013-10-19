package co.tshock.manager.ui.activities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import co.tshock.manager.R;
import co.tshock.manager.api.TShockApi;
import co.tshock.manager.api.TShockResponseHandler;
import co.tshock.manager.events.Event;
import co.tshock.manager.events.EventListener;
import co.tshock.manager.events.EventManager;
import co.tshock.manager.events.EventParam;
import co.tshock.manager.events.EventType;

import com.loopj.android.http.RequestParams;

public abstract class BaseCommandActivity extends BaseActivity {
	private EventType eventType;
	private LinearLayout paramsLayout;
	private Map<EventParam, LinearLayout> paramViews;
	private LinearLayout customViewsLayout;
	private EventListener listener;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (eventType == null) {
			throw new RuntimeException(
					"Call setEventType() before calling super.onCreate()");
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_command);
		this.paramsLayout = (LinearLayout) this.findViewById(R.id.paramsLayout);
		this.customViewsLayout = (LinearLayout) this
				.findViewById(R.id.customViewsLayout);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(R.string.app_name);
		getSupportActionBar().setSubtitle(eventType.getInfo().getTitleResId());

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		paramViews = new HashMap<EventParam, LinearLayout>();

		for (EventParam param : eventType.getInfo().getParams()) {
			LinearLayout paramView = (LinearLayout) inflater.inflate(
					R.layout.event_param, null);
			EditText editText = (EditText) paramView
					.findViewById(R.id.eventParamEditText);
			if (param.getParamString() > 0) {
				editText.setHint(param.getParamString());
			}
			editText.setInputType(param.getType());
			editText.setImeOptions(EditorInfo.IME_ACTION_SEND);
			if (param.getDefaultValue() > 0) {
				editText.setText(param.getDefaultValue());
			}

			editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

				@Override
				public boolean onEditorAction(TextView v, int actionId,
						KeyEvent event) {
					if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
							|| (actionId == EditorInfo.IME_ACTION_SEND)) {
						sendRequest();
					}
					return false;
				}

			});

			TextView textView = (TextView) paramView
					.findViewById(R.id.eventParamTextView);
			textView.setText(param.getParamString());

			paramsLayout.addView(paramView);
			paramViews.put(param, paramView);
		}

		listener = new CommandEventListener();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onResume() {
		super.onResume();
		EventManager.getInstance().registerListener(eventType, listener);
		EventManager.getInstance().registerListener(EventType.ERROR, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onPause() {
		super.onPause();
		EventManager.getInstance().unregisterListener(eventType, listener);
		EventManager.getInstance().registerListener(EventType.ERROR, listener);
	}

	private void sendRequest() {
		RequestParams params = new RequestParams();
		for (Entry<EventParam, LinearLayout> entry : paramViews.entrySet()) {
			EventParam param = entry.getKey();
			LinearLayout layout = entry.getValue();
			EditText editText = (EditText) layout
					.findViewById(R.id.eventParamEditText);
			String value = editText.getText().toString();
			params.put(param.getParamName(), value);
		}

		TShockApi.get(eventType, new TShockResponseHandler.DataProcessor() {
			@Override
			public void parseResponse(JSONObject object,
					Map<String, Object> data) throws JSONException {
				processData(object, data);
			}
		}, params);
	}

	/**
	 * Called when the submit button is clicked
	 * 
	 * @param v
	 *            The view of the button
	 */
	public void submit(View v) {
		sendRequest();
	}

	/**
	 * Add a view in a dedicated layout
	 * 
	 * @param v
	 *            the view to add
	 */
	protected void addCustomView(View v) {
		customViewsLayout.addView(v);
	}

	/**
	 * Remove a view in the dedicated layout
	 * 
	 * @param v
	 *            the view to remove
	 */
	protected void removeCustomView(View v) {
		customViewsLayout.removeView(v);
	}

	/**
	 * Remove all views in the dedicated layout
	 */
	protected void clearCustomViews() {
		customViewsLayout.removeAllViews();
	}

	/**
	 * Called when a valid response should be parsed
	 * 
	 * @param object
	 *            The data object that has been approved and is thus valid
	 * @param data
	 *            The data that will be passed to registered EventManagers
	 * @throws JSONException
	 *             When the data is not valid
	 * 
	 * @see Event
	 * @see TShockResponseHandler.DataProcessor#parseResponse(JSONObject, Map)
	 */
	protected abstract void processData(JSONObject object,
			Map<String, Object> data) throws JSONException;

	/**
	 * Sets the event type which is used to create a layout
	 * 
	 * @param type
	 *            the type
	 */
	public void setEventType(EventType type) {
		this.eventType = type;
	}

	/**
	 * Gets the event type that is used to create a layout
	 * 
	 * @return the type
	 */
	public EventType getEventType() {
		return eventType;
	}

	private class CommandEventListener implements EventListener {

		@Override
		public void onEvent(Event event) {
			if (event.getType() == EventType.ERROR) {
				Log.e(TAG, "An error occured while executing this command: "
						+ event.toString());
			} else if (event.getType() == eventType) {
				Log.i(TAG,
						"The event has been received! :) " + event.toString());
			} else {
				Log.e(TAG,
						"An event that can not be handled by the CommandEventListener has been received: "
								+ event.toString());
			}

		}

	}
}
