package com.example.taxi_mob;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;


//свое окно

public class ScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final String FROM = "from place";
    static final String TO = "to place";

    Context context;
    EditText editTextFrom;
    EditText editTextTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_taxi);
        setSupportActionBar(toolbar);

        context = ScreenActivity.this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_screen);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_screen);
        navigationView.setNavigationItemSelectedListener(this);

        editTextFrom = (EditText)findViewById(R.id.from_place_id);
        editTextTo = (EditText)findViewById(R.id.end_place_id);

        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_screen);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addresses) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_callback) {

        } else if (id == R.id.nav_operator) {

        } else if (id == R.id.nav_history) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_screen);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putString(FROM, editTextFrom.getText().toString());
        saveInstanceState.putString(TO, editTextTo.getText().toString());

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editTextFrom.setText(savedInstanceState.getString(FROM));
        editTextTo.setText(savedInstanceState.getString(TO));

    }
}
