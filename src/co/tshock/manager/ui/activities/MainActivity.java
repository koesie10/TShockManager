package co.tshock.manager.ui.activities;

import java.util.ArrayList;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import co.tshock.manager.R;
import co.tshock.manager.api.Server;
import co.tshock.manager.data.adapter.ServerAdapter;
import co.tshock.manager.data.sqlite.DatabaseHelper;
import co.tshock.manager.ui.activities.server.CreateTokenActivity;
import co.tshock.manager.ui.dialogs.AddServerDialogFragment;

public class MainActivity extends BaseActivity {
	
	private ListView listView;
	private ListAdapter listAdapter;
	private ArrayList<Server> servers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.mainListView);
		servers = new ArrayList<Server>();
		servers.add(new Server("Rinse", "192.168.3.45", 7878));
		listAdapter = new ServerAdapter(this, servers);
		
		listView.setAdapter(listAdapter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			AddServerDialogFragment addServerDialogFragment = new AddServerDialogFragment();
			addServerDialogFragment.show(getSupportFragmentManager(), "add_server");
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.server_list, menu);
		return true;
	}

	public void generateButton(View view) {
		Intent intent = new Intent(this, CreateTokenActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    if (DatabaseHelper.getInstance(this) != null) {
	        OpenHelperManager.releaseHelper();
	    }
	}
}
