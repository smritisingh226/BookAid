package com.example.googlemapsactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    TextView name, capital, region, population, timezone, currencies, location;
    String cFlag, cName, cCapital, cRegion, cTimezones, cCurrencies;
    String cPopulation, cLocation;

    Double cLatitude, cLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ImageView flag = findViewById(R.id.country_flag);
        name = findViewById(R.id.country_name);
        capital = findViewById(R.id.country_capital);
        region = findViewById(R.id.country_region);
        population = findViewById(R.id.country_population);
        timezone = findViewById(R.id.country_timezone);
        currencies = findViewById(R.id.country_currency);
        location = findViewById(R.id.country_location);


        Bundle bundle = getIntent().getExtras();

        cFlag = bundle.getString("flags");
        cName = bundle.getString("name");
        cCapital = bundle.getString("capital");
        cRegion = bundle.getString("region");
        cPopulation = bundle.getString("population");
        cTimezones = bundle.getString("timezones");
        cCurrencies = bundle.getString("currencies");
        cLatitude = bundle.getDouble("latitude");
        cLongitude = bundle.getDouble("longitude");



        Glide.with(this).load(cFlag).into(flag);
        name.setText("" + cName);
        capital.setText("Capital: " + cCapital);
        region.setText("Region: " + cRegion);
        population.setText("Population: " + cPopulation);
        timezone.setText("Timezone: " + cTimezones);
        currencies.setText("Currency: " + cCurrencies);
        location.setText("Location: " + cLatitude + ", " + cLongitude);
    }
}
