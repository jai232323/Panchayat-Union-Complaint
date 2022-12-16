package com.example.puc.adapater;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.puc.R;
import com.example.puc.activity.FullImagerActivity;
import com.example.puc.activity.UserMainActivity;
import com.example.puc.activity.UserRegisterActivity;
import com.example.puc.modal.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {


    Context context;
    ArrayList<Users> list;
    String UserId;

    public UsersAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.user_adapter,parent,false);

        return new UsersAdapter.ViewHolder(view1);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Users data = list.get(position);

        Glide.with(context).load(data.getUserImage()).into(holder.UserImage);

        holder.UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",data.getUserImage());
                context.startActivity(intent);
            }
        });

        holder.Name.setText(data.getName());
        holder.Email.setText(data.getEmail());
        holder.Address.setText(data.getAddress());
        holder.MobileNumber.setText(data.getMobileNumber());
        holder.Gender.setText(data.getGender());
        holder.DOB.setText(data.getDOB());



        SharedPreferences prefs1 = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        UserId=prefs1.getString("UserId","none");

        holder.DeleteThisUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");

                reference.child(UserId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseAuth firebaseAuth;
                        firebaseAuth=FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        Toast.makeText(context, data.getName()+" "+"deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView UserImage;
        private TextView Name,Email,Address,MobileNumber,Gender,DOB;
        private Button DeleteThisUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UserImage = itemView.findViewById(R.id.UserImage);
            Name = itemView.findViewById(R.id.Name);
            Email = itemView.findViewById(R.id.Email);
            Address = itemView.findViewById(R.id.Address);
            MobileNumber = itemView.findViewById(R.id.MobileNumber);
            Gender = itemView.findViewById(R.id.Gender);
            DOB = itemView.findViewById(R.id.DOB);
            DeleteThisUser = itemView.findViewById(R.id.DeleteThisUser);

        }
    }

}
