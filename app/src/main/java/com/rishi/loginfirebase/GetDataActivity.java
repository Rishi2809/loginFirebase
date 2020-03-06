package com.rishi.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetDataActivity extends AppCompatActivity {
    ShimmerRecyclerView shimmerRecycler;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    Button b1;
    List<Dates> mlist;
    MyListAdapter adapter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.shimmer_recycler_view);
        shimmerRecycler.showShimmerAdapter();
//        recyclerView = findViewById(R.id.recycler);
        shimmerRecycler.setHasFixedSize(true);
        shimmerRecycler.setLayoutManager(new LinearLayoutManager(this));
        mlist = new ArrayList<>();
        uid = getIntent().getStringExtra("uid");
        Log.d("GetDataActivity", uid);
                getData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        adapter = new MyListAdapter(mlist);
                        shimmerRecycler.setAdapter(adapter);
                        shimmerRecycler.hideShimmerAdapter();
                    }
                },4000);


    }

    public void getData() {
        reference = database.getReference("Users/" + uid + "/events/");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        String name = npsnapshot.child("name").getValue().toString();
                        String dob = npsnapshot.child("dob").getValue().toString();
                        String oca = npsnapshot.child("occassion").getValue().toString();
                        String remindme = npsnapshot.child("remindme").getValue().toString();

                        Dates dates = new Dates();
                        dates.setName(name);
                        dates.setDob(dob);
                        dates.setOccassion(oca);
                        dates.setRemindme(remindme);
                        mlist.add(dates);
                        Log.d("hello", "hi");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
