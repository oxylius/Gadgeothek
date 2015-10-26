package ch.mge.miniprojekt.gadgeothek.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import ch.mge.miniprojekt.gadgeothek.R;

public class LibrarySelectionActivity extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Set Server");
        setContentView(R.layout.activity_library_selection);

        AutoCompleteTextView serverAddressElement = (AutoCompleteTextView) findViewById(R.id.serverAddressAutoComplete);
        String[] defaultServers = getResources().getStringArray(R.array.default_servers_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, defaultServers);
        serverAddressElement.setAdapter(adapter);
        if(GadgeothekMain.getServerAddress().equals("missing"))
            serverAddressElement.setText(GadgeothekMain.getServerAddress().substring(7));
    }


    public void clickSetServer(View view) {

        AutoCompleteTextView serverAddressElement = (AutoCompleteTextView) findViewById(R.id.serverAddressAutoComplete);
        String serverAddress = serverAddressElement.getText().toString();
        if (serverAddress.trim().length() == 0) {
            Snackbar.make(view, "Please enter a Server Address!", Snackbar.LENGTH_LONG).show();
        } else {
            serverAddress = "http://" + serverAddress;
            GadgeothekMain.setServerAddress(serverAddress);
            Snackbar.make(view, "Set Server Address to: " + GadgeothekMain.getServerAddress().substring(7), Snackbar.LENGTH_LONG).show();
        }


    }


}
