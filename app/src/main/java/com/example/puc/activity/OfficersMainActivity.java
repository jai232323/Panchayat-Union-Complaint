package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.puc.R;
import com.example.puc.adapater.ComplaintsAdapter;
import com.example.puc.adapater.UsersAdapter;
import com.example.puc.modal.Complaints;
import com.example.puc.modal.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OfficersMainActivity extends AppCompatActivity {


    String Beemakulam,Devasathanam,Echangal,Elayanagaram,Girisamuthiram,Gollakuppam,
            Govindapuram, Jaffarabad,Kothakottai,Madhanancheri,Marimanikuppam,mitturNaickanur,
            Narasingapuram,Nekkanamalai,Nimmiyambattu,Pallipattu, Periyakurumbatheru,Pethaveppambattu,
            Poongulam,Reddiur,Sammanthikuppam,Valayambattu,Vallipattu,Velathigamanibenda,Vellakuttai,Vijilapuram;



    //Initialize Variables
     RecyclerView ComplaintRecyclerView;
     LinearLayout ComplaintNOData;

    private ArrayList<Complaints> list;
    private ComplaintsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officers_main);


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



        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(OfficersMainActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        ComplaintRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new ComplaintsAdapter(OfficersMainActivity.this,list);
        ComplaintRecyclerView.setAdapter(adapter);


        SharedPreferences prefs1 = getSharedPreferences("PREFSBeemakulam", MODE_PRIVATE);
        Beemakulam=prefs1.getString("Beemakulam","none");

        SharedPreferences prefs2 = getSharedPreferences("PREFSDevasathanam", MODE_PRIVATE);
        Devasathanam=prefs2.getString("Devasathanam","none");

        SharedPreferences prefs3 = getSharedPreferences("PREFSEchangal", MODE_PRIVATE);
        Echangal=prefs3.getString("Echangal","none");

        SharedPreferences prefs4 = getSharedPreferences("PREFSElayanagaram", MODE_PRIVATE);
        Elayanagaram=prefs4.getString("Elayanagaram","none");

        SharedPreferences prefs5 = getSharedPreferences("PREFSGirisamuthiram", MODE_PRIVATE);
        Girisamuthiram=prefs5.getString("Girisamuthiram","none");

        SharedPreferences prefs6 = getSharedPreferences("PREFSGollakuppam", MODE_PRIVATE);
        Gollakuppam=prefs6.getString("Gollakuppam","none");

        SharedPreferences prefs7 = getSharedPreferences("PREFSGovindapuram", MODE_PRIVATE);
        Govindapuram=prefs7.getString("Govindapuram","none");

        SharedPreferences prefs8 = getSharedPreferences("PREFSJaffarabad", MODE_PRIVATE);
        Jaffarabad=prefs8.getString("Jaffarabad","none");

        SharedPreferences prefs9 = getSharedPreferences("PREFSKothakottai", MODE_PRIVATE);
        Kothakottai=prefs9.getString("Kothakottai","none");

        SharedPreferences prefs10 = getSharedPreferences("PREFSMadhanancheri", MODE_PRIVATE);
        Madhanancheri=prefs10.getString("Madhanancheri","none");

        SharedPreferences prefs11 = getSharedPreferences("PREFSMarimanikuppam", MODE_PRIVATE);
        Marimanikuppam=prefs11.getString("Marimanikuppam","none");

        SharedPreferences prefs12 = getSharedPreferences("PREFSmitturNaickanur", MODE_PRIVATE);
        mitturNaickanur=prefs12.getString("mitturNaickanur","none");

        SharedPreferences prefs13 = getSharedPreferences("PREFSNarasingapuram", MODE_PRIVATE);
        Narasingapuram=prefs13.getString("Narasingapuram","none");

        SharedPreferences prefs14 = getSharedPreferences("PREFSENekkanamalai", MODE_PRIVATE);
        Nekkanamalai=prefs14.getString("Nekkanamalai","none");

        SharedPreferences prefs15 = getSharedPreferences("PREFSNimmiyambattu", MODE_PRIVATE);
        Nimmiyambattu=prefs15.getString("Nimmiyambattu","none");

        SharedPreferences prefs16 = getSharedPreferences("PREFSGollakuppam", MODE_PRIVATE);
        Nimmiyambattu=prefs16.getString("Nimmiyambattu","none");

        SharedPreferences prefs17 = getSharedPreferences("PREFSPallipattu", MODE_PRIVATE);
        Pallipattu=prefs17.getString("Pallipattu","none");

        SharedPreferences prefs18 = getSharedPreferences("PREFSPeriyakurumbatheru", MODE_PRIVATE);
        Periyakurumbatheru=prefs18.getString("Periyakurumbatheru","none");

        SharedPreferences prefs19 = getSharedPreferences("PREFSPethaveppambattu", MODE_PRIVATE);
        Pethaveppambattu=prefs19.getString("Pethaveppambattu","none");

        SharedPreferences prefs20 = getSharedPreferences("PREFSPoongulam", MODE_PRIVATE);
        Poongulam=prefs20.getString("Poongulam","none");

        SharedPreferences prefs21 = getSharedPreferences("PREFSReddiur", MODE_PRIVATE);
        Reddiur=prefs21.getString("Reddiur","none");

        SharedPreferences prefs22 = getSharedPreferences("PREFSSammanthikuppam", MODE_PRIVATE);
        Sammanthikuppam=prefs22.getString("Sammanthikuppam","none");

        SharedPreferences prefs23 = getSharedPreferences("PREFSEValayambattu", MODE_PRIVATE);
        Valayambattu=prefs23.getString("Valayambattu","none");

        SharedPreferences prefs24 = getSharedPreferences("PREFSVallipattu", MODE_PRIVATE);
        Vallipattu=prefs24.getString("Vallipattu","none");

        SharedPreferences prefs25 = getSharedPreferences("PREFSVelathigamanibenda", MODE_PRIVATE);
        Velathigamanibenda=prefs25.getString("Velathigamanibenda","none");

        SharedPreferences prefs26 = getSharedPreferences("PREFSVellakuttai", MODE_PRIVATE);
        Vellakuttai=prefs26.getString("Vellakuttai","none");

        SharedPreferences prefs27 = getSharedPreferences("PREFSVijilapuram", MODE_PRIVATE);
        Vijilapuram=prefs27.getString("Vijilapuram","none");

        getBeemakulam();
        getDevasathanam();

    }

    private void getDevasathanam() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Complaints");
        reference1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    ComplaintNOData.setVisibility(View.VISIBLE);
                    ComplaintRecyclerView.setVisibility(View.GONE);
                }else {
                    ComplaintNOData.setVisibility(View.GONE);
                    ComplaintRecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Complaints data = dataSnapshot.getValue(Complaints.class);



                        if (Devasathanam.equals(data.getC_Village())){
                            list.add(0, data);
                            adapter.notifyDataSetChanged();
                        }else {
                            ComplaintNOData.setVisibility(View.VISIBLE);
                        }


                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getBeemakulam() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Complaints");
        reference1.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    ComplaintNOData.setVisibility(View.VISIBLE);
                    ComplaintRecyclerView.setVisibility(View.GONE);
                }else {
                    ComplaintNOData.setVisibility(View.GONE);
                    ComplaintRecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Complaints data = dataSnapshot.getValue(Complaints.class);


                        String Bee = reference1.child("Beemakulam").toString();
                        if (Beemakulam.equals(Bee)){
                            list.add(0, data);
                            adapter.notifyDataSetChanged();
                        }else {
                            ComplaintNOData.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}