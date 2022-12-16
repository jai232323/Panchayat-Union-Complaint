package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.puc.R;
import com.example.puc.fragment.ComplaintFragment;
import com.example.puc.fragment.GalleryFragment;
import com.example.puc.fragment.HomeFragment;
import com.example.puc.fragment.PresidentFragment;
import com.example.puc.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    HomeFragment homeFragment = new HomeFragment();
    ComplaintFragment deptFragment = new ComplaintFragment();
    GalleryFragment facultyFragment = new GalleryFragment();
    PresidentFragment profileFragment = new PresidentFragment();
    ProfileFragment aboutFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;
            case R.id.navigation_complaint:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, deptFragment).commit();
                return true;
            case R.id.navigation_gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, facultyFragment).commit();
                return true;
            case R.id.navigation_president:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                return true;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, aboutFragment).commit();
                return true;


        }
        return false;
    }
}