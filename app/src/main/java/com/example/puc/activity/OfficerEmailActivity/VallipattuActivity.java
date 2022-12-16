package com.example.puc.activity.OfficerEmailActivity;

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
import com.example.puc.adapater.ComplaintsAdapter;
import com.example.puc.modal.Complaints;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VallipattuActivity extends AppCompatActivity {


    //Initialize Variables
    RecyclerView ComplaintRecyclerView;
    LinearLayout ComplaintNOData;

    private ArrayList<Complaints> list;
    private ComplaintsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vallipattu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Complaints");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Assign Variables
        ComplaintRecyclerView = findViewById(R.id.ComplaintRecyclerView);
        ComplaintNOData = findViewById(R.id.ComplaintNOData);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VallipattuActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        ComplaintRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new ComplaintsAdapter(VallipattuActivity.this, list);
        ComplaintRecyclerView.setAdapter(adapter);

        getBeemakulam();
    }

    private void getBeemakulam() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Complaints").child("Vallipattu");
        reference1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()) {
                    ComplaintNOData.setVisibility(View.VISIBLE);
                    ComplaintRecyclerView.setVisibility(View.GONE);
                } else {
                    ComplaintNOData.setVisibility(View.GONE);
                    ComplaintRecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Complaints data = dataSnapshot.getValue(Complaints.class);
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