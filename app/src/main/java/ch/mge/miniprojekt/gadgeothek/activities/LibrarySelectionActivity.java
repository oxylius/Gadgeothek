package ch.mge.miniprojekt.gadgeothek.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

import ch.mge.miniprojekt.gadgeothek.R;

public class LibrarySelectionActivity extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Set Server");
        setContentView(R.layout.activity_library_selection);


        //Autocomplete TextView
        AutoCompleteTextView serverAddressElement = (AutoCompleteTextView) findViewById(R.id.serverAddressAutoComplete);
        String[] defaultServers = getResources().getStringArray(R.array.default_servers_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, defaultServers);
        serverAddressElement.setAdapter(adapter);
        if(LibraryService.getServerAddress() != null)
            serverAddressElement.setText(LibraryService.getServerAddress());
    }


    public void clickSetServer(View view) {

        AutoCompleteTextView serverAddressElement = (AutoCompleteTextView) findViewById(R.id.serverAddressAutoComplete);
        String serverAddress = serverAddressElement.getText().toString();
        if (serverAddress.trim().length() == 0) {
            Toast.makeText(LibrarySelectionActivity.this, "Please enter an Server Address!", Toast.LENGTH_SHORT).show();
        } else {
            serverAddress = "http://" + serverAddress;
            LibraryService.setServerAddress(serverAddress);
            Toast.makeText(LibrarySelectionActivity.this, "Set Server Address to: " + LibraryService.getServerAddress(), Toast.LENGTH_SHORT).show();
        }


    }


}
