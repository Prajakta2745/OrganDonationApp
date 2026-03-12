package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class HospitalInfoActivity extends AppCompatActivity {

    LinearLayout hospitalContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);

        hospitalContainer = findViewById(R.id.hospitalContainer);

        addHospital("City Hospital", "MG Road, Pune");
        addHospital("Apollo Hospital", "Baner, Pune");
        addHospital("Ruby Hall Clinic", "Sassoon Road, Pune");
        addHospital("Sahyadri Hospital", "Karve Road, Pune");
    }

    private void addHospital(String name, String address) {

        Button hospitalBtn = new Button(this);
        hospitalBtn.setText(name + "\n" + address);

        hospitalBtn.setOnClickListener(v -> {

            Intent intent = new Intent(HospitalInfoActivity.this, HospitalDetailsActivity.class);
            intent.putExtra("hospitalName", name);
            startActivity(intent);

        });

        hospitalContainer.addView(hospitalBtn);
    }
}