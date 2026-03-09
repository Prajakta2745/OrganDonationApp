package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DonorRegistrationActivity extends AppCompatActivity {

    Button registerDonorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Correct layout file
        setContentView(R.layout.activity_donor_registration);

        registerDonorBtn = findViewById(R.id.requestBtn);

        registerDonorBtn.setOnClickListener(v -> {

            Intent intent = new Intent(DonorRegistrationActivity.this, OrganRequestActivity.class);
            startActivity(intent);

        });
    }
}