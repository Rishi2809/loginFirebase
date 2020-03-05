package com.rishi.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GetDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    EditText et1, et2, et3, et4;
    Button b1;
    List<Dates> mlist;
    Dates adapter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        et1 = findViewById(R.id.edit_text_name);
        et2 = findViewById(R.id.edit_text_dob);
        et3 = findViewById(R.id.edit_text_remindme);
        et4 = findViewById(R.id.edit_text_ocassion);
        b1 = findViewById(R.id.retrive);
        uid = getIntent().getStringExtra("uid");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    public void getData() {
        reference = database.getReference("Users/f2c9Km7g8Qb9KDfCjMQXrRNRjpw1/birthdays/rishi");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String dob = dataSnapshot.child("dob").getValue().toString();
                String oca = dataSnapshot.child("occassion").getValue().toString();
                String remindme = dataSnapshot.child("remindme").getValue().toString();

                et1.setText(name);
                et2.setText(dob);
                et3.setText(remindme);
                et4.setText(oca);
//                        name.setText(nameee);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
