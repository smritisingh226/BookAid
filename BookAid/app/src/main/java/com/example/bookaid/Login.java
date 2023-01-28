package com.example.bookaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email, password;;
    Button login;
    TextView register;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.sign_in);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();
                if(useremail.length() == 0 || userpassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter details to login", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}