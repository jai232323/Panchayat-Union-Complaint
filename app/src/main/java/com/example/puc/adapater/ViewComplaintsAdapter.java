package com.example.puc.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.puc.R;
import com.example.puc.modal.Complaints;

import java.util.ArrayList;

public class ViewComplaintsAdapter extends RecyclerView.Adapter<ViewComplaintsAdapter.ViewHolder> {



    Context context;
    ArrayList<Complaints> list;


    public ViewComplaintsAdapter(Context context, ArrayList<Complaints> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.viewcomplaint_adapter,parent,false);

        return new ViewComplaintsAdapter.ViewHolder(view1);
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
        holder.ResultView.setText("RESULT : "+data.getResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView ProblemPhoto,ProofPhoto;
        TextView Complainter_Name,Complainter_MobileNumber,Complainter_Address,Complainter_Village,
                Complainter_Ward,Complainter_Taluk,Complainter_Box;

        TextView ResultView;


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


            ResultView = itemView.findViewById(R.id.ResultView);


        }
    }
}
