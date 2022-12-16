package com.example.puc.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.puc.R;
import com.example.puc.activity.FullImagerActivity;
import com.example.puc.activity.UserLoginActivity;
import com.example.puc.activity.ViewComplaintAndResultActivity;
import com.example.puc.modal.Users;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {


    //Initialize Variables

    ImageView UserImage;
    TextView Name,Email,Address,MobileNumber,Gender,DOB,Logout;

    String UserId;
    FirebaseAuth firebaseAuth;

    MaterialCardView ViewComplaint;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //Assign Variables
        UserImage = view.findViewById(R.id.UserImage);
        Name = view.findViewById(R.id.Name);
        Email = view.findViewById(R.id.Email);
        Address = view.findViewById(R.id.Address);
        MobileNumber = view.findViewById(R.id.MobileNumber);
        Gender = view.findViewById(R.id.Gender);
        DOB = view.findViewById(R.id.DOB);
        Logout = view.findViewById(R.id.Logout);
        ViewComplaint = view.findViewById(R.id.ViewComplaint);



        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        UserId=prefs1.getString("UserId","none");

        firebaseAuth=FirebaseAuth.getInstance();


        //Logout
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users")
                        .child(UserId);


                reference1.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        firebaseAuth.signOut();
                        Toast.makeText(getContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), UserLoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        //get user profile or user details
        getUserProfile();

        return view;
    }

    private void getUserProfile() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users")
                .child(UserId);

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((getContext() == null)){
                    return;
                }
                Users data = snapshot.getValue(Users.class);


                assert data != null;
                Glide.with(getContext()).load(data.getUserImage()).into(UserImage);

                UserImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), FullImagerActivity.class);
                        intent.putExtra("image",data.getUserImage());
                        getContext().startActivity(intent);
                    }
                });

                //Name.setText(data.getName());
                Name.setText(data.getName());
                Email.setText(data.getEmail());
                Address.setText(data.getAddress());
                MobileNumber.setText(data.getMobileNumber());
                Gender.setText(data.getGender());
                DOB.setText(data.getDOB());

                ViewComplaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(getContext(), ViewComplaintAndResultActivity.class);
                        startActivity(intent);

                        SharedPreferences.Editor editor1 = getContext().getSharedPreferences("PREFS",getContext().MODE_PRIVATE).edit();
                        editor1.putString("MobileNumber",data.getMobileNumber());
                        editor1.apply();





                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}