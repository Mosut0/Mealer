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

public class RecyclerViewClientPurchase extends RecyclerView.Adapter<RecyclerViewClientPurchase.MyViewHolder>{

    private List<PurchaseRequest> purchaseList;
    private List<Meal> meals;
    private List<Cook> cooks;

    public RecyclerViewClientPurchase(List<PurchaseRequest> purchaseList, List<Meal> meals, List<Cook> cooks){
        this.purchaseList = purchaseList;
        this.meals = meals;
        this.cooks = cooks;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView mealNameText;
        private final TextView mealTypeText;
        private final TextView cuisineTypeText;
        private final TextView mealAllergensText;
        private final TextView mealIngredientsText;
        private final TextView mealDescriptionText;
        private final TextView mealPriceText;
        private final TextView mealPickupTime;
        private final TextView purchaseStatusText;

        private final TextView cookNameText;
        private final TextView cookEmailText;
        private final TextView cookAddressText;
        private final TextView cookDescriptionText;
        private final TextView cookRatingText;


        private RecyclerViewClientPurchase adapter;

        DatabaseReference dbPurchaseRequests;
        DatabaseReference dbCook;
        DatabaseReference dbClient;
        DatabaseReference dbComplaints;

        EditText ratingInput, complaintInput;
        Button rateBtn, complainBtn;
        public MyViewHolder(final View view) {
            super(view);

            mealNameText = (TextView) view.findViewById(R.id.clientPurchaseMealName);
            mealTypeText = (TextView) view.findViewById(R.id.clientPurchaseMealType);
            cuisineTypeText = (TextView) view.findViewById(R.id.clientPurchaseCuisineType);
            mealAllergensText = (TextView) view.findViewById(R.id.clientPurchaseMealAllergens);
            mealIngredientsText = (TextView) view.findViewById(R.id.clientPurchaseMealIngredients);
            mealDescriptionText = (TextView) view.findViewById(R.id.clientPurchaseMealDescription);
            mealPriceText = (TextView) view.findViewById(R.id.clientPurchaseMealPrice);

            mealPickupTime = (TextView) view.findViewById(R.id.clientPurchasePickupTime);
            purchaseStatusText = (TextView) view.findViewById(R.id.clientPurchaseMealStatus);

            cookNameText = (TextView) view.findViewById(R.id.clientPurchaseCookName);
            cookEmailText = (TextView) view.findViewById(R.id.clientPurchaseCookEmail);
            cookAddressText = (TextView) view.findViewById(R.id.clientPurchaseCookAddress);
            cookDescriptionText = (TextView) view.findViewById(R.id.clientPurchaseCookDescription);
            cookRatingText = (TextView) view.findViewById(R.id.clientPurchaseCookRating);

            ratingInput = (EditText) view.findViewById(R.id.ratingInput);
            complaintInput = (EditText) view.findViewById(R.id.complaintInput);

            rateBtn = (Button) view.findViewById(R.id.clientRateBtn);
            complainBtn = (Button) view.findViewById(R.id.clientComplainBtn);

            dbPurchaseRequests = FirebaseDatabase.getInstance().getReference("purchaseRequests");
            dbCook = FirebaseDatabase.getInstance().getReference("cooks");
            dbClient = FirebaseDatabase.getInstance().getReference("clients");
            dbComplaints = FirebaseDatabase.getInstance().getReference("complaints");

            rateBtn.setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();

                if(!purchaseList.get(adapterPosition).rated) {
                    try {
                        int rating = Integer.parseInt(ratingInput.getText().toString());
                        if (rating >= 1 && rating <= 5) {

                            Task<DataSnapshot> taskTotalRating = dbCook.child(purchaseList.get(adapterPosition).cookId).child("totalRatings").get();
                            taskTotalRating.addOnSuccessListener(result -> {
                                Long totalRating = (Long) result.getValue();

                                Task<DataSnapshot> taskCookRating = dbCook.child(purchaseList.get(adapterPosition).cookId).child("rating").get();
                                taskCookRating.addOnSuccessListener(result2 -> {
                                    Long cookRating = (Long) result2.getValue();
                                    dbCook.child(purchaseList.get(adapterPosition).cookId).child("rating").setValue(((cookRating * totalRating) + rating) / (totalRating + 1));
                                });

                                dbCook.child(purchaseList.get(adapterPosition).cookId).child("totalRatings").setValue(totalRating + 1);
                            });

                            dbPurchaseRequests.child(purchaseList.get(adapterPosition).id).child("rated").setValue(true);
                            Toast.makeText(itemView.getContext(), "Cook Rated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(view.getContext(), "Invalid Rating", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(view.getContext(), "Invalid Rating", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(view.getContext(), "Already Rated", Toast.LENGTH_LONG).show();
                }
            });

            complainBtn.setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                if(!purchaseList.get(adapterPosition).complained) {
                    String complaintDescription = complaintInput.getText().toString().trim();
                    if (!TextUtils.isEmpty(complaintDescription)) {
                        String complaintID = dbComplaints.push().getKey();
                        Cook cook = getCookFromPurchase(purchaseList.get(adapterPosition).cookId);
                        Task<DataSnapshot> taskClientFN = dbClient.child(purchaseList.get(adapterPosition).clientId).child("firstName").get();
                        taskClientFN.addOnSuccessListener(resultFN -> {
                            String clientFN = (String) resultFN.getValue();
                            Task<DataSnapshot> taskClientLN = dbClient.child(purchaseList.get(adapterPosition).clientId).child("lastName").get();
                            taskClientLN.addOnSuccessListener(resultLN -> {
                                String clientLN = (String) resultLN.getValue();
                                Complaint complaint = new Complaint(cook.firstName + " " + cook.lastName, cook.id, clientFN + " " + clientLN, complaintDescription, complaintID);
                                dbComplaints.child(complaintID).setValue(complaint);
                            });
                        });
                        dbPurchaseRequests.child(purchaseList.get(adapterPosition).id).child("complained").setValue(true);
                        Toast.makeText(view.getContext(), "Complaint Filed", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(view.getContext(), "Fill The Complaint Box", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(view.getContext(), "Already Filed a Complaint", Toast.LENGTH_LONG).show();
                }
            });
        }

        public MyViewHolder linkAdapter(RecyclerViewClientPurchase adapter){
            this.adapter = adapter;
            return this;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_purchase_items, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal meal = getMealFromPurchase(purchaseList.get(position).mealId);
        String mealName = meal.getMealName();
        String mealType = meal.getMealType();
        String cuisineType = meal.getCuisineType();
        String mealAllergens = meal.getAllergens();
        String mealIngredients = meal.getIngredients();
        String mealDescription = meal.getDescription();
        String mealPrice = meal.getPrice();
        String pickUpTime = purchaseList.get(position).pickupTime;
        String purchaseStatus = purchaseList.get(position).status;

        Cook cook = getCookFromPurchase(purchaseList.get(position).cookId);
        String cookName = cook.firstName + " " + cook.lastName;
        String cookEmail = cook.email;
        String cookAddress = cook.address;
        String cookDescription = cook.description;
        String cookRating = String.valueOf(cook.rating);

        holder.mealNameText.setText(Html.fromHtml("<b>" + "Meal Name: " + "</b>" + mealName));
        holder.mealTypeText.setText(Html.fromHtml("<b>" + "Meal Type: " + "</b>" + mealType));
        holder.cuisineTypeText.setText(Html.fromHtml("<b>" + "Cuisine Type: " + "</b>" + cuisineType));
        holder.mealAllergensText.setText(Html.fromHtml("<b>" + "Allergens: " + "</b>" + mealAllergens));
        holder.mealIngredientsText.setText(Html.fromHtml("<b>" + "Ingredients: " + "</b>" + mealIngredients));
        holder.mealDescriptionText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + mealDescription));
        if(purchaseStatus.equals("Approved")){
            if(purchaseList.get(position).complained){
                holder.complaintInput.setVisibility(View.GONE);
                holder.complainBtn.setVisibility(View.GONE);
            }
            if(purchaseList.get(position).rated){
                holder.ratingInput.setVisibility(View.GONE);
                holder.rateBtn.setVisibility(View.GONE);
            }
            holder.mealPriceText.setText(Html.fromHtml("<b>" + "Price: " + "</b>" + mealPrice + "$ (charged)"));
        }else{
            holder.mealPriceText.setText(Html.fromHtml("<b>" + "Price: " + "</b>" + mealPrice + "$ (uncharged)"));
            holder.ratingInput.setVisibility(View.GONE);
            holder.complaintInput.setVisibility(View.GONE);
            holder.rateBtn.setVisibility(View.GONE);
            holder.complainBtn.setVisibility(View.GONE);
        }
        holder.mealPickupTime.setText(Html.fromHtml("<b>" + "Pick-up Time: " + "</b>" + pickUpTime));
        holder.purchaseStatusText.setText(Html.fromHtml("<b>" + "Status: " + "</b>" + purchaseStatus));

        holder.cookNameText.setText(Html.fromHtml("<b>" + "Cook Name: " + "</b>" + cookName));
        holder.cookEmailText.setText(Html.fromHtml("<b>" + "Cook Email: " + "</b>" + cookEmail));
        holder.cookAddressText.setText(Html.fromHtml("<b>" + "Cook Address: " + "</b>" + cookAddress));
        holder.cookDescriptionText.setText(Html.fromHtml("<b>" + "Cook Description: " + "</b>" + cookDescription));
        holder.cookRatingText.setText(Html.fromHtml("<b>" + "Cook Rating: " + "</b>" + cookRating + "/5"));
    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public Cook getCookFromPurchase(String cookID){
        for(Cook cook : cooks){
            if(cook.id.equals(cookID)){
                return cook;
            }
        }
        return null;
    }

    public Meal getMealFromPurchase(String mealID){
        for(Meal meal : meals){
            if(meal.getMealID().equals(mealID)){
                return meal;
            }
        }
        return null;
    }
}
