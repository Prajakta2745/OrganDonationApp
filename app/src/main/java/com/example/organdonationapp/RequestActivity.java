package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {

    private EditText patientNameEt, organTypeEt;
    private Button requestBtn;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_request);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("OrganRequests");

        // Initialize UI elements
        patientNameEt = findViewById(R.id.patientName);
        organTypeEt = findViewById(R.id.organType);
        requestBtn = findViewById(R.id.requestBtn);

        // Button click listener
        requestBtn.setOnClickListener(v -> submitRequest());
    }

    private void submitRequest() {

        String name = patientNameEt.getText().toString().trim();
        String organ = organTypeEt.getText().toString().trim();

        // Validate fields
        if (name.isEmpty() || organ.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get user ID
        String userId = "anonymous";
        if (mAuth.getCurrentUser() != null) {
            userId = mAuth.getCurrentUser().getUid();
        }

        // Generate request ID
        String requestId = mDatabase.push().getKey();

        if (requestId == null) {
            Toast.makeText(this, "Failed to create request ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create request data
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("userId", userId);
        requestData.put("patientName", name);
        requestData.put("organType", organ);
        requestData.put("status", "pending");
        requestData.put("timestamp", System.currentTimeMillis());

        // Save data to Firebase
        mDatabase.child(requestId).setValue(requestData)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(RequestActivity.this,
                            "Request Submitted Successfully",
                            Toast.LENGTH_SHORT).show();

                    // Clear fields
                    patientNameEt.setText("");
                    organTypeEt.setText("");

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(RequestActivity.this,
                            "Failed: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();

                });
    }
}