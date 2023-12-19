package com.example.country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailedActivity extends AppCompatActivity {
    TextView name, capital, region, population, timezone, currencies;
    String cFlag, cName, cCapital, cRegion, cTimezones, cCurrencies;
    String cPopulation;

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



        Bundle bundle = getIntent().getExtras();

        cFlag = bundle.getString("flags");
        cName = bundle.getString("name");
        cCapital = bundle.getString("capital");
        cRegion = bundle.getString("region");
        cPopulation = bundle.getString("population");
        cTimezones = bundle.getString("timezones");
        cCurrencies = bundle.getString("currencies");


        Glide.with(this).load(cFlag).into(flag);
        name.setText("" + cName);
        capital.setText("Capital: " + cCapital);
        region.setText("Region: " + cRegion);
        population.setText("Population: " + cPopulation);
        timezone.setText("Timezone: " + cTimezones);
        currencies.setText("Currency: " + cCurrencies);

    }
}