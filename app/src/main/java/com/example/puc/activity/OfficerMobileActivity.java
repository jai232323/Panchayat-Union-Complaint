package com.example.puc.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.puc.R;
import com.example.puc.modal.OfficerData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OfficerMobileActivity extends AppCompatActivity {




    EditText U_MobileNumber,U_Password;
    Button LoginBtn;
    LinearLayout NewUserRegisterBtn;

    FirebaseAuth auth;
    String U_Id;

    DatabaseReference reference = FirebaseDatabase.getInstance().
            getReferenceFromUrl("https://p-u-c-22b44-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_email1);

        U_MobileNumber=findViewById(R.id.U_MobileNumber);
        U_Password=findViewById(R.id.U_Password);
        LoginBtn=findViewById(R.id.LoginBtn);
        NewUserRegisterBtn=findViewById(R.id.NewUserRegisterBtn);

        auth=FirebaseAuth.getInstance();


        SharedPreferences prefs1 = getSharedPreferences("PREFS", MODE_PRIVATE);
        U_Id=prefs1.getString("U_Id","none");



        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog pd = new ProgressDialog(OfficerMobileActivity.this);
                pd.setMessage("Wait a seconds...");
                pd.show();

                final String str_mobile=U_MobileNumber.getText().toString();
                final String str_password = U_Password.getText().toString();

                if (TextUtils.isEmpty(str_mobile) || TextUtils.isEmpty(str_password)){
                    Toast.makeText(OfficerMobileActivity.this,"All Fields are Required",Toast.LENGTH_SHORT).show();
                }
//                else {
//
//
//
//                }
                reference.child("Officers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                     //   OfficerData data = snapshot.getValue(OfficerData.class);

                        if (snapshot.hasChild(str_mobile)){

                            final String password = snapshot.child(str_mobile).
                                    child("OfficerPassword").getValue(String.class);

                            final String OfficerVillage = snapshot.child(str_mobile).
                                    child("OfficerVillage").getValue(String.class);

                            if (str_mobile.equals("") || str_password.equals("")){
                                U_MobileNumber.setError("Fill Mobile Number");
                                U_MobileNumber.requestFocus();
                                U_Password.setError("Fill Password");
                                U_Password.requestFocus();
                                U_MobileNumber.requestFocus();
                                pd.dismiss();
                            }

                            else if (password.equals(str_password)){
                                pd.dismiss();
                                Toast.makeText(OfficerMobileActivity.this,
                                        "Successfully Logged in", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(OfficerMobileActivity.this,OfficerShowActivity.class);



                                SharedPreferences.Editor edito2 = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                                edito2.putString("OfficerVillage", OfficerVillage);
                                edito2.apply();

                                SharedPreferences.Editor edito3 = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                                edito3.putString("OfficerMobilenumber", OfficerVillage);
                                edito3.apply();
                                Log.i("<",OfficerVillage+">");

                                startActivity(intent);
                                finish();

                            }else {
                                pd.dismiss();
                                Toast.makeText(OfficerMobileActivity.this,
                                        "Wrong Password", Toast.LENGTH_SHORT).show();

                            }

                        }else {
                            pd.dismiss();
                            Toast.makeText(OfficerMobileActivity.this,
                                    "Wrong Mobile Number", Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}