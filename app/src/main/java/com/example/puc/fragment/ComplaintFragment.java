package com.example.puc.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puc.R;
import com.example.puc.activity.MainActivity;
import com.example.puc.activity.UserMainActivity;
import com.example.puc.activity.UserRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class ComplaintFragment extends Fragment {



    //Initialize Variables
    EditText Complainter_Name,Complainter_MobileNumber,Complainter_Address,Complainter_Box;
    Spinner Complainter_Village,Complainter_Ward,Complainter_Taluk;
    MaterialCardView MC_Complainter_ComplaintPhoto,MC_Complainter_ProofPhoto;
    ImageView Complainter_ComplaintPhoto,Complainter_ProofPhoto;
    TextView ProblemText,Proof_Text;
    Button Submit;

    String c_name,c_mobileNumber,c_address,c_box,c_village,c_ward,c_taluk,mc_c_ComplaintPhoto,
            mc_c_ProofPhoto,downloadUrl_Complaint = "",downloadUrl_Proof = "";

    private final int REQ1 = 1,REQ2=2;
    private Bitmap bitmap1,bitmap2;

    private DatabaseReference reference;
    private StorageReference storageReference;
    private ProgressDialog pd;



    String MobileNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);

        pd = new ProgressDialog(getContext());
        storageReference = FirebaseStorage.getInstance().getReference();



        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", getContext().MODE_PRIVATE);
        MobileNumber = prefs1.getString("MobileNumber", "none");


        //Assign Variables
        //EditText
        Complainter_Name = view.findViewById(R.id.Complainter_Name);
        Complainter_MobileNumber = view.findViewById(R.id.Complainter_MobileNumber);
        Complainter_Address = view.findViewById(R.id.Complainter_Address);
        Complainter_Box = view.findViewById(R.id.Complainter_Box);
        //Spinner
        Complainter_Village = view.findViewById(R.id.Complainter_Village);
        Complainter_Ward = view.findViewById(R.id.Complainter_Ward);
        Complainter_Taluk = view.findViewById(R.id.Complainter_Taluk);
        //MaterialCardView
        MC_Complainter_ComplaintPhoto = view.findViewById(R.id.MC_Complainter_ComplaintPhoto);
        MC_Complainter_ProofPhoto = view.findViewById(R.id.MC_Complainter_ProofPhoto);
        //TextView
        Complainter_ComplaintPhoto = view.findViewById(R.id.Complainter_ComplaintPhoto);
        Complainter_ProofPhoto = view.findViewById(R.id.Complainter_ProofPhoto);
        Proof_Text = view.findViewById(R.id.Proof_Text);
        ProblemText = view.findViewById(R.id.ProblemText);
        //Button
        Submit = view.findViewById(R.id.Submit);

        //Spinner Villages
        String[] itemsVillages = new String[]{"Select Village", "Beemakulam", "Devasathanam","Echangal",
                "Elayanagaram","Girisamuthiram","Gollakuppam","Govindapuram"
        ,"Jaffarabad","Kothakottai","Madhanancheri","Marimanikuppam"
        ,"Mittur","Naickanur","Narasingapuram","Nekkanamalai","Nimmiyambattu"
        ,"Pallipattu","Periyakurumbatheru","Pethaveppambattu","Poongulam","Reddiur"
        ,"Sammanthikuppam","Valayambattu","Vallipattu","Velathigamanibenda","Vellakuttai"
        ,"Vijilapuram"};

        Complainter_Village.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, itemsVillages));

        Complainter_Village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c_village = Complainter_Village.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Ward
        String[] itemsWard = new String[]{"Select Ward","1","2","3","4","5","6","7","8","9","10","11","12"};

        Complainter_Ward.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, itemsWard));

        Complainter_Ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c_ward = Complainter_Ward.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Taluk
        String[] itemsTaluk = new String[]{"Select Taluk","Vaniyambadi","Tirupattur"};

        Complainter_Taluk.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, itemsTaluk));

        Complainter_Taluk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c_taluk = Complainter_Taluk.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //MaterialCarView
        MC_Complainter_ComplaintPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1();
            }
        });
        MC_Complainter_ProofPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery2();
            }
        });

        //Submit Button
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_name = Complainter_Name.getText().toString().trim();
                c_mobileNumber = Complainter_MobileNumber.getText().toString();
                c_address = Complainter_Address.getText().toString();
                c_box = Complainter_Box.getText().toString();

                //Name
                if (c_name.isEmpty()){
                    Complainter_Name.setError("Required Name");
                    Complainter_Name.requestFocus();
                }else if (c_name.length()<3){
                    Complainter_Name.setError("Name too short");
                    Complainter_Name.requestFocus();
                }else if (c_name.length()>30){
                    Complainter_Name.setError("Name too long");
                    Complainter_Name.requestFocus();
                }
                //MobileNumber
                else if (c_mobileNumber.isEmpty()){
                    Complainter_MobileNumber.setError("Required MobileNumber");
                    Complainter_MobileNumber.requestFocus();
                }else if (c_mobileNumber.length()<10){
                    Complainter_MobileNumber.setError("10 Numbers only");
                    Complainter_MobileNumber.requestFocus();
                }else if (c_mobileNumber.length()>10){
                    Complainter_MobileNumber.setError("10 Numbers only");
                    Complainter_MobileNumber.requestFocus();
                }
                //Address
                else if (c_address.isEmpty()){
                    Complainter_Address.setError("Required Address");
                    Complainter_Address.requestFocus();
                }else if (c_address.length()<3){
                    Complainter_Address.setError("Address too short");
                    Complainter_Address.requestFocus();
                }else if (c_address.length()>30){
                    Complainter_Address.setError("Address too long");
                    Complainter_Address.requestFocus();
                }
                //Village
                else  if (c_village.equals("Select Village")) {
                    Toast.makeText(getContext(), "Please Select Village", Toast.LENGTH_SHORT).show();
                }
                //Ward
                else if (c_ward.equals("Select Ward")){
                    Toast.makeText(getContext(), "Please Select Ward", Toast.LENGTH_SHORT).show();
                }
                //
                else if (c_taluk.equals("Select Taluk")){
                    Toast.makeText(getContext(), "Please Select Taluk", Toast.LENGTH_SHORT).show();
                }

                //Images
                else if (bitmap1==null){
                    Toast.makeText(getContext(), "Fix Problem Photo", Toast.LENGTH_SHORT).show();
                }else if (bitmap2==null){
                    Toast.makeText(getContext(), "Fix Proof Photo", Toast.LENGTH_SHORT).show();
                }

                //ComplaintBox
                else  if (c_box.isEmpty()){
                    Complainter_Box.setError("Complaint box Empty");
                    Complainter_Box.requestFocus();
                }else if (c_box.length()<3){
                    Complainter_Box.setError("Complaint too short");
                    Complainter_Box.requestFocus();
                }else if (c_box.length()>100){
                    Complainter_Box.setError("Complaint too long");
                    Complainter_Box.requestFocus();
                }
//                else {
//
//                }
                UploadProblemPhoto();


            }
        });

        return  view;
    }
    private void UploadProblemPhoto() {
        pd.setMessage(c_name+" "+"Please Wait...");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        String UserId=prefs1.getString("UserId","none");

        final StorageReference filePath;
        filePath=storageReference.child("Complaints").child(c_village).child(UserId).child(c_name+" "+finalimg+"jpg"+System.currentTimeMillis());
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener((Activity) getContext(), new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl_Complaint = String.valueOf(uri);
                                    UploadProofPhoto();
                                    //insertData(email, password);
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(getContext(),"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void UploadProofPhoto() {
        pd.setMessage(c_name+" "+"Please Wait...");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();


        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        String UserId=prefs1.getString("UserId","none");

        final StorageReference filePath;
        filePath=storageReference.child("Complaints").child(c_village).child(UserId).child(c_name+" "+finalimg+"jpg"+System.currentTimeMillis());
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener((Activity) getContext(), new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl_Proof = String.valueOf(uri);
                                    //insertData(email, password);
                                    insertData();
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(getContext(),"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertData() {

        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        String UserId=prefs1.getString("UserId","none");

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        String date=currentDate.format(calForDate.getTime());


        Calendar calForTIme = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time=currentTime.format(calForTIme.getTime());

        reference = FirebaseDatabase.getInstance().getReference().child("Complaints");

        String UniqueKey = reference.push().getKey();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("C_Name",c_name);
        hashMap.put("C_MobileNumber",c_mobileNumber);
        hashMap.put("C_Address",c_address);
        hashMap.put("C_Village",c_village);
        hashMap.put("C_Ward",c_ward);
        hashMap.put("C_Taluk",c_taluk);
        hashMap.put("C_ProblemPhoto",downloadUrl_Complaint);
        hashMap.put("C_ProofPhoto",downloadUrl_Proof);
        hashMap.put("C_Box",c_box);
        hashMap.put("UserId",UserId);
        hashMap.put("C_id",UniqueKey);
        hashMap.put("Date",date);
        hashMap.put("Time",time);
        hashMap.put("User_MobileNumber",MobileNumber);

        reference.child(UniqueKey).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(getContext(), "Complaint Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), UserMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                SharedPreferences.Editor editor = getContext().getSharedPreferences("PREFS",getContext().MODE_PRIVATE).edit();
                editor.putString("C_Village",c_village);
                editor.apply();

                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage()+"\nSomething went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void openGallery1() {
        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImage,REQ1);
    }

    private void openGallery2() {
        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImage,REQ2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ1 && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Complainter_ComplaintPhoto.setImageBitmap(bitmap1);
            ProblemText.setVisibility(View.INVISIBLE);
        }else if(requestCode==REQ2 && resultCode == RESULT_OK){
            Uri uri1 = data.getData();
            try {

                bitmap2 = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri1);
            }catch (IOException e1){
                e1.printStackTrace();
            }
            Complainter_ProofPhoto.setImageBitmap(bitmap2);
            Proof_Text.setVisibility(View.INVISIBLE);
        }
    }
}