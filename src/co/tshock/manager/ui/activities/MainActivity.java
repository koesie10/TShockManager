package co.tshock.manager.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import co.tshock.manager.R;
import co.tshock.manager.ui.activities.server.CreateTokenActivity;
import co.tshock.manager.ui.activities.server.ServerBroadcastActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.add("Test");
		return true;
	}

	public void generateButton(View view) {
		Intent intent = new Intent(this, CreateTokenActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().toString().equals("Test")) {
			Intent intent = new Intent(this, ServerBroadcastActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
