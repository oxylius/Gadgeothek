package ch.mge.miniprojekt.gadgeothek.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Button;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.Callback;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LogoutUser extends GadgeothekMain {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle("Logout");
        setContentView(R.layout.activity_logout_user);

        //Button logoutButton = (Button) findViewById(R.id.logoutButton);

        final TextView tvDrawerHeaderLogin = (TextView) findViewById(R.id.drawer_header_login_name);

        try {
            LibraryService.logout(new Callback<Boolean>() {

                @Override
                public void onCompletion(Boolean success) {
                    if (success) {
                        // Jetzt sind wir ausgelogt
                        tvDrawerHeaderLogin.setText("Logged out");
                        //setLoginText("Logged out");
                        //Snackbar.make(v, "Logged out", Snackbar.LENGTH_LONG).show();
                    } else {
                        // Passwort war falsch oder User unbekannt.
                        //Snackbar.make(v, "Logout failed", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(LogoutUser.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.d("Exception", "Logout");
        }
    }
}
