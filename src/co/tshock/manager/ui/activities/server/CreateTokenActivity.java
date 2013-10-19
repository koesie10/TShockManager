package co.tshock.manager.ui.activities.server;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import co.tshock.manager.R;
import co.tshock.manager.api.TShockApi;
import co.tshock.manager.events.Event;
import co.tshock.manager.events.EventListener;
import co.tshock.manager.events.EventManager;
import co.tshock.manager.events.EventType;
import co.tshock.manager.ui.activities.BaseActivity;
import co.tshock.manager.util.Utils;

public class CreateTokenActivity extends BaseActivity implements EventListener {

	private EditText ipEditText;
	private EditText portEditText;
	private EditText usernameEditText;
	private EditText passwordEditText;

	private EventType[] registerToEvents = new EventType[] {
			EventType.CREATE_TOKEN, EventType.ERROR };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate_token);
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
		EventManager.getInstance()
				.registerListener(this.registerToEvents, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		EventManager.getInstance().unregisterListener(this.registerToEvents,
				this);
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