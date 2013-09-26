package co.tshock.manager;

import java.net.*;
import java.io.*;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class GenerateToken extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate_token);
		// Show the Up button in the action bar.
		setupActionBar();

		String token = "";
		String json = "";
		int status;
		int c;
		try
		{
			  EditText ip = (EditText) findViewById(R.id.ip_message);
			  EditText port = (EditText) findViewById(R.id.port_message);
			  EditText username = (EditText) findViewById(R.id.username_message);
			  EditText password = (EditText) findViewById(R.id.password_message);
			
			URL hp = new URL("http", ip.getText().toString(), Integer.parseInt(port.getText().toString()), "/token/create/" + username.getText().toString() + "/" + password.getText().toString());
			URLConnection hpCon = hp.openConnection();
			if(hpCon.getContentLength() > 0)
			{
				InputStream input = hpCon.getInputStream();
				int i=hpCon.getContentLength();
				while(((c = input.read()) != -1) && (--i > 0))
				{	
					json = json + ((char) c);
				}
				input.close();
				JSONObject jObject = new JSONObject(json);
				status = jObject.getInt("status");
				if(status == 200)
				{
					token = jObject.getString("token");
					TextView textView = new TextView(this);
					 textView.setText("Token generated successfully. Token: " + token);
					 setContentView(textView);
				}
				else
				{
					String error = jObject.getString("error");
					 TextView textView = new TextView(this);
					 textView.setText("An error occured when generating the token: " + error + ".");
					 setContentView(textView);
				}
				//TokenDialog.onCreateDialog();			
			}
			else//rest API not enabled?
			{
				 TextView textView = new TextView(this);
				 textView.setText("An unknown error occured.");
				 setContentView(textView);	
			}
		}
		catch(Exception ex)//cannot connect.
		{
			 TextView textView = new TextView(this);
			 textView.setText("An error occured when connecting to the server: " + ex.toString() + ".");
			 setContentView(textView);
		}
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
}