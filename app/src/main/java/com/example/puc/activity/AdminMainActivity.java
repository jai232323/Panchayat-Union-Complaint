package com.example.puc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puc.R;
import com.google.android.material.card.MaterialCardView;

public class AdminMainActivity extends AppCompatActivity {


    //Initialize Variable
    MaterialCardView Users;
    MaterialCardView AdminCreateOfficer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //Assign Variable
        Users = findViewById(R.id.Users);
        AdminCreateOfficer = findViewById(R.id.AdminCreateOfficer);

        //Go to AdminUserActivity
        Users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,AdminUserActivity.class));
            }
        });

        //Go to AdminCreateOfficerActivity
        AdminCreateOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,AdminCreateOfficerActivity.class));
            }
        });

    }
}