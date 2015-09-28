package ch.mge.miniprojekt.gadgeothek.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class registerUser extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Register");
        setContentView(R.layout.activity_register_user);

        final EditText password = (EditText) findViewById(R.id.EditTextPassword);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pw = s.toString();
                if (s.length() < 5) {
                    password.setError("Passwort muss min. 5 Zeichen lang sein.");
                }
            }
        });

        Button rButton = (Button) findViewById(R.id.regButton);

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tvEmail = (EditText) findViewById(R.id.EditTextMail);
                EditText tvPassword = (EditText) findViewById(R.id.EditTextPassword);
                EditText tvName = (EditText) findViewById(R.id.EditTextName);
                EditText tvMatrikelnumber = (EditText) findViewById(R.id.EditTextMatrikelnumber);
                String email = tvEmail.getText().toString();
                String password = tvPassword.getText().toString();
                String name = tvName.getText().toString();
                String studentenNummer = tvMatrikelnumber.getText().toString();
                LibraryService.register(email, password, name, studentenNummer, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean success) {
                        if (success) {
                            // Jetzt sind wir eingeloggt
                            Toast.makeText(registerUser.this, "Registrierung erfolgreich!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Passwort war falsch oder User unbekannt.
                            Toast.makeText(registerUser.this, "Registrierung fehlgeschlagen!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(registerUser.this, "Registrierung Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
