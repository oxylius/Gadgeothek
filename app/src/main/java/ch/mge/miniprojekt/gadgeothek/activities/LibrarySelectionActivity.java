package ch.mge.miniprojekt.gadgeothek.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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

    }


    public void clickSetServer(View view) {

        AutoCompleteTextView serverAddressElement = (AutoCompleteTextView) findViewById(R.id.serverAddressAutoComplete);
        String serverAddress = serverAddressElement.getText().toString();
        if (serverAddress.trim().length() == 0) {
            Toast.makeText(LibrarySelectionActivity.this, "Please enter an Server Address!", Toast.LENGTH_SHORT).show();
        } else {
            LibraryService.setServerAddress(serverAddress);
            Toast.makeText(LibrarySelectionActivity.this, "Set Server Address to: " + LibraryService.getServerAddress(), Toast.LENGTH_SHORT).show();
        }


    }


}
