package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    Button registerDonorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerDonorBtn = findViewById(R.id.registerDonorBtn);

        registerDonorBtn.setOnClickListener(v -> {

            Intent intent = new Intent(SignupActivity.this, DonorActivity.class);
            startActivity(intent);

        });
    }
}