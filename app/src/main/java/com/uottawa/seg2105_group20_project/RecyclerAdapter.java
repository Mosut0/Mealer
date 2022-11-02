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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Complaint> complaintList;

    public RecyclerAdapter(List<Complaint> complaintList){
        this.complaintList = complaintList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView complaineeText;
        private final TextView complainantText;
        private final TextView complaintDescriptionText;
        private RecyclerAdapter adapter;


        public MyViewHolder(final View view){
            super(view);
            complaineeText = view.findViewById(R.id.complaineeText);
            complainantText = view.findViewById(R.id.complainantText);
            complaintDescriptionText = view.findViewById(R.id.complaintDescriptionText);

            view.findViewById(R.id.complaintDismissBtn).setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                FirebaseDatabase.getInstance().getReference("complaints")
                        .child(complaintList.get(adapterPosition).getDbId()).removeValue();
                adapter.complaintList.remove(adapterPosition);
                adapter.notifyItemChanged(adapterPosition);
            });
        }

        public MyViewHolder linkAdapter(RecyclerAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_items, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String complainee = complaintList.get(position).getComplainee();
        String complainant = complaintList.get(position).getComplainant();
        String description = complaintList.get(position).getDescription();
        holder.complaineeText.setText(Html.fromHtml("<b>" + "Complainee: " + "</b>" + complainee));
        holder.complainantText.setText(Html.fromHtml("<b>" + "Complainant: " + "</b>" + complainant));
        holder.complaintDescriptionText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + description));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
}
