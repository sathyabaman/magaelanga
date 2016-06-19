package baman.lankahomes.lk.magaelanga;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import baman.lankahomes.lk.pathfinder.AddNewLocation;
import baman.lankahomes.lk.pathfinder.R;

public class SelectCoordinates extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final int PLACE_PICKER_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coordinates);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        Toast.makeText(this, "Loading Map. Please wait.", Toast.LENGTH_LONG).show();

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        Context context = getApplicationContext();
        try {
            startActivityForResult(builder.build(context), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String latlng = String.format("Place: %s", place.getLatLng());
                String address = (String) place.getAddress();
                String placeName = (String) place.getName();
                String rating = String.valueOf(place.getRating());
                String phonenumber = (String) place.getPhoneNumber();
                String placeType = String.valueOf(place.getPlaceTypes());
                String priceLevel = String.valueOf(place.getPriceLevel());
                String locale = String.valueOf(place.getLocale());
                String placeURI = String.valueOf(place.getWebsiteUri());
                Log.d("latlng : ", latlng);
                Log.d("address : ", address);
                Log.d("placeName : ", placeName);
                Log.d("rating : ", rating);
                Log.d("phonenumber : ", phonenumber);
                Log.d("placeType : ", placeType);
                Log.d("priceLevel : ", priceLevel);
                Log.d("locale : ", locale);
                Log.d("placeURI : ", placeURI);
                Intent i = new Intent(getApplicationContext(),AddNewLocation.class);
                startActivity(i);
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
