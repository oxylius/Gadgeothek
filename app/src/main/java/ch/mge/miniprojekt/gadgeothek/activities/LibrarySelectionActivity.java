package ch.mge.miniprojekt.gadgeothek.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

import ch.mge.miniprojekt.gadgeothek.R;

public class LibrarySelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_selection);

    }

    public void clickSetServer(View view) {
        EditText serverAddressElement = (EditText) findViewById(R.id.serverAddress);
        String serverAddress = serverAddressElement.getText().toString();
        if (serverAddress.trim().length() == 0) {
            Toast.makeText(LibrarySelectionActivity.this, "Please enter an Server Address!", Toast.LENGTH_SHORT).show();
        } else {
            LibraryService.setServerAddress(serverAddress);
            Toast.makeText(LibrarySelectionActivity.this, "Set Server Address to: " + LibraryService.getServerAddress(), Toast.LENGTH_SHORT).show();
        }


    }


}
