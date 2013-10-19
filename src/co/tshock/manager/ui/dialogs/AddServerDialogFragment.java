package co.tshock.manager.ui.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import co.tshock.manager.R;
import co.tshock.manager.api.Server;
import co.tshock.manager.util.Utils;

public class AddServerDialogFragment extends BaseDialogFragment {
	private View view;
	private Button addButton;
	private EditText ipEditText;
	private EditText portEditText;
	private EditText displayNameEditText;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int style = DialogFragment.STYLE_NORMAL, theme = 0;
		setStyle(style, theme);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle(R.string.action_settings);
		view = inflater.inflate(R.layout.dialog_add_server, container, false);
		addButton = (Button) view.findViewById(R.id.addButton);
		ipEditText = (EditText) view.findViewById(R.id.ipEditText);
		portEditText = (EditText) view.findViewById(R.id.portEditText);
		displayNameEditText = (EditText) view.findViewById(R.id.displayNameEditText);

		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// The server should be added here
				String ip = ipEditText.getText().toString();
				int port = Utils.parseInt(portEditText.getText().toString(), 7878);
				String displayName = displayNameEditText.getText().toString();
				if (displayName == null || displayName.trim().length() == 0) {
					displayName = String.format("%s:%d", ip, port);
				}
				Server server = new Server(displayName, ip, port);
			}
		});

		return view;
	}
}
