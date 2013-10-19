package co.tshock.manager.ui.activities.server;

import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import co.tshock.manager.events.EventType;
import co.tshock.manager.ui.activities.BaseCommandActivity;

public class ServerBroadcastActivity extends BaseCommandActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setEventType(EventType.SERVER_BROADCAST);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void processData(JSONObject object, Map<String, Object> data) {
		
	}

}
