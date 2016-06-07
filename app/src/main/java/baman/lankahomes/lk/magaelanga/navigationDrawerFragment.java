package baman.lankahomes.lk.magaelanga;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class navigationDrawerFragment extends android.support.v4.app.Fragment implements VivzAdapter.ClickListener{

    private RecyclerView recyclerView;
    private VivzAdapter adapter;
    public static final String PREF_FILE_Name = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    static List<Information> data = new ArrayList<>();

    public navigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,  "false"));
        if(savedInstanceState != null){
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new VivzAdapter(getActivity(), getData());
        adapter.setClicklistner(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<Information> getData(){
        if(data.size() ==0){
            int[] icons = {
                    R.drawable.atm,
                    R.drawable.bank,
                    R.drawable.bar,
                    R.drawable.beauty_saloon,
                    R.drawable.book_store,
                    R.drawable.buddhist_temple,
                    R.drawable.bus_station,
                    R.drawable.cafe,
                    R.drawable.church,
                    R.drawable.clothing_store,
                    R.drawable.departmental_store,
                    R.drawable.electronic_store,
                    R.drawable.fitness_center,
                    R.drawable.florist,
                    R.drawable.gas_station,
                    R.drawable.hindu_temple,
                    R.drawable.hospital,
                    R.drawable.hotels,
                    R.drawable.library,
                    R.drawable.liquor_store,
                    R.drawable.mosque,
                    R.drawable.movie_theatre,
                    R.drawable.museum,
                    R.drawable.night_club,
                    R.drawable.park,
                    R.drawable.pharmacy,
                    R.drawable.police_station,
                    R.drawable.post_office,
                    R.drawable.restaurant,
                    R.drawable.school,
                    R.drawable.shopping_mall,
                    R.drawable.spa,
                    R.drawable.university,
                    R.drawable.zoo,
                    R.drawable.add_new_location,};

            String[] titles = {"ATM","Bank","Bar","Beauty Saloon", "Book Store","Buddhist Temple",
                    "Bus Station","Cafe","Church","clothing Store", "Departmental Store", "Electronic Store",
                    "Fitness Center", "Florist", "Gas Station", "Hindu Temples", "Hospitals", "Hotels", "Library", "liquor Store",
                    "Mosque", "Movie Theatre", "Museum", "Night Club", "Park", "Pharmacy", "Police Station",
                    "Post Office", "Restaurant", "School", "Shopping Mall", "Spa", "University", "Zoo", "Add New Location",};

            for (int k=0; k < icons.length && k < titles.length; k++){
                Information current = new Information();
                current.iconId = icons[k];
                current.title = titles[k];
                data.add(current);
            }
        }

        return data;
    }


    public void setup(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView =getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close ){
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.3) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };

        // Checking if the user is first time  user and show him we have a drawer
        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // add a hamburger icon to toggle drawer
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences (Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_Name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_Name, context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {

//        Toast.makeText(getActivity(), "pisition"+ Integer.toString(position)+" view id :"+ String.valueOf(data.get(position).getTitle()),
//                Toast.LENGTH_LONG).show();

        String clicked = String.valueOf(data.get(position).getTitle());
        Intent intent = new Intent(getActivity(), Houses.class);

        switch (clicked){
            case "Search":
                Intent intent2 = new Intent(getActivity(), MainActivity.class);
                intent2.putExtra("activity_title", clicked);
                startActivity(intent2);
                break;
            case "Add New Location":
                Intent intent3 = new Intent(getActivity(), AddNewLocation.class);
                intent3.putExtra("activity_title", clicked);
                startActivity(intent3);
                break;
            default:
                intent.putExtra("activity_title", clicked);
                startActivity(intent);
                break;
        }

    }
}


