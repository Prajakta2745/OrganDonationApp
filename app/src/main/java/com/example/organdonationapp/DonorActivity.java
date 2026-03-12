package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class DonorActivity extends AppCompatActivity {

    private EditText donorNameEt, donorAgeEt, donorBloodEt, donorOrganEt, donorPhoneEt;
    private Button registerDonorBtn;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Donors");

        donorNameEt = findViewById(R.id.donorName);
        donorAgeEt = findViewById(R.id.donorAge);
        donorBloodEt = findViewById(R.id.donorBlood);
        donorOrganEt = findViewById(R.id.donorOrgan);
        donorPhoneEt = findViewById(R.id.donorPhone);
        registerDonorBtn = findViewById(R.id.requestBtn);

        registerDonorBtn.setOnClickListener(v -> registerDonor());
    }

    private void registerDonor() {
        String name = donorNameEt.getText().toString().trim();
        String age = donorAgeEt.getText().toString().trim();
        String blood = donorBloodEt.getText().toString().trim();
        String organ = donorOrganEt.getText().toString().trim();
        String phone = donorPhoneEt.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || organ.isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String donorId = mDatabase.push().getKey();
        Map<String, Object> donorData = new HashMap<>();
        donorData.put("name", name);
        donorData.put("age", age);
        donorData.put("bloodGroup", blood);
        donorData.put("organ", organ);
        donorData.put("phone", phone);
        donorData.put("status", "available");

        if (donorId != null) {
            mDatabase.child(donorId).setValue(donorData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(DonorActivity.this, "Donor Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(DonorActivity.this, "Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }
}
