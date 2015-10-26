package ch.mge.miniprojekt.gadgeothek.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class LoginUser extends GadgeothekMain {

    Handler mHandler = new Handler();
    private Runnable mLaunchTask = new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),LoansActivity.class);
            startActivity(i);
        }
    };

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Login");
        setContentView(R.layout.activity_login_user);

        Button lButton  = (Button) findViewById(R.id.logButton);
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            
            public void onClick(final View v) {
                TextInputLayout tiEmail = (TextInputLayout) findViewById(R.id.EditTextMail);
                TextInputLayout tiPassword = (TextInputLayout) findViewById(R.id.EditTextPassword);
                final String email = tiEmail.getEditText().getText().toString();
                String password = tiPassword.getEditText().getText().toString();
                try {
                    LibraryService.login(email, password, new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean success) {
                            if (success) {
                                changeDrawerHeader(email);
                                Snackbar.make(v, "Logged in as: " + email, Snackbar.LENGTH_LONG).show();
                                mHandler.postDelayed(mLaunchTask, 1000);
                            } else {
                                Snackbar.make(v, "Login failed", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Snackbar.make(v, "Login Error", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Exception", "Login");
                }
            }
        });

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
                if (s.length() < 5) {
                    password.setError("Passwort muss min. 5 Zeichen lang sein.");
                } else {
                    password.setErrorEnabled(false);
                }
            }
        });
    }
}
