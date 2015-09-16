package ch.mge.miniprojekt.gadgeothek.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        EditText serverAddress = (EditText) findViewById(R.id.serverAddress);
        LibraryService.setServerAddress(serverAddress.getText().toString());
        Toast.makeText(LibrarySelectionActivity.this, "Successfully set Server", Toast.LENGTH_SHORT).show();

    }


}
