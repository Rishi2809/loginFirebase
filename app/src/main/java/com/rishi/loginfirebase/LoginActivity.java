package com.rishi.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
EditText emailId,password;
Button signin;
    FirebaseAuth mFirebaseAuth;
    String uid;
    FirebaseApp firebaseApp;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseApp.initializeApp(this);
        signin = findViewById(R.id.submit);
        mFirebaseAuth =FirebaseAuth.getInstance();
        emailId = findViewById(R.id.edit_text_name);
        password = findViewById(R.id.edit_text_password);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser =  mFirebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Toast.makeText(LoginActivity.this,"You Are Logged In",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,InsertDataActivity.class);
                    intent.putExtra("uid",mFirebaseAuth.getUid());
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();

                }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Enter the Email");
                    emailId.requestFocus();
                }else if(pwd.isEmpty()){
                    password.setError("Enter the Password");
                    password.requestFocus();
                }else if((email.isEmpty() && pwd.isEmpty())){
                    Toast.makeText(LoginActivity.this,"Fields cannot be empty",Toast.LENGTH_LONG).show();
                }else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error ! Please Login Again",Toast.LENGTH_SHORT).show();

                            }else {
                                uid = mFirebaseAuth.getCurrentUser().getUid();
                                Intent intent = new Intent(LoginActivity.this,InsertDataActivity.class);
                                intent.putExtra("uid",uid);
                                Log.d("LoginActivityIntent",uid);
                                startActivity(intent);
                            }
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }
}
