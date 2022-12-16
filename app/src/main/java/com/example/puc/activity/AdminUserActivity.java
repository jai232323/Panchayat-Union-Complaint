package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.puc.R;
import com.example.puc.adapater.UsersAdapter;
import com.example.puc.modal.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminUserActivity extends AppCompatActivity {


    //Initialize Variables
    private RecyclerView UserRecyclerView;
    private LinearLayout UserNOData;

    private ArrayList<Users> list;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Users");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Assign Variables
        UserRecyclerView = findViewById(R.id.UserRecyclerView);
        UserNOData = findViewById(R.id.UserNOData);


        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(AdminUserActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        UserRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new UsersAdapter(AdminUserActivity.this,list);
        UserRecyclerView.setAdapter(adapter);

        //get user's data
        getUserDatas();
    }

    private void getUserDatas() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Users");
        reference1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    UserNOData.setVisibility(View.VISIBLE);
                    UserRecyclerView.setVisibility(View.GONE);
                }else {
                    UserNOData.setVisibility(View.GONE);
                    UserRecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Users data = dataSnapshot.getValue(Users.class);
                        list.add(0, data);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}