package co.tshock.manager.ui.activities.server;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import co.tshock.manager.R;
import co.tshock.manager.api.TShockApi;
import co.tshock.manager.events.Event;
import co.tshock.manager.events.EventListener;
import co.tshock.manager.events.EventManager;
import co.tshock.manager.events.EventType;
import co.tshock.manager.util.Utils;

public class CreateTokenActivity extends ActionBarActivity implements EventListener {

	private EditText ipEditText;
	private EditText portEditText;
	private EditText usernameEditText;
	private EditText passwordEditText;
	
	private EventType[] registerToEvents = new EventType[]{EventType.CREATE_TOKEN, EventType.ERROR};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate_token);
		// Show the Up button in the action bar.
		setupActionBar();
		ipEditText = (EditText) findViewById(R.id.ip_message);
		portEditText = (EditText) findViewById(R.id.port_message);
		usernameEditText = (EditText) findViewById(R.id.username_message);
		passwordEditText = (EditText) findViewById(R.id.password_message);
	}
	
	public void generateToken(View v) {
		TShockApi.setServer(ipEditText.getText().toString(),
				Utils.parseInt(portEditText.getText().toString(), 7878));

		TShockApi.createToken(usernameEditText.getText().toString(),
				passwordEditText.getText().toString());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		EventManager.getInstance().registerListener(this.registerToEvents, this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		EventManager.getInstance().unregisterListener(this.registerToEvents, this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generate_token, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onEvent(Event event) {
		switch (event.getType()) {
		case CREATE_TOKEN:
			TextView textView = new TextView(this);
			String token = event.getData().get("token").toString();
			textView.setText("Token generated successfully. Token: " + token);
			setContentView(textView);
			break;
		case ERROR:
			String message = "Unknown error occurred";
			if (event.getData().containsKey("error")) {
				message = event.getData().get("error").toString();
			}
			if (event.getData().containsKey("exception")) {
				message = ((Exception) event.getData().get("exception"))
						.toString();
			}
			textView = new TextView(this);
			textView.setText("Error: " + message);
			setContentView(textView);
			break;
		default:
			break;
		}

	}
}