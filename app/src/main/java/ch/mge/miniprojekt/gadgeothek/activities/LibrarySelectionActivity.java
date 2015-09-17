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

public class LibrarySelectionActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_selection);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                Toast.makeText(LibrarySelectionActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                return true;
                }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Autocomplete TextView
        AutoCompleteTextView serverAddressElement = (AutoCompleteTextView) findViewById(R.id.serverAddressAutoComplete);
        String[] defaultServers = getResources().getStringArray(R.array.default_servers_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, defaultServers);
        serverAddressElement.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
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
