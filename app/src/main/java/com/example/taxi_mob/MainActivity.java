package com.example.taxi_mob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    ImageView imageView;
    AlertDialog al;
    AlertDialog.Builder ad;
    EditText editTextNew;
    EditText editTextAdd;
    static final String NEWS = "news";
    static final String ADD = "add";


    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;
        ///////////////////

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShareMenu(view);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        //drawer.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        editTextNew = (EditText)findViewById(R.id.edTextNew);
        editTextAdd = (EditText)findViewById(R.id.edTextAdd);
    }

    public void showShareMenu(View v){
        PopupMenu shareMenu = new PopupMenu(this,v);
        shareMenu.inflate(R.menu.sharemenu);
        shareMenu.show();
    }


    public void onClickYES(View view) {
        finish();
    }

    public void onClickNo(View view) {
        al.cancel();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_about:
                ad = new AlertDialog.Builder(context);
                ad.setView(R.layout.about_layout);
                //////////////////////////
                ad.setCancelable(false);
                al = ad.create();
                al.show();

                return true;
            case R.id.menu_buy:
                Toast.makeText(getApplicationContext(),
                        "Ok, just give me a dollar",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_exit:
                ad = new AlertDialog.Builder(context);

                ad.setView(R.layout.exit_layout);
                //////////////////////////
                ad.setCancelable(false);
                al = ad.create();
                al.show();

                return true;
            default:
                return false;

        }

        //return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_bio) {
            //

        } else if (id == R.id.nav_tags) {

        } else if (id == R.id.nav_sett) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_taxi) {
            Intent intent = new Intent(MainActivity.this, ScreenActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putString(NEWS, editTextNew.getText().toString());
        saveInstanceState.putString(ADD, editTextAdd.getText().toString());

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editTextNew.setText(savedInstanceState.getString(NEWS));
        editTextAdd.setText(savedInstanceState.getString(ADD));

    }


}
