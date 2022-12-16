package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.puc.R;
import com.example.puc.adapater.OfficerAdapter;
import com.example.puc.modal.OfficerData;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminCreateOfficerActivity extends AppCompatActivity {


   FloatingActionButton add_officer;


    RecyclerView OfficerRecyclerView;

    private ArrayList<OfficerData> list;
    private OfficerAdapter adapter;

    DatabaseReference reference =
            FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://p-u-c-22b44-default-rtdb.firebaseio.com/");


    String mobilenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_officer);


        add_officer = findViewById(R.id.add_officer);
        add_officer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminCreateOfficerActivity.this,CreateOfficerActivity.class));

            }
        });

//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                OfficerData data = snapshot.getValue(OfficerData.class);
//
//                 mobilenumber = snapshot.child(data.getOfficerMobilenumber()).child("OfficerMobilenumber").getValue(String.class);
//                Log.i("<",data.getOfficerMobilenumber()+">");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(AdminCreateOfficerActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        OfficerRecyclerView = findViewById(R.id.OfficerRecyclerView);
        OfficerRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new OfficerAdapter(AdminCreateOfficerActivity.this,list);
        OfficerRecyclerView.setAdapter(adapter);


        getOfficerData();
    }

    private void getOfficerData() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Officers");
        reference1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OfficerData data = dataSnapshot.getValue(OfficerData.class);

//                    mobilenumber = snapshot.child(data.getOfficerMobilenumber()).child("OfficerMobilenumber").getValue(String.class);
//
//                 //   String mn = snapshot.child(data.getOfficerMobilenumber()).child("OfficerMobilenumber").getValue().toString();
//                    Log.i("<",mobilenumber+">");
//                    if (data.getOfficerMobilenumber().equals(mobilenumber)){
//                        list.add(0, data);
//                        adapter.notifyDataSetChanged();
//                    }


                    list.add(0, data);
                    adapter.notifyDataSetChanged();




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminCreateOfficerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}