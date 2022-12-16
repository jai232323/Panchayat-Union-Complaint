package com.example.puc.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class UserRegisterActivity extends AppCompatActivity {


    //Initialize Variable
    MaterialCardView MC_UserImage;
    ImageView UserImage;
    TextView UserImage_Text;
    EditText Name,Email,Password,Address,MobileNumber;
    Spinner Gender;
    Button DOB,SignUpBtn;
    LinearLayout SignUpActivity;

    String downloadUrl="",name,email,password,address,mobile_number,gender,dob;

    DatePickerDialog.OnDateSetListener onDateSetListener;
    Bitmap bitmap;
    private final int REQ = 1;

    DatabaseReference reference;
    StorageReference storageReference;
    private FirebaseAuth auth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        //Assign Variable
        MC_UserImage=findViewById(R.id.MC_UserImage);
        UserImage=findViewById(R.id.UserImage);
        UserImage_Text=findViewById(R.id.UserImage_Text);
        Name=findViewById(R.id.Name);
        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        Address=findViewById(R.id.Address);
        MobileNumber=findViewById(R.id.MobileNumber);
        Gender=findViewById(R.id.Gender);
        DOB=findViewById(R.id.DOB);
        SignUpBtn=findViewById(R.id.SignUpBtn);
        SignUpActivity=findViewById(R.id.SignUpActivity);

        pd=new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Users");

        storageReference= FirebaseStorage.getInstance().getReference();

        SignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRegisterActivity.this,UserLoginActivity.class));
            }
        });
        String[] itemsGender1 = new String[]{"Select Gender", "Male", "Female"};

        Gender.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsGender1));

        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = Gender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UserRegisterActivity.this,
                        onDateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                DOB.setText(date);
            }
        };


        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap == null) {
                    Toast.makeText(UserRegisterActivity.this, "Please Set Image", Toast.LENGTH_SHORT).show();
                } else {
                    checkValidations();
                }
            }
        });

        MC_UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void checkValidations() {
        //    String downloadUrl="",name,email,password,address,mobile_number,gender,dob;

        name = Name.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();
        address = Address.getText().toString();
        mobile_number = MobileNumber.getText().toString();
        dob =DOB.getText().toString();

        String Email1;
        Email1 = Email.getText().toString().trim();

        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Email1.matches(emailpattern) && s.length()>0){
                  //  Toast.makeText(UserRegisterActivity.this, "Valid Email Address", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UserRegisterActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (bitmap==null){
            Toast.makeText(UserRegisterActivity.this,"Set Your Image",Toast.LENGTH_SHORT).show();
        }else if (name.isEmpty()){
            Name.setError("Name Empty");
            Name.requestFocus();
        }else if (email.isEmpty()){
            Email.setError("Email Empty");
            Email.requestFocus();
        }else if (password.isEmpty()){
            Password.setError("Password Empty");
            Password.requestFocus();
        }else if (address.isEmpty()){
            Address.setError("Address Empty");
            Address.requestFocus();
        }else if (mobile_number.isEmpty()){
            MobileNumber.setError("Mobile Number Empty");
            MobileNumber.requestFocus();
        } else if (mobile_number.length()<10){
            Toast.makeText(UserRegisterActivity.this,"Give 10 Numbers Only",Toast.LENGTH_SHORT).show();
        }else if (mobile_number.length()>10){
            Toast.makeText(UserRegisterActivity.this,"Give 10 Numbers Only",Toast.LENGTH_SHORT).show();
        }else if (gender.equals("Select Gender")) {
            Toast.makeText(UserRegisterActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (dob.equals("Date Of Birth")){
            Toast.makeText(UserRegisterActivity.this,"Date Of Birth Empty",Toast.LENGTH_SHORT).show();
        }else {
            uploadUserImage();
        }
    }

    private void uploadUserImage() {
        pd.setMessage(name+" "+"Please Wait...");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;
        filePath=storageReference.child("Users").child(name+" "+finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UserRegisterActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertData(email, password);
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(UserRegisterActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertData(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser1 = auth.getCurrentUser();
                String userid = firebaseUser1.getUid();

                String uniqueKey = reference.push().getKey();
                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("UserImage",downloadUrl);
                hashMap.put("Name",name);
                hashMap.put("Email",email);
                hashMap.put("Password",password);
                hashMap.put("Address",address);
                hashMap.put("MobileNumber",mobile_number);
                hashMap.put("Gender",gender);
                hashMap.put("DOB",dob);
                hashMap.put("UserId",userid);
                hashMap.put("UniqueKey",uniqueKey);

                reference.child(userid).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        pd.dismiss();
                        Toast.makeText(UserRegisterActivity.this,name+" "+"Registered Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserRegisterActivity.this, UserMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                        editor.putString("UserId",userid);
                        editor.apply();

                        startActivity(intent);
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserRegisterActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void openGallery() {
        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {

            Uri uri = data.getData();


            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserImage.setImageBitmap(bitmap);
            UserImage_Text.setVisibility(View.INVISIBLE);
        }
    }
}