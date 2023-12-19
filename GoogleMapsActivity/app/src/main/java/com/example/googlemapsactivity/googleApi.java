package com.example.googlemapsactivity;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class googleApi extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap1;   //for map API
    private FusedLocationProviderClient mFusedLocationClient;
    private int FINE_PERMISSION_CODE = 44;
    Double cLatitude, cLongitude;
    String cName;


    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_api);
        Intent intent = getIntent();


        cLatitude = intent.getDoubleExtra("latitude", 0.0);

        cLongitude = intent.getDoubleExtra("longitude", 0.0);
        cName = intent.getStringExtra("name");



       //for map API
        if(isGooglePlayServicesAvailable()){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            Toast.makeText(this, "Google Play services are not available",Toast.LENGTH_LONG).show();
        }



//current location project
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION}, FINE_PERMISSION_CODE);
        }else{
        getLocation();
        }
    }


//    for map API
    private boolean isGooglePlayServicesAvailable(){
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        return availability == ConnectionResult.SUCCESS;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap1 = googleMap;
        if(googleMap1!=null) {
            LatLng location = new LatLng(cLatitude, cLongitude);
            googleMap.addMarker(new MarkerOptions().position(location).title(cName));

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
        }
    }
}