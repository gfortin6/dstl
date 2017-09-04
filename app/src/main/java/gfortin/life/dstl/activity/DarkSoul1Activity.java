package gfortin.life.dstl.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import gfortin.life.dstl.R;
import gfortin.life.dstl.fragment.ItemFragment;
import gfortin.life.dstl.fragment.SorceryFragment;
import gfortin.life.dstl.fragment.TrophyFragment;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.util.FragmentUtil;

public class DarkSoul1Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SorceryFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = DatabaseHelper.getInstance(getApplicationContext());

        setContentView(R.layout.activity_dark_soul1);
        setTitle(R.string.dark_souls_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0 ) {
                getSupportFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar Item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }/*else if (id == R.id.action_locale) {
            CharSequence lang = item.getTitle();
            if(lang == getResources().getText(R.string.fr)){
                LanguageHelper.changeLocale(getResources(),"fr");
             //   ClipData.Item actionLocale = (ClipData.Item) findViewById(R.id.action_locale);
                //actionLocale.set
            }else {
                LanguageHelper.changeLocale(getResources(), "en");
            }
        }*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view Item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_sorceries:
                FragmentUtil.createNewFragment(new ItemFragment(), getSupportFragmentManager(),"itemId", item.getItemId(), getResources().getBoolean(R.bool.twoPaneMode));
                break;
            case R.id.nav_miracles:
                break;
            case R.id.nav_weapons:
                break;
            case R.id.nav_rings:
                break;
            case R.id.nav_trophies:
                FragmentUtil.createNewFragment(new ItemFragment(), getSupportFragmentManager(),"itemId", item.getItemId(), getResources().getBoolean(R.bool.twoPaneMode));
                break;
            case R.id.nav_change_game:
                startActivity(new Intent(DarkSoul1Activity.this, MainActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    public void onListFragmentInteraction(Item item){
        //you can leave it empty
    }


}
