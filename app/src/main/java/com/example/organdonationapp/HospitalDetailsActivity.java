package com.example.organdonationapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HospitalDetailsActivity extends AppCompatActivity {

    TextView hospitalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        hospitalDetails = findViewById(R.id.hospitalDetails);

        String hospitalName = getIntent().getStringExtra("hospitalName");

        if (hospitalName == null) {
            hospitalDetails.setText("Hospital information not available.");
            return;
        }

        if (hospitalName.equals("City Hospital")) {

            hospitalDetails.setText(
                    "City Hospital\n\n" +
                            "Available Organs:\nKidney, Liver\n\n" +
                            "Blood Bank:\nA+, B+, O+, AB+"
            );

        } else if (hospitalName.equals("Apollo Hospital")) {

            hospitalDetails.setText(
                    "Apollo Hospital\n\n" +
                            "Available Organs:\nHeart, Kidney\n\n" +
                            "Blood Bank:\nA+, O+, O-, AB+"
            );

        } else if (hospitalName.equals("Ruby Hall Clinic")) {

            hospitalDetails.setText(
                    "Ruby Hall Clinic\n\n" +
                            "Available Organs:\nLiver, Cornea\n\n" +
                            "Blood Bank:\nB+, O+, AB+"
            );

        } else if (hospitalName.equals("Sahyadri Hospital")) {

            hospitalDetails.setText(
                    "Sahyadri Hospital\n\n" +
                            "Available Organs:\nKidney, Lung\n\n" +
                            "Blood Bank:\nA+, B+, O+, O-"
            );

        }
    }
}