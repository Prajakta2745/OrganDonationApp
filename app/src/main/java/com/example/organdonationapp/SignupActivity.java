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

public class SignupActivity extends AppCompatActivity {

    private EditText nameEt, emailEt, passwordEt, phoneEt, bloodGroupEt, organNeededEt;
    private Button signupBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        nameEt = findViewById(R.id.name);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        phoneEt = findViewById(R.id.phone);
        bloodGroupEt = findViewById(R.id.bloodGroup);
        organNeededEt = findViewById(R.id.organNeeded);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String name = nameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String bloodGroup = bloodGroupEt.getText().toString().trim();
        String organNeeded = organNeededEt.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password are required", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    String userId = mAuth.getCurrentUser().getUid();
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", name);
                    user.put("email", email);
                    user.put("phone", phone);
                    user.put("bloodGroup", bloodGroup);
                    user.put("organNeeded", organNeeded);
                    user.put("role", "receiver");

                    mDatabase.child("Users").child(userId).setValue(user)
                        .addOnCompleteListener(dbTask -> {
                            if (dbTask.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                } else {
                    Toast.makeText(SignupActivity.this, "Auth Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }
}
