package com.example.puc.adapater;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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
import com.example.puc.modal.Complaints;
import com.example.puc.modal.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder> {


    Context context;
    ArrayList<Complaints> list;

    Activity activity;

    int REQ1 = 1;
    Bitmap bitmap1;



    public ComplaintsAdapter(Context context, ArrayList<Complaints> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.complaint_adapter,parent,false);

        return new ComplaintsAdapter.ViewHolder(view1);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Complaints data = list.get(position);


        Glide.with(context).load(data.getC_ProblemPhoto()).into(holder.ProblemPhoto);
        Glide.with(context).load(data.getC_ProofPhoto()).into(holder.ProofPhoto);

        holder.Complainter_Name.setText(data.getC_Name());
        holder.Complainter_MobileNumber.setText(data.getC_MobileNumber());
        holder.Complainter_Address.setText(data.getC_Address());
        holder.Complainter_Village.setText(data.getC_Village());
        holder.Complainter_Ward.setText(data.getC_Ward());
        holder.Complainter_Taluk.setText(data.getC_Taluk());
        holder.Complainter_Box.setText(data.getC_Box());




        holder.ResultView.setText(data.getResult());
        holder.ResultView.setVisibility(View.VISIBLE);

//        holder.Description.setVisibility(View.GONE);
//        holder.SUBMIT.setVisibility(View.GONE);

        holder.SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String des = holder.Description.getText().toString();

                String Cid = data.getC_id();
                GiveResult(des,Cid);


            }
        });


//        holder.Result_Photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });





    }

    private void GiveResult(String des, String cid) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Complaints");


        SharedPreferences prefs1 = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);
        String OfficerMobilenumber=prefs1.getString("OfficerMobilenumber","none");


        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("Result",des);
        hashMap.put("OfficerMobilenumber",OfficerMobilenumber);

        reference.child(cid).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(context, " Success ", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

//        reference.child(cid).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//
//                Toast.makeText(context, " Success ", Toast.LENGTH_SHORT).show();
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    private void openGallery() {



        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(picImage,REQ1);



    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView ProblemPhoto,ProofPhoto;
        TextView Complainter_Name,Complainter_MobileNumber,Complainter_Address,Complainter_Village,
                Complainter_Ward,Complainter_Taluk,Complainter_Box;

        //Result
//        ImageView Result_Photo;
        TextView Description;
        TextView ResultView;
        Button SUBMIT;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ProblemPhoto = itemView.findViewById(R.id.ProblemPhoto);
            ProofPhoto = itemView.findViewById(R.id.ProofPhoto);
            Complainter_Name = itemView.findViewById(R.id.Complainter_Name);
            Complainter_MobileNumber = itemView.findViewById(R.id.Complainter_MobileNumber);
            Complainter_Address = itemView.findViewById(R.id.Complainter_Address);
            Complainter_Village = itemView.findViewById(R.id.Complainter_Village);
            Complainter_Ward = itemView.findViewById(R.id.Complainter_Ward);
            Complainter_Taluk = itemView.findViewById(R.id.Complainter_Taluk);
            Complainter_Box = itemView.findViewById(R.id.Complainter_Box);

           // Result_Photo = itemView.findViewById(R.id.Result_Photo);
            Description = itemView.findViewById(R.id.Description);
            ResultView = itemView.findViewById(R.id.ResultView);
            SUBMIT = itemView.findViewById(R.id.SUBMIT);



        }





    }



}
