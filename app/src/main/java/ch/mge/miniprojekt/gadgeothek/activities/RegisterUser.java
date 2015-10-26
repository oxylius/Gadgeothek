package ch.mge.miniprojekt.gadgeothek.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

public class RegisterUser extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Register");
        setContentView(R.layout.activity_register_user);

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

        Button rButton = (Button) findViewById(R.id.regButton);

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextInputLayout tvEmail = (TextInputLayout) findViewById(R.id.EditTextMail);
                TextInputLayout tvPassword = (TextInputLayout) findViewById(R.id.EditTextPassword);
                TextInputLayout tvName = (TextInputLayout) findViewById(R.id.EditTextName);
                TextInputLayout tvMatrikelnumber = (TextInputLayout) findViewById(R.id.EditTextMatrikelnumber);
                String email = tvEmail.getEditText().getText().toString();
                String password = tvPassword.getEditText().getText().toString();
                String name = tvName.getEditText().getText().toString();
                String studentenNummer = tvMatrikelnumber.getEditText().getText().toString();
                try {
                    LibraryService.register(email, password, name, studentenNummer, new Callback<Boolean>() {
                        @Override
                        public void onCompletion(Boolean success) {
                            if (success) {
                                Snackbar.make(v, "Registrierung erfolgreich", Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(v, "Registrierung fehlgeschlagen", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Snackbar.make(v, "Registrierung error!", Snackbar.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Exception", "register");
                }
            }
        });
    }
}
