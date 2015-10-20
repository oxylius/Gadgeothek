package ch.mge.miniprojekt.gadgeothek.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class loginUser extends GadgeothekMain {

    final String SET_SERVER = "navigation_item_set_server";
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Login");
        setContentView(R.layout.activity_login_user);

        Button lButton  = (Button) findViewById(R.id.logButton);
        Button rButton = (Button) findViewById(R.id.regButton);

        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextInputLayout tiEmail = (TextInputLayout) findViewById(R.id.EditTextMail);
                TextInputLayout tiPassword = (TextInputLayout) findViewById(R.id.EditTextPassword);
                final String email = tiEmail.getEditText().getText().toString();
                String password = tiPassword.getEditText().getText().toString();
                LibraryService.login(email, password, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean success) {
                        if (success) {
                            // Jetzt sind wir eingeloggt
                            Snackbar.make(v, "Logged in", Snackbar.LENGTH_LONG).show();
                        } else {
                            // Passwort war falsch oder User unbekannt.
                            Snackbar.make(v, "Login failed", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Snackbar.make(v, "error", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginUser.this, registerUser.class);
                startActivity(intent);
            }
        });

        //password lButton input validation
        final TextInputLayout password = (TextInputLayout) findViewById(R.id.EditTextPassword);
        password.getEditText().addTextChangedListener(new TextWatcher() {
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
                } else {
                    password.setErrorEnabled(false);
                }
            }
        });
    }
}
