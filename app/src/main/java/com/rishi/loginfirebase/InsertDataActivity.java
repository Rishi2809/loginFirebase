package com.rishi.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertDataActivity extends AppCompatActivity {
    EditText et1, et2, et3, et4;
    Button b1,b2;
    Context con;
    String uid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        uid = getIntent().getStringExtra("uid");
        et1 = findViewById(R.id.edit_text_name);
        et2 = findViewById(R.id.edit_text_dob);
        et3 = findViewById(R.id.edit_text_remindme);
        et4 = findViewById(R.id.edit_text_ocassion);
        b1 = findViewById(R.id.submit);
        con = getApplicationContext();
        progressBar = findViewById(R.id.progressbar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                addDetails();
            }
        });
    }

    private void addDetails() {
        String name = et1.getText().toString();
        String date = et2.getText().toString();
        String remindme = et3.getText().toString();
        String occasion = et4.getText().toString();

        myRef = database.getReference("Users").child(uid).child(occasion).child(name);
        myRef.child("name").setValue(name);
        myRef.child("dob").setValue(date);
        myRef.child("remindme").setValue(remindme).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(con, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });



    }

    public void getdata(View view) {
Intent intent = new Intent(InsertDataActivity.this,GetDataActivity.class);
intent.putExtra("uid",uid);
startActivity(intent);
    }
}
