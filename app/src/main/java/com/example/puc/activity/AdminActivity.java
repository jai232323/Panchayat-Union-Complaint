package com.example.puc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.puc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity {


    //Initialize Variable
    EditText MobileNumber , Password ;
    Button AdminBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //Assign Variable
        MobileNumber = findViewById(R.id.AdminMobileNumber);
        Password = findViewById(R.id.AdminPassword);
        AdminBtn = findViewById(R.id.AdminBtn);

        AdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile_number = MobileNumber.getText().toString();
                String password = Password.getText().toString();

                if (mobile_number.isEmpty()){
                    MobileNumber.setError("Empty");
                    MobileNumber.requestFocus();
                }else if (mobile_number.length()<10){
                    Toast.makeText(AdminActivity.this, "10 Numbers Only", Toast.LENGTH_SHORT).show();
                }else if (mobile_number.length()>10){
                    Toast.makeText(AdminActivity.this, "10 Numbers Only", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Password.setError("Empty");
                    Password.requestFocus();
                }else if (password.length()<6){
                    Toast.makeText(AdminActivity.this, "6 Numbers Only", Toast.LENGTH_SHORT).show();
                }else if (password.length()>6){
                    Toast.makeText(AdminActivity.this, "6 Numbers Only", Toast.LENGTH_SHORT).show();
                }else if (mobile_number.equals("1234567890") && password.equals("123456")){
                    startActivity(new Intent(AdminActivity.this,AdminMainActivity.class));
                    finish();
                }else {
                    Toast.makeText(AdminActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}