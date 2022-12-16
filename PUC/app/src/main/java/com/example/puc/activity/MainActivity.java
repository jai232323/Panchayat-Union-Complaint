package com.example.puc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.puc.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {


    //Initialize Variable
    private MaterialCardView Users , Officers , Admin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        Users = findViewById(R.id.Users);
        Officers = findViewById(R.id.Officer);
        Admin = findViewById(R.id.Admin);

        Users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UserLoginActivity.class));
            }
        });
        Officers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OfficersActivity.class));
            }
        });
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdminActivity.class));
            }
        });
    }
}