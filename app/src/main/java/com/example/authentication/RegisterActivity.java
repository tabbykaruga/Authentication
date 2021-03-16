package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_email, et_password,et_username,et_phone;
    private Button btnregister;
    private TextView tv_login;
    public FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        et_username=findViewById(R.id.username);
        et_email=findViewById(R.id.email);
        et_password=findViewById(R.id.password);
        et_phone=findViewById(R.id.phone);
        btnregister=findViewById(R.id.btn_register);
        tv_login=findViewById(R.id.textviewLogin);
        progressBar = findViewById(R.id.progressbar);


        if (mAuth.getCurrentUser() !=null){
            startActivity( new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }



        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });
    }


    private void register(){

        progressBar.setVisibility(View.VISIBLE);
        String email, password;

        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            et_email.setError("Email is required.");
            return;
        }
        if (TextUtils.isEmpty(password)){
            et_password.setError("Password is required.");
            return;
        }
        if (password.length() <4){
            et_password.setError("Password must have at least 4 characters");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                 new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();
                            startActivity( new Intent(getApplicationContext(),MainActivity.class));
                           // progressBar.setVisibility(View.GONE);


                    }else{
                            Toast.makeText(RegisterActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
    /*
    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser=mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {


    }

    public class user{
        String username;
        int phone;

        public user(String username, int phone) {
            this.username = username;
            this.phone = phone;
        }

        public String getUsername() {return username;}
        public void setUsername(String username) {this.username = username;}

        public int getPhone() {return phone;}
        public void setPhone(int phone) {this.phone = phone;}
    }

     */
    }

