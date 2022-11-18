package com.uottawa.seg2105_group20_project;

import android.annotation.SuppressLint;
import android.text.*;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.*;
import android.view.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;

public class RecyclerAdapterComplaint extends RecyclerView.Adapter<RecyclerAdapterComplaint.MyViewHolder> {

    private List<Complaint> complaintList;

    public RecyclerAdapterComplaint(List<Complaint> complaintList){
        this.complaintList = complaintList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView complaineeText;
        private final TextView complaineeIDText;
        private final TextView complainantText;
        private final TextView complaintDescriptionText;
        private RecyclerAdapterComplaint adapter;

        DatabaseReference dbComplaints;
        DatabaseReference dbCooks;
        EditText suspensionInput;


        public MyViewHolder(final View view){
            super(view);
            complaineeText = view.findViewById(R.id.complaineeText);
            complainantText = view.findViewById(R.id.complainantText);
            complaineeIDText = view.findViewById(R.id.complaineeIDText);
            complaintDescriptionText = view.findViewById(R.id.complaintDescriptionText);
            suspensionInput = (EditText) view.findViewById(R.id.suspensionTimeInput);

            dbComplaints = FirebaseDatabase.getInstance().getReference("complaints");
            dbCooks = FirebaseDatabase.getInstance().getReference("cooks");

            view.findViewById(R.id.complaintDismissBtn).setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                dbComplaints.child(complaintList.get(adapterPosition).getDbId()).removeValue();
                adapter.complaintList.remove(adapterPosition);
                adapter.notifyItemChanged(adapterPosition);
                Toast.makeText(itemView.getContext(), "Complaint Dismissed", Toast.LENGTH_LONG).show();

            });

            view.findViewById(R.id.complaintSuspendBtn).setOnClickListener(itemView -> {

                try{
                    int adapterPosition = getAdapterPosition();
                    String[] suspensionTime = suspensionInput.getText().toString().split("/");
                    int days = Integer.parseInt(suspensionTime[0]);
                    int hours = Integer.parseInt(suspensionTime[1]);
                    int minutes = Integer.parseInt(suspensionTime[2]);
                    if(days >= 0 && hours >= 0 && minutes >= 0){
                        String cookID = (String) dbComplaints.child(complaintList.get(adapterPosition).getDbId()).child("complaineeID").get().getResult().getValue();
                        dbCooks.child(cookID).child("suspension").setValue("Suspended for: " + days + "D " + hours + "H " + minutes + "M");
                        dbComplaints.child(complaintList.get(adapterPosition).getDbId()).removeValue();
                        adapter.complaintList.remove(adapterPosition);
                        adapter.notifyItemChanged(adapterPosition);
                        Toast.makeText(itemView.getContext(), "Cook Dismissed", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Toast.makeText(view.getContext(), "Invalid Suspension Time", Toast.LENGTH_LONG).show();
                }
            });

            view.findViewById(R.id.complaintBanBtn).setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                String cookID = (String) dbComplaints.child(complaintList.get(adapterPosition).getDbId()).child("complaineeID").get().getResult().getValue();
                dbCooks.child(cookID).child("suspension").setValue("Banned");
                dbComplaints.child(complaintList.get(adapterPosition).getDbId()).removeValue();
                adapter.complaintList.remove(adapterPosition);
                adapter.notifyItemChanged(adapterPosition);
                Toast.makeText(view.getContext(), "Cook Banned", Toast.LENGTH_LONG).show();

            });

        }

        public MyViewHolder linkAdapter(RecyclerAdapterComplaint adapter){
            this.adapter = adapter;
            return this;
        }
    }
    @NonNull
    @Override
    public RecyclerAdapterComplaint.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_items, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String complainee = complaintList.get(position).getComplainee();
        String complaineeID = complaintList.get(position).getComplaineeID();
        String complainant = complaintList.get(position).getComplainant();
        String description = complaintList.get(position).getDescription();
        holder.complaineeText.setText(Html.fromHtml("<b>" + "Complainee: " + "</b>" + complainee));
        holder.complaineeIDText.setText(Html.fromHtml("<b>" + "Complainee ID: " + "</b>" + complaineeID));
        holder.complainantText.setText(Html.fromHtml("<b>" + "Complainant: " + "</b>" + complainant));
        holder.complaintDescriptionText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + description));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
}
