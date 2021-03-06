package baman.lankahomes.lk.pathfinder;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddNewLocation extends ActionBarActivity {

    private Toolbar toolbar;
    private navigationDrawerFragment drawerFragment;
    EditText sub_cordinates;
    EditText sub_title;
    EditText sub_address;
    EditText sub_phone;
    EditText sub_website;


    public static final String PREF_FILE_Name = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* ------------------- Block the navigation drawer at start ---------------------------------*/
        mUserLearnedDrawer = true;
        drawerFragment.saveToPreferences(this, KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
        /* ------------------------------------------------------------------------------------------*/

        setContentView(R.layout.activity_add_new_location);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (navigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);



        Intent intent = getIntent();
        String latlng = intent.getStringExtra("latlng");
        String placeName = intent.getStringExtra("placeName");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phonenumber");
        String website = intent.getStringExtra("placeURI");

        String new_latlng = latlng.substring(latlng.indexOf("(")+1,latlng.indexOf(")"));
        sub_cordinates = (EditText) findViewById(R.id.et_sub_coordinates);
        sub_title = (EditText) findViewById(R.id.et_sub_title);
        sub_address = (EditText) findViewById(R.id.et_submit_address);
        sub_phone = (EditText) findViewById(R.id.et_sub_phone);
        sub_website = (EditText) findViewById(R.id.et_submit_website);


        /* validating place name */
        String s1 = new_latlng.substring(1,4).replace(".", "");
        String s2 = placeName.substring(2,5).replace(".", "");
        Log.d("latlng : ", new_latlng);
        Log.d("place  : ", placeName);
        Log.d("latlng : ", s1);
        Log.d("place  : ", s2);

        /* Asigning values to text box */
        sub_cordinates.setText(new_latlng);
        if(!(s1.equals(s2) && s2.equals(s1))){sub_title.setText(placeName); }
        if(!(address.equals("null"))) {sub_address.setText(address); }
        if(!(phone.equals("null"))) {sub_phone.setText(phone); }
        if(!(website.equals("null"))){sub_website.setText(website); }

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


        return super.onOptionsItemSelected(item);
    }
}
