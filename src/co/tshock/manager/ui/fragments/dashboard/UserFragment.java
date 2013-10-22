package co.tshock.manager.ui.fragments.dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.tshock.manager.R;
import co.tshock.manager.api.Server;
import co.tshock.manager.api.TShockApi;
import co.tshock.manager.data.adapter.UserAdapter;
import co.tshock.manager.data.models.User;
import co.tshock.manager.events.Event;
import co.tshock.manager.events.EventManager;
import co.tshock.manager.events.EventType;
import co.tshock.manager.events.EventListener;

public class UserFragment extends BaseDashboardFragment implements EventListener {
    private static final EventType[] types = new EventType[] { EventType.ERROR };

    private ListView listView;
    private List<User> userList;
    private UserAdapter listAdapter;

    @Override
    public void onSuccessfulLogin(Server server) {
        this.refreshUserList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

        this.userList = new ArrayList<User>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listAdapter = new UserAdapter(this.getActivity(), userList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                this.refreshUserList();;
                break;
            case R.id.action_add:
                // TODO Add a user dialog
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.user, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void refreshUserList() {
        // TODO Refresh the user list
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        this.listView = (ListView) v.findViewById(R.id.userListView);
        this.listView.setAdapter(listAdapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Show extra info about the user
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventManager.getInstance().registerListener(types, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventManager.getInstance().unregisterListener(types, this);
    }

    @Override
    public void onEvent(Event event) {
        switch (event.getType()) {
            case SERVER_STATUS:

                break;
        }

    }
}
