/**
 * 
 */
package co.tshock.manager.data.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import co.tshock.manager.R;
import co.tshock.manager.api.Server;

/**
 * An adapter for {@link Server} objects
 * 
 * @author koesie10
 */
public class ServerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Server> servers;

	public ServerAdapter(Context context, ArrayList<Server> servers) {
		super();
		this.context = context;
		this.servers = servers;
	}

	/**
	 * A holder which saves the views so we have more speed ;)
	 * 
	 * @author koesie10
	 * 
	 */
	static class ViewHolder {
		private TextView displayName;
		private TextView host;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount() {
		return servers.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getItem(int position) {
		return servers.get(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = inflater
					.inflate(R.layout.server_row, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.displayName = (TextView) view.findViewById(R.id.displayNameTextView);
			viewHolder.host = (TextView) view.findViewById(R.id.hostTextView);
			view.setTag(viewHolder);
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		
		Server server = servers.get(position);
		
		holder.displayName.setText(server.getDisplayName());
		holder.host.setText(String.format("%s:%d", server.getIp(), server.getPort()));
		return view;
	}

}
