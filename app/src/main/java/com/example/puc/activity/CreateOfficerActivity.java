package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.puc.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CreateOfficerActivity extends AppCompatActivity {


    ImageView back;

    private EditText OfficerName,OfficerMobileNumber,OfficerPassword;
    Spinner Complainter_Village;
    private Button Save;


    String o_name,o_mobilenumber,o_password,c_village;


    DatabaseReference reference =
            FirebaseDatabase.getInstance().
                    getReferenceFromUrl("https://p-u-c-22b44-default-rtdb.firebaseio.com/");


    ProgressDialog pd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_officer);


        pd= new ProgressDialog(this);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        OfficerName = findViewById(R.id.OfficerName);
        OfficerMobileNumber = findViewById(R.id.OfficerMobileNumber);
        OfficerPassword = findViewById(R.id.OfficerPassword);
        Complainter_Village = findViewById(R.id.Complainter_Village);
        Save = findViewById(R.id.Save);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

        //Spinner Villages
        String[] itemsVillages = new String[]{"Select Village", "Beemakulam", "Devasathanam","Echangal",
                "Elayanagaram","Girisamuthiram","Gollakuppam","Govindapuram"
                ,"Jaffarabad","Kothakottai","Madhanancheri","Marimanikuppam"
                ,"Mittur","Naickanur","Narasingapuram","Nekkanamalai","Nimmiyambattu"
                ,"Pallipattu","Periyakurumbatheru","Pethaveppambattu","Poongulam","Reddiur"
                ,"Sammanthikuppam","Valayambattu","Vallipattu","Velathigamanibenda","Vellakuttai"
                ,"Vijilapuram"};

        Complainter_Village.setAdapter(new ArrayAdapter<String>(CreateOfficerActivity.this, android.R.layout.simple_spinner_dropdown_item, itemsVillages));

        Complainter_Village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c_village = Complainter_Village.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void checkValidation() {


        o_name =OfficerName.getText().toString();
        o_mobilenumber =OfficerMobileNumber.getText().toString();
        o_password =OfficerPassword.getText().toString();

        if (o_name.isEmpty()){
            OfficerName.setError("Provide Officer Name");
            OfficerName.requestFocus();
        }else if (o_mobilenumber.isEmpty()){
            OfficerMobileNumber.setError("Provide Mobile Number");
            OfficerMobileNumber.requestFocus();
        }else if (o_password.isEmpty()){
            OfficerPassword.setError("Provide Password");
            OfficerPassword.requestFocus();
        }
        //Village
        else  if (c_village.equals("Select Village")) {
            Toast.makeText(CreateOfficerActivity.this,"Please Select Village", Toast.LENGTH_SHORT).show();
        }
        else {
            InsertData(o_name,o_mobilenumber,o_password);
        }


    }

    private void InsertData(String o_name, String o_mobilenumber,
                            String o_password) {


        pd.setMessage("Uploading...");
        pd.show();
        reference.child("Officers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild(o_mobilenumber)) {
                    pd.dismiss();
                    Toast.makeText(CreateOfficerActivity.this,
                            "Mobile Number Already Registered", Toast.LENGTH_SHORT).show();
                } else {
                    reference = FirebaseDatabase.getInstance().getReference().child("Officers");


                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    String date=currentDate.format(calForDate.getTime());


                    Calendar calForTIme = Calendar.getInstance();
                    SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                    String time=currentTime.format(calForTIme.getTime());

                    String userid = reference.push().getKey().toString();

                    HashMap<String, String> hashMap = new HashMap<>();

                    hashMap.put("OfficerId", userid);
                    hashMap.put("OfficerName", o_name);
                    hashMap.put("OfficerMobilenumber", o_mobilenumber);
                    hashMap.put("OfficerPassword", o_password);
                    hashMap.put("OfficerVillage",c_village);
                    hashMap.put("Date",date);
                    hashMap.put("Time",time);
                    hashMap.put("Status","0");

                    reference.child(o_mobilenumber).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            pd.dismiss();
//                            Intent intent = new Intent(RegisterActivity.this, RegBusinessActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                            SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
//                            editor.putString("Usermobilenumber", MobileNumber);
//                            editor.apply();
//
//                            startActivity(intent);

                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(CreateOfficerActivity.this, "You can't register", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}