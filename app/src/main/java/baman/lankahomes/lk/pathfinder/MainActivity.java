package baman.lankahomes.lk.pathfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import baman.lankahomes.lk.magaelanga.SelectCoordinates;


public class MainActivity extends ActionBarActivity {
    Button search_now;
    private Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public ListView drawerList;
    private navigationDrawerFragment drawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("pathRegistration", MODE_PRIVATE);
        boolean isloggedin = prefs.getBoolean("is_logged_in", false);
        if(!isloggedin) {
            startActivity(new Intent(MainActivity.this, SignIn.class));
        }

        setContentView(R.layout.activity_main_screen);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (navigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        search_now = (Button) findViewById(R.id.btn_search_now);
        search_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchResults.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(),Settings.class);
            startActivity(i);
            return true;
        }
        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.favorite){
            Intent i = new Intent(getApplicationContext(),Activity_favourite.class);
            startActivity(i);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.addNewLocation){
            Intent i = new Intent(getApplicationContext(),SelectCoordinates.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
