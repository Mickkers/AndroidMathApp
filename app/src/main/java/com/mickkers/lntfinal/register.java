package com.mickkers.lntfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    private Button regisButton, switchLogin;
    private EditText regisID, regisEmail, regisNama, regisPassword, regisConfirm;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        regisButton = findViewById(R.id.regisButton);
        switchLogin = findViewById(R.id.switchLogin);
        regisID = findViewById(R.id.regisID);
        regisEmail = findViewById(R.id.regisEmail);
        regisNama = findViewById(R.id.regisNama);
        regisPassword = findViewById(R.id.regisPassword);
        regisConfirm = findViewById(R.id.regisConfirm);

        switchLogin.setOnClickListener(view -> {

            Intent intent = new Intent(register.this, MainActivity.class);
            startActivity(intent);

        });

        regisButton.setOnClickListener(view -> {
            String email, password, idBimbel, nama, passConfirm;



            if(TextUtils.isEmpty(regisEmail.getText()) || TextUtils.isEmpty(regisPassword.getText()) || TextUtils.isEmpty(regisID.getText()) || TextUtils.isEmpty(regisNama.getText()) || TextUtils.isEmpty(regisConfirm.getText())){
                Toast.makeText(register.this, "Registration Unsuccessful Please Fill The Empty Fields", Toast.LENGTH_SHORT).show();
            } else {
                email = regisEmail.getText().toString();
                password = regisPassword.getText().toString();
                idBimbel = regisID.getText().toString();
                nama = regisNama.getText().toString();
                passConfirm = regisConfirm.getText().toString();

                if(password.equals(passConfirm)){
                    if(email.contains("@") && email.endsWith(".com")){
                        if(nama.length() >= 5){
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, task -> {
                                if(task.isSuccessful()){
                                    user = mAuth.getCurrentUser();

                                    mDatabase = FirebaseDatabase.getInstance().getReference("User Data");

                                    userData userdata = new userData(nama, idBimbel);

                                    String id = mAuth.getUid();

                                    mDatabase.child(id).setValue(userdata);

                                    user = mAuth.getCurrentUser();


                                    Intent intent = new Intent(register.this, MainMenu.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(register.this, "Registration Unsuccessful " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(register.this, "Registration Unsuccessful Name Length Invalid 5 Characters Minimum", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(register.this, "Registration Unsuccessful Email Is Invalid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(register.this, "Registration Unsuccessful Password And Confirmation Does Not Match", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}