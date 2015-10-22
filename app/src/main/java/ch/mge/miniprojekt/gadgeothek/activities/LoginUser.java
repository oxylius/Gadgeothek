package ch.mge.miniprojekt.gadgeothek.activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class LoginUser extends GadgeothekMain {

    final String SET_SERVER = "navigation_item_set_server";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Login");
        setContentView(R.layout.activity_login_user);

        Button lButton  = (Button) findViewById(R.id.logButton);
        Button rButton = (Button) findViewById(R.id.regButton);

        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tvEmail = (EditText) findViewById(R.id.EditTextMail);
                EditText tvPassword = (EditText) findViewById(R.id.EditTextPassword);
                final String email = tvEmail.getText().toString();
                String password = tvPassword.getText().toString();
                try {
                    LibraryService.login(email, password, new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean success) {
                            if (success) {
                                // Jetzt sind wir eingeloggt
                                TextView loginName = (TextView)findViewById(R.id.drawer_header_login_name);
                                loginName.setText(email);
                                Toast.makeText(LoginUser.this, "Logged in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginUser.this, LoansActivity.class);
                                startActivity(intent);
                            } else {
                                // Passwort war falsch oder User unbekannt.
                                Toast.makeText(LoginUser.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(LoginUser.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Exception", "Login");
                }
            }
        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUser.this, RegisterUser.class);
                startActivity(intent);
            }
        });

        //password lButton input validation
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
    }
}
