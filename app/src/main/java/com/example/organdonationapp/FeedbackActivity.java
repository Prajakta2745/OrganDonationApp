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

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackEt;
    private Button sendFeedbackBtn;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Feedback");

        feedbackEt = findViewById(R.id.feedbackText);
        sendFeedbackBtn = findViewById(R.id.sendFeedbackBtn);

        sendFeedbackBtn.setOnClickListener(v -> submitFeedback());
    }

    private void submitFeedback() {
        String feedback = feedbackEt.getText().toString().trim();

        if (feedback.isEmpty()) {
            Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "anonymous";
        String feedbackId = mDatabase.push().getKey();

        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("userId", userId);
        feedbackData.put("feedback", feedback);
        feedbackData.put("timestamp", System.currentTimeMillis());

        if (feedbackId != null) {
            mDatabase.child(feedbackId).setValue(feedbackData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(FeedbackActivity.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                        feedbackEt.setText("");
                        finish();
                    } else {
                        Toast.makeText(FeedbackActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }
}
