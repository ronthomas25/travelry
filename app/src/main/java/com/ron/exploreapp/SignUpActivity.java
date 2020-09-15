package com.ron.exploreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

        EditText emailId, password;
        Button btnSignUp;
        TextView tvSignIn;
        FirebaseAuth mFirebaseAuth;
        FirebaseAuth mfirebaseauth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            mfirebaseauth=FirebaseAuth.getInstance();

            FirebaseUser user=mfirebaseauth.getCurrentUser();
            if (user != null) {
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
            }
            else{

            }

            mFirebaseAuth = FirebaseAuth.getInstance();
            emailId = findViewById(R.id.editText);
            password = findViewById(R.id.editText2);
            btnSignUp = findViewById(R.id.button2);
            tvSignIn = findViewById(R.id.textView);
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailId.getText().toString();
                    String pwd = password.getText().toString();
                    if(email.isEmpty()){
                        emailId.setError("Please enter email id");
                        emailId.requestFocus();
                    }
                    else  if(pwd.isEmpty()){
                        password.setError("Please enter your password");
                        password.requestFocus();
                    }
                    else  if(email.isEmpty() && pwd.isEmpty()){
                        Toast.makeText(SignUpActivity.this,".Fields Are Empty!",Toast.LENGTH_SHORT).show();
                    }
                    else  if(!(email.isEmpty() && pwd.isEmpty())){
                        mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                    finish();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                    }
                }
            });

            tvSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SignUpActivity.this,LogInActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }


