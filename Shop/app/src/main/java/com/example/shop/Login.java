package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button login;
    EditText userpassword, useremailid;
    ImageView showpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.Login);
        userpassword = findViewById(R.id.password);
        useremailid = findViewById(R.id.emailid);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(useremailid.getText().toString().equals("admin@gmail.com") && userpassword.getText().toString().equals("admin"))
                {
                    Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PasswordSetting.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Unsuccessfully attempt", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}