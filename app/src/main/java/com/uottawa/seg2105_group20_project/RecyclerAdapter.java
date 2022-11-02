package com.uottawa.seg2105_group20_project;

import android.annotation.SuppressLint;
import android.text.*;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.*;
import android.view.*;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Complaint> complaintList;

    public RecyclerAdapter(ArrayList<Complaint> complaintList){
        this.complaintList = complaintList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView complaineeText;
        private TextView complainantText;
        private TextView complaintDescriptionText;


        public MyViewHolder(final View view){
            super(view);
            complaineeText = view.findViewById(R.id.complaineeText);
            complainantText = view.findViewById(R.id.complainantText);
            complaintDescriptionText = view.findViewById(R.id.complaintDescriptionText);
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String complainee = complaintList.get(position).getComplainee();
        String complainant = complaintList.get(position).getComplainant();
        String description = complaintList.get(position).getComplaintDescription();
        holder.complaineeText.setText(Html.fromHtml("<b>" + "Complainee: " + "</b>" + complainee));
        holder.complainantText.setText(Html.fromHtml("<b>" + "Complainant: " + "</b>" + complainant));
        holder.complaintDescriptionText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + description));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
}
