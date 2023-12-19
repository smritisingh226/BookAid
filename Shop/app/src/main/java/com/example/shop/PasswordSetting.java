package com.example.shop;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PasswordSetting extends AppCompatActivity {


    EditText etName, etEmail, etPassword;
    CardView cardOne, cardTwo, cardThree, cardFour, btnregister;
    private boolean isAtleast8 = false, hasUpper = false, hasNumber = false, hasSymbol = false, isRegisterClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_setting);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        cardOne = findViewById(R.id.cardOne);
        cardTwo = findViewById(R.id.cardTwo);
        cardThree = findViewById(R.id.cardThree);
        cardFour = findViewById(R.id.cardFour);

        btnregister = findViewById(R.id.btnregister);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString(),
                        email = etEmail.getText().toString(),
                        password = etPassword.getText().toString();

                if(name.length() > 0 && email.length() > 0 && password.length() >0){
                    if(isRegisterClick){
                        Toast.makeText(PasswordSetting.this, "Password Validated", Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(name.length() == 0) {
                        Toast.makeText(PasswordSetting.this, "Name cannot be null", Toast.LENGTH_LONG).show();
                    }
                    if(password.length() == 0){
                        Toast.makeText(PasswordSetting.this, "Password cannot be null", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        inputChange();
    }


    private void passwordCheck(){
        String name = etName.getText().toString(),
                email = etEmail.getText().toString(),
                password = etPassword.getText().toString();

        if(password.length() >= 8){
            isAtleast8 = true;
            cardOne.setCardBackgroundColor(Color.parseColor("#008000"));
        }
        else{
            isAtleast8 = false;
            cardOne.setCardBackgroundColor(Color.parseColor("#DE3163"));
        }

        //for uppercase
        if(password.matches("(.*[A-Z].*)")){
            hasUpper = true;
            cardThree.setCardBackgroundColor(Color.parseColor("#008000"));
        }
        else{
            hasUpper = false;
            cardThree.setCardBackgroundColor(Color.parseColor("#DE3163"));
        }

        //for number
        if(password.matches("(.*[0-9].*)")){
            hasNumber = true;
            cardTwo.setCardBackgroundColor(Color.parseColor("#008000"));
        }
        else{
            hasNumber = false;
            cardTwo.setCardBackgroundColor(Color.parseColor("#DE3163"));
        }

        //for symbol
        if(password.matches("^(?=.*[_.()]).*$")){
            hasSymbol = true;
            cardFour.setCardBackgroundColor(Color.parseColor("#008000"));
        }
        else{
            hasSymbol = false;
            cardFour.setCardBackgroundColor(Color.parseColor("#DE3163"));
        }

        checkAllData(email);

    }

    @SuppressLint("ResourceType")
    private void checkAllData(String email){
        if (isAtleast8 && hasUpper && hasNumber && hasSymbol && email.length() >0){
            isRegisterClick = true;
            btnregister.setCardBackgroundColor(Color.parseColor("#008000"));
        }
        else{
            isRegisterClick = false;
            btnregister.setCardBackgroundColor(Color.parseColor("#DE3163"));
        }
    }

    private void inputChange(){
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordCheck();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

//    private void passwordCheck(){
//        String password = etPassword.getText().toString();
//
//
//        //Atleast 8 characters
//        if(password.length() >= 8){
//            isAtleast8 = true;
//            conditionOne.setCardBackgroundColor(Color.parseColor("#7CFC00"));
//        }
//        else{
//            isAtleast8 = false;
//            conditionOne.setCardBackgroundColor(Color.parseColor("＃FF0000"));
//        }
//
////        //Atleast Three Letters
////        if(password.matches("(.*[A-Z][a-z]{3}.*)")){
////            atleastThreeLetter = true;
////            conditionTwo.setCardBackgroundColor(Color.parseColor("#7CFC00"));
////        }
////        else{
////            atleastThreeLetter = false;
////            conditionTwo.setCardBackgroundColor(Color.parseColor("＃FF0000"));
////        }
//
//        //For Uppercase
//        if(password.matches("(.*[A-Z].*)")){
//            hasUpper = true;
//            conditionThree.setCardBackgroundColor(Color.parseColor("#7CFC00"));
//        }
//        else{
//            hasUpper = false;
//            conditionThree.setCardBackgroundColor(Color.parseColor("＃FF0000"));
//        }
//
//        //Atleast one symbol
//        if(password.matches("^(?=.*[_.()]).*$")){
//            hasSymbol = true;
//            conditionFour.setCardBackgroundColor(Color.parseColor("#7CFC00"));
//        }
//        else{
//            hasSymbol = false;
//            conditionFour.setCardBackgroundColor(Color.parseColor("＃FF0000"));
//        }
//
////        //For Number
////        if(password.matches("(.*[0-9]{3}.*)")){
////            atleastThreeNumbers = true;
////            conditionFive.setCardBackgroundColor(Color.parseColor("#7CFC00"));
////        }
////        else {
////            atleastThreeNumbers = false;
////            conditionFive.setCardBackgroundColor(Color.parseColor("＃FF0000"));
////        }
//        checkAllData(password);
//    }
//
//    @SuppressLint("ResourceType")
//    private void checkAllData(String password){
//        if(isAtleast8 && atleastThreeLetter && atleastThreeNumbers && hasSymbol && hasUpper && password.length() >0){
//            isRegisterClick = true;
//            btnRegister.setCardBackgroundColor(Color.parseColor("#DE3163"));
//        }
//        else{
//            isRegisterClick = false;
//            btnRegister.setCardBackgroundColor(Color.parseColor("#FF000000"));
//        }
//    }
//
//
//    private void inputChange(){
//        etPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                passwordCheck();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//
////    private void confirmPasswordCheck(String password, String confirmPassword){
////        if(password == confirmPassword){
////            Toast.makeText(getApplicationContext(),"Both password matches", Toast.LENGTH_LONG).show();
////        }
////    }
//
