package com.mickkers.lntfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button loginButton, switchRegister;
    private EditText loginEmail, loginPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loginButton = findViewById(R.id.loginButton);
        switchRegister = findViewById(R.id.switchRegister);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        switchRegister.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, register.class);
            startActivity(intent);

        });

        loginButton.setOnClickListener(view -> {

            String email, password;

            email = loginEmail.getText().toString();
            password = loginPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, task -> {

                if(task.isSuccessful()){
                    user = mAuth.getCurrentUser();

                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Login Unsuccessful " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}