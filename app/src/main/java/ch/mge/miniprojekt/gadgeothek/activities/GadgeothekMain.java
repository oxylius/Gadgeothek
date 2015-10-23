package ch.mge.miniprojekt.gadgeothek.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import ch.mge.miniprojekt.gadgeothek.R;
import ch.mge.miniprojekt.gadgeothek.service.LibraryService;


public class GadgeothekMain extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private String activityTitle;
    static SharedPreferences mSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
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

    @Override
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_gadgeothek_main, null);
        mDrawerLayout = fullView;
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener(){

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                InputMethodManager inputMethodManager = (InputMethodManager) GadgeothekMain.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(GadgeothekMain.this.getCurrentFocus().getWindowToken(), 0);
            }
        });

        super.setContentView(fullView);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch(menuItem.getItemId()) {
                    case R.id.navigation_item_set_server:
                        startActivity(new Intent(GadgeothekMain.this, LibrarySelectionActivity.class));
                        break;
                    case R.id.navigation_item_login:
                        startActivity(new Intent(GadgeothekMain.this, LoginUser.class));
                        break;
                    case R.id.navigation_item_register:
                        startActivity(new Intent(GadgeothekMain.this, RegisterUser.class));
                        break;
                    case R.id.navigation_item_reservation:
                        if(LibraryService.isLoggedIn())
                            startActivity(new Intent(GadgeothekMain.this, ReservationActivity.class));
                        else Snackbar.make(findViewById(R.id.activity_container), "Not Logged in!", Snackbar.LENGTH_SHORT).setAction("Login", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(GadgeothekMain.this, LoginUser.class));
                            }
                        }).show();
                        break;
                    case R.id.navigation_item_loan:
                        if(LibraryService.isLoggedIn())
                            startActivity(new Intent(GadgeothekMain.this, LoansActivity.class));
                        else Snackbar.make(findViewById(R.id.activity_container), "Not Logged in!", Snackbar.LENGTH_SHORT).setAction("Login", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(GadgeothekMain.this, LoginUser.class));
                            }
                        }).show();
                        break;
                    default:
                        Toast.makeText(GadgeothekMain.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        setTitle(activityTitle);
    }

    public void setActivityTitle(String s) {
        activityTitle = s;
    }

    public static String getServerAddress() {
        return mSettings.getString("ServerAddress", "missing");
    }

    public static void setServerAddress(String serverAddress) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("ServerAddress", serverAddress);
        editor.apply();
        Log.d("SharedPreferences", "Setting server to " + serverAddress);
    }

}
