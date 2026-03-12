package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminApprovalActivity extends AppCompatActivity {

    LinearLayout requestContainer;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approval);

        requestContainer = findViewById(R.id.requestContainer);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("OrganRequests");

        loadPendingRequests();
    }

    private void loadPendingRequests() {

        databaseReference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                for (DataSnapshot snapshot : task.getResult().getChildren()) {

                    String id = snapshot.getKey();
                    String name = snapshot.child("patientName").getValue(String.class);
                    String organ = snapshot.child("organType").getValue(String.class);
                    String status = snapshot.child("status").getValue(String.class);

                    if ("pending".equals(status)) {

                        LinearLayout itemLayout = new LinearLayout(this);
                        itemLayout.setOrientation(LinearLayout.VERTICAL);
                        itemLayout.setPadding(20,20,20,20);

                        TextView info = new TextView(this);
                        info.setText("Patient: " + name + "\nOrgan: " + organ);

                        Button approveBtn = new Button(this);
                        approveBtn.setText("Approve");

                        Button rejectBtn = new Button(this);
                        rejectBtn.setText("Reject");

                        approveBtn.setOnClickListener(v -> {
                            databaseReference.child(id).child("status").setValue("approved");
                            Toast.makeText(this,"Request Approved",Toast.LENGTH_SHORT).show();
                            recreate();
                        });

                        rejectBtn.setOnClickListener(v -> {
                            databaseReference.child(id).child("status").setValue("rejected");
                            Toast.makeText(this,"Request Rejected",Toast.LENGTH_SHORT).show();
                            recreate();
                        });

                        itemLayout.addView(info);
                        itemLayout.addView(approveBtn);
                        itemLayout.addView(rejectBtn);

                        requestContainer.addView(itemLayout,
                                new ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                    }
                }

            } else {

                Toast.makeText(this,"Error loading requests",Toast.LENGTH_SHORT).show();

            }

        });

    }
}