package com.uottawa.seg2105_group20_project;

import android.text.Html;
import android.text.TextUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.*;
import android.view.*;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;

public class RecyclerAdapterPurchaseRequest extends RecyclerView.Adapter<RecyclerAdapterPurchaseRequest.MyViewHolder>{


    private List<PurchaseRequest> purchaseRequestList;
    private List<Meal> meals;
    private List<Client> clients;

    public RecyclerAdapterPurchaseRequest(List<PurchaseRequest> purchaseRequestList, List<Meal> meals, List<Client> clients){
        this.purchaseRequestList = purchaseRequestList;
        this.meals = meals;
        this.clients = clients;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView clientNameText;
        private final TextView clientEmailText;
        private final TextView clientAddressText;
        private final TextView mealPickupTime;

        private final TextView mealNameText;
        private final TextView mealTypeText;
        private final TextView cuisineTypeText;
        private final TextView mealAllergensText;
        private final TextView mealIngredientsText;
        private final TextView mealDescriptionText;
        private final TextView mealPriceText;

        private RecyclerAdapterPurchaseRequest adapter;

        DatabaseReference dbPurchaseRequests, dbCooks;

        Button rejectBtn, approveBtn;
        public MyViewHolder(final View view) {
            super(view);

            mealNameText = (TextView) view.findViewById(R.id.cookPurchaseMealName);
            mealTypeText = (TextView) view.findViewById(R.id.cookPurchaseMealType);
            cuisineTypeText = (TextView) view.findViewById(R.id.cookPurchaseCuisineType);
            mealAllergensText = (TextView) view.findViewById(R.id.cookPurchaseMealAllergens);
            mealIngredientsText = (TextView) view.findViewById(R.id.cookPurchaseMealIngredients);
            mealDescriptionText = (TextView) view.findViewById(R.id.cookPurchaseMealDescription);
            mealPriceText = (TextView) view.findViewById(R.id.cookPurchaseMealPrice);

            mealPickupTime = (TextView) view.findViewById(R.id.cookPurchasePickupTime);

            clientNameText = (TextView) view.findViewById(R.id.cookPurchaseClientName);
            clientEmailText = (TextView) view.findViewById(R.id.cookPurchaseClientEmail);
            clientAddressText = (TextView) view.findViewById(R.id.cookPurchaseClientAddress);

            rejectBtn = (Button) view.findViewById(R.id.cookPurchaseReject);
            approveBtn = (Button) view.findViewById(R.id.cookPurchaseApprove);

            dbPurchaseRequests = FirebaseDatabase.getInstance().getReference("purchaseRequests");
            dbCooks = FirebaseDatabase.getInstance().getReference("cooks");

            rejectBtn.setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                dbPurchaseRequests.child(purchaseRequestList.get(adapterPosition).id).child("status").setValue("Rejected");
                Toast.makeText(itemView.getContext(), "Purchase Request Rejected", Toast.LENGTH_LONG).show();
            });

            approveBtn.setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                String cookID = purchaseRequestList.get(adapterPosition).cookId;
                String purchaseID = purchaseRequestList.get(adapterPosition).id;
                Task<DataSnapshot> task = dbCooks.child(purchaseRequestList.get(adapterPosition).cookId).child("sales").get();
                task.addOnSuccessListener(result -> {
                    long sales = (long) result.getValue();

                    dbPurchaseRequests.child(purchaseID).child("status").setValue("Approved");
                    dbCooks.child(cookID).child("sales").setValue(sales+1);
                });
                Toast.makeText(itemView.getContext(), "Purchase Request Approved", Toast.LENGTH_LONG).show();
            });
        }

        public MyViewHolder linkAdapter(RecyclerAdapterPurchaseRequest adapter){
            this.adapter = adapter;
            return this;
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterPurchaseRequest.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_purchase_items, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal meal = getMealFromPurchase(purchaseRequestList.get(position).mealId);
        String mealName = meal.getMealName();
        String mealType = meal.getMealType();
        String cuisineType = meal.getCuisineType();
        String mealAllergens = meal.getAllergens();
        String mealIngredients = meal.getIngredients();
        String mealDescription = meal.getDescription();
        String mealPrice = meal.getPrice();
        String pickUpTime = purchaseRequestList.get(position).pickupTime;

        Client client = getClientFromPurchase(purchaseRequestList.get(position).clientId);
        String clientName = client.firstName + " " + client.lastName;
        String clientEmail = client.email;
        String clientAddress = client.address;

        holder.mealNameText.setText(Html.fromHtml("<b>" + "Meal Name: " + "</b>" + mealName));
        holder.mealTypeText.setText(Html.fromHtml("<b>" + "Meal Type: " + "</b>" + mealType));
        holder.cuisineTypeText.setText(Html.fromHtml("<b>" + "Cuisine Type: " + "</b>" + cuisineType));
        holder.mealAllergensText.setText(Html.fromHtml("<b>" + "Allergens: " + "</b>" + mealAllergens));
        holder.mealIngredientsText.setText(Html.fromHtml("<b>" + "Ingredients: " + "</b>" + mealIngredients));
        holder.mealDescriptionText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + mealDescription));
        holder.mealPriceText.setText(Html.fromHtml("<b>" + "Price: " + "</b>" + mealPrice + "$"));

        holder.mealPickupTime.setText(Html.fromHtml("<b>" + "Pick-up Time: " + "</b>" + pickUpTime));

        holder.clientNameText.setText(Html.fromHtml("<b>" + "Client Name: " + "</b>" + clientName));
        holder.clientEmailText.setText(Html.fromHtml("<b>" + "Client Email: " + "</b>" + clientEmail));
        holder.clientAddressText.setText(Html.fromHtml("<b>" + "Client Address: " + "</b>" + clientAddress));
    }

    @Override
    public int getItemCount() {
        return purchaseRequestList.size();
    }

    public Meal getMealFromPurchase(String mealID){
        for(Meal meal : meals){
            if(meal.getMealID().equals(mealID)){
                return meal;
            }
        }
        return null;
    }

    public Client getClientFromPurchase(String clientID){
        for(Client client : clients){
            if(client.id.equals(clientID)){
                return client;
            }
        }
        return null;
    }

}
