package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class activity_organ_request extends AppCompatActivity {

    Button requestBtn;  // ✅ Fixed: matches XML id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_request);  // ✅ Fixed: correct layout name

        requestBtn = findViewById(R.id.requestBtn);  // ✅ Fixed: matches XML id

        requestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(activity_organ_request.this, HospitalActivity.class);  // ✅ Fixed: correct class
            startActivity(intent);
        });
    }
}