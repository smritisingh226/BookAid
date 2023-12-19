package com.example.clickshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.clickshop.MainActivity;
import com.example.clickshop.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    LinearLayout mainSignIn, mainSignUp;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();


        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

//        if(auth.getCurrentUser() != null){
//            progressBar.setVisibility(View.VISIBLE);
//            startActivity(new Intent(HomeActivity.this, MainActivity.class));
//            Toast.makeText(this, "Please Wait, you Are Already Logged In ", Toast.LENGTH_LONG).show();
//            finish();
//        }

        mainSignIn = findViewById(R.id.main_signin);
        mainSignUp = findViewById(R.id.main_signup);

        mainSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        mainSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
            }
        });
    }
}