package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button donorBtn, requestBtn, hospitalBtn, adminBtn, feedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donorBtn = findViewById(R.id.donorBtn);
        requestBtn = findViewById(R.id.requestBtn);
        hospitalBtn = findViewById(R.id.hospitalBtn);
        adminBtn = findViewById(R.id.adminBtn);
        feedbackBtn = findViewById(R.id.feedbackBtn);

        donorBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DonorActivity.class)));

        requestBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RequestActivity.class)));

        hospitalBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HospitalInfoActivity.class)));

        adminBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AdminApprovalActivity.class)));

        feedbackBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class)));
    }
}