package com.example.puc.adapater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puc.R;
import com.example.puc.activity.CreateOfficerActivity;
import com.example.puc.modal.OfficerData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OfficerAdapter extends RecyclerView.Adapter<OfficerAdapter.ViewHolder> {



    private Context context;
    private ArrayList<OfficerData> items;

    public OfficerAdapter(Context context, ArrayList<OfficerData> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.officer_adapter,parent,false);

        return new OfficerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OfficerData data = items.get(position);
        holder.OfficerVillage.setText(data.getOfficerVillage());
        holder.OfficerMobileNumber.setText(data.getOfficerMobilenumber());
        holder.OfficerName.setText(data.getOfficerName());


        //Update
        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.LL_Visiblity.setVisibility(View.VISIBLE);

                holder.OfficerNameUpdate.setText(data.getOfficerName());
                holder.OfficerMobileNumberUpdate.setText(data.getOfficerMobilenumber());
                holder.OfficerMobileNumberUpdate.setFocusable(false);
                holder.OfficerPasswordUpdate.setText(data.getOfficerPassword());

                //Spinner Villages
                String[] itemsVillages = new String[]{data.getOfficerVillage(),"Select Village", "Beemakulam", "Devasathanam","Echangal",
                        "Elayanagaram","Girisamuthiram","Gollakuppam","Govindapuram"
                        ,"Jaffarabad","Kothakottai","Madhanancheri","Marimanikuppam"
                        ,"Mittur","Naickanur","Narasingapuram","Nekkanamalai","Nimmiyambattu"
                        ,"Pallipattu","Periyakurumbatheru","Pethaveppambattu","Poongulam","Reddiur"
                        ,"Sammanthikuppam","Valayambattu","Vallipattu","Velathigamanibenda","Vellakuttai"
                        ,"Vijilapuram"};

                holder.Complainter_VillageUpdate.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                        itemsVillages));

                holder.Complainter_VillageUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        holder.c_village = holder.Complainter_VillageUpdate.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                holder.Edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String on = holder.OfficerNameUpdate.getText().toString();
                        String o_mn = holder.OfficerMobileNumberUpdate.getText().toString();
                        String o_p = holder.OfficerPasswordUpdate.getText().toString();


                        if (on.isEmpty()){
                            holder.OfficerNameUpdate.setError("Required Name");
                            holder.OfficerNameUpdate.requestFocus();
                        }else if (o_mn.isEmpty()){
                            holder.OfficerMobileNumberUpdate.setError("Required Mobile Number");
                            holder.OfficerMobileNumberUpdate.requestFocus();
                        }else if (o_p.isEmpty()){
                            holder.OfficerPasswordUpdate.setError("Required Password");
                            holder.OfficerPasswordUpdate.requestFocus();
                        }else {
                            UpdateData(on,o_mn,o_p,holder.c_village);
                        }




                    }

                    private void UpdateData(String on, String o_mn, String o_p, String c_village) {

                        Calendar calForDate = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                        String date=currentDate.format(calForDate.getTime());


                        Calendar calForTIme = Calendar.getInstance();
                        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                        String time=currentTime.format(calForTIme.getTime());

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Officers");

                        HashMap<String, Object> hashMap = new HashMap<>();

                        hashMap.put("OfficerId", data.getOfficerId());
                        hashMap.put("OfficerName", on);
                        hashMap.put("OfficerMobilenumber", o_mn);
                        hashMap.put("OfficerPassword", o_p);
                        hashMap.put("OfficerVillage",c_village);
                        hashMap.put("Date",date);
                        hashMap.put("Time",time);
                        hashMap.put("Status","0");

                        reference.child(o_mn).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                holder.LL_Visiblity.setVisibility(View.GONE);
                                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });




            }
        });


        //Delete
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure,you want to Delete");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                DatabaseReference referenceDelete = FirebaseDatabase.getInstance().getReference("Officers");



                                String o_mn = data.getOfficerMobilenumber();

                                referenceDelete.child(o_mn).removeValue();

                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        String c_village;

        TextView OfficerVillage,OfficerName,OfficerMobileNumber;

        Button Update,Delete;

        LinearLayout LL_Visiblity;
        EditText OfficerNameUpdate,OfficerMobileNumberUpdate,OfficerPasswordUpdate;
        Spinner Complainter_VillageUpdate;
        Button Edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            OfficerVillage = itemView.findViewById(R.id.OfficerVillage);
            OfficerName = itemView.findViewById(R.id.OfficerName);
            OfficerMobileNumber = itemView.findViewById(R.id.OfficerMobileNumber);

            Update = itemView.findViewById(R.id.Update);
            Delete = itemView.findViewById(R.id.Delete);
            LL_Visiblity = itemView.findViewById(R.id.LL_Visiblity);
            OfficerNameUpdate = itemView.findViewById(R.id.OfficerNameUpdate);
            OfficerMobileNumberUpdate = itemView.findViewById(R.id.OfficerMobileNumberUpdate);
            OfficerPasswordUpdate = itemView.findViewById(R.id.OfficerPasswordUpdate);
            Complainter_VillageUpdate = itemView.findViewById(R.id.Complainter_VillageUpdate);
            Edit = itemView.findViewById(R.id.Edit);


        }
    }

}
