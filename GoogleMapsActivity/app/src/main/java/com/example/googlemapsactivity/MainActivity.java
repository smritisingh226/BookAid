package com.example.googlemapsactivity;

//import static androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.Table.map;

import static com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private ArrayList<Country> countryList;

    double latitude, logitude;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(this);
        countryList = new ArrayList<>();

        fetchCountries();


    }



    private void fetchCountries() {

        String url = "https://restcountries.com/v2/all";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                // Extract movie information from each JSON object
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                title = jsonObject.getString("name");
                                String capital = jsonObject.getString("capital");
                                String region = jsonObject.getString("region");
                                String population = jsonObject.getString("population");


                                //Fetching values from array within the jsonObject
                                JSONArray currencyName = jsonObject.getJSONArray("currencies");
                                String currency = processCurrencies(currencyName);

                                //Fetching values from an object within the json object
                                JSONObject flagObject = jsonObject.getJSONObject("flags");
                                String imageUrl = processFlags(flagObject);

                                JSONArray timezoneArray = jsonObject.getJSONArray("timezones");
                                String timezone = processTimezones(timezoneArray);

                                //Fetching values for LatLang
                                JSONArray locationArray = jsonObject.getJSONArray("latlng");
                                latitude = locationArray.optDouble(0, 0.0);
                                logitude = locationArray.optDouble(1, 0.0);


                                Country country = new Country(title, capital, imageUrl, region, timezone, currency, population, latitude, logitude);
                                countryList.add(country);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            CountryAdapter adapter = new CountryAdapter(MainActivity.this, countryList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }


    private String processTimezones(JSONArray timezoneArray) {
        try {
            List<String> timezoneList = new ArrayList<>();
            for (int i = 0; i < timezoneArray.length(); i++) {
                String timezone = timezoneArray.getString(i);
                timezoneList.add(timezone);
                return TextUtils.join(", ", timezoneList);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String processFlags(JSONObject flagObject) {
        try {
            String flagpngUrl = flagObject.getString("png");
            return flagpngUrl;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private String processCurrencies(JSONArray currencyName) {
        try {
            for (int i = 0; i < currencyName.length(); i++) {
                JSONObject currencyObject = currencyName.getJSONObject(i);

                String currency = currencyObject.getString("name");
                return currency;

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
