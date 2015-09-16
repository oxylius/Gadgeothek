package ch.mge.miniprojekt.gadgeothek.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

import ch.mge.miniprojekt.gadgeothek.R;

public class LibrarySelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_selection);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_library);
        }

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
