package gfortin.life.dstl.activity;


import android.content.Context;
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

import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.fragment.ItemFragment;
import gfortin.life.dstl.fragment.SorceryFragment;
import gfortin.life.dstl.fragment.TrophyFragment;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.util.FragmentUtil;

public class MainActivity extends AppCompatActivity{

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();

        dbHelper = DatabaseHelper.getInstance(getApplicationContext());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            List<Game> games = dbHelper.getGameDao().queryForAll();
        }catch (Exception e){
            throw new RuntimeException();
        }

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
}
