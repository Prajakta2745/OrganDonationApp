package com.example.organdonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEt, passwordEt;
    private Button loginBtn, signupRedirectBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Connect UI elements
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginBtn);
        signupRedirectBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(v -> loginUser());

        // Redirect to Signup Screen
        signupRedirectBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {

        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        // Validation
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase Login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {

                        Toast.makeText(LoginActivity.this,
                                "Login Successful",
                                Toast.LENGTH_SHORT).show();

                        // Go to Main Screen
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        Toast.makeText(LoginActivity.this,
                                "Login Failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}