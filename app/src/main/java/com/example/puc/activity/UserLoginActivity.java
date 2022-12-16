package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.puc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserLoginActivity extends AppCompatActivity {


    private EditText Email , Password;
    private Button SignInBtn;
    private LinearLayout SignUp;

    FirebaseAuth auth;

    FirebaseUser firebaseUser;
    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            startActivity(new Intent(UserLoginActivity.this, UserMainActivity.class));
            finish();
        }
    }
    String MobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        SignInBtn = findViewById(R.id.SignInBtn);
        SignUp = findViewById(R.id.SignUp);

        auth=FirebaseAuth.getInstance();


        SharedPreferences prefs1 = getSharedPreferences("PREFS", MODE_PRIVATE);
        MobileNumber = prefs1.getString("MobileNumber", "none");


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLoginActivity.this,UserRegisterActivity.class));
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog pd = new ProgressDialog(UserLoginActivity.this);
                pd.setMessage("Login\nPlease Wait...");
                pd.show();

                String email = Email.getText().toString();
                String password = Password.getText().toString();


                if (email.isEmpty()){
                    Email.setError("Email Empty");
                    Email.requestFocus();
                }else if (password.isEmpty()){
                    Password.setError("Password Empty");
                    Password.requestFocus();
                }else if (password.length() < 6){
                    Toast.makeText(UserLoginActivity.this,"Password must have 6 characters",Toast.LENGTH_SHORT).show();
                }else {

                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(UserLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {




                                    if (task.isSuccessful()) {

                                        String Userid = auth.getCurrentUser().getUid();

                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(Userid);

                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                pd.dismiss();
                                                Intent intent = new Intent(UserLoginActivity.this, UserMainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                                SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                                                editor.putString("UserId",Userid);
                                                editor.apply();

                                                SharedPreferences.Editor editor1 = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                                                editor1.putString("MobileNumber",MobileNumber);
                                                editor1.apply();

                                                startActivity(intent);
                                                Toast.makeText(UserLoginActivity.this,auth.getCurrentUser().getUid(),Toast.LENGTH_SHORT).show();
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                pd.dismiss();

                                            }
                                        });
                                    } else {
                                        pd.dismiss();
                                        Toast.makeText(UserLoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}