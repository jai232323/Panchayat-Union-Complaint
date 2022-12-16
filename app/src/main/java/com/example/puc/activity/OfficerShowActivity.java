package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puc.R;
import com.example.puc.adapater.ComplaintsAdapter;
import com.example.puc.adapater.OfficerAdapter;
import com.example.puc.modal.Complaints;
import com.example.puc.modal.OfficerData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OfficerShowActivity extends AppCompatActivity {


    RecyclerView OfficerRecyclerView;

    private ArrayList<Complaints> list;
    private ComplaintsAdapter adapter;

    TextView OfficerVillage;


    String OfficerVillage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_show);


        SharedPreferences prefs1 = getSharedPreferences("PREFS", MODE_PRIVATE);
        OfficerVillage1=prefs1.getString("OfficerVillage","none");

        OfficerVillage = findViewById(R.id.OfficerVillage);
        OfficerVillage.setText(OfficerVillage1);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(OfficerShowActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        OfficerRecyclerView = findViewById(R.id.OfficerRecyclerView);
        OfficerRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new ComplaintsAdapter(OfficerShowActivity.this,list);
        OfficerRecyclerView.setAdapter(adapter);


        getOfficerData();
    }

    private void getOfficerData() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Complaints");
        reference1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Complaints data = dataSnapshot.getValue(Complaints.class);

                    final String OfficerVillage = snapshot.child(data.getC_id()).
                            child("C_Village").getValue(String.class);



                    if (data.getC_Village().equals(OfficerVillage1)){
                        list.add(0, data);
                        adapter.notifyDataSetChanged();
                        Log.i("<","SUCCESS"+">");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OfficerShowActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}