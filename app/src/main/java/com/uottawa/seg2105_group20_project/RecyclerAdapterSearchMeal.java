package com.uottawa.seg2105_group20_project;

import android.text.Html;
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

public class RecyclerAdapterSearchMeal extends RecyclerView.Adapter<RecyclerAdapterSearchMeal.MyViewHolder> {

    private List<Meal> meals;
    private List<Cook> cooks;
    private String clientID;

    public RecyclerAdapterSearchMeal(List<Meal> meals, List<Cook> cooks, String clientID){
        this.meals = meals;
        this.cooks = cooks;
        this.clientID = clientID;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView mealNameText;
        private final TextView mealTypeText;
        private final TextView cuisineTypeText;
        private final TextView mealAllergensText;
        private final TextView mealIngredientsText;
        private final TextView mealDescriptionText;
        private final TextView mealPriceText;

        private final TextView cookNameText;
        private final TextView cookEmailText;
        private final TextView cookAddressText;
        private final TextView cookDescriptionText;
        private final TextView cookRatingText;


        private RecyclerAdapterSearchMeal adapter;

        DatabaseReference dbPurchaseRequests;
        EditText pickUpTimeInput;
        public MyViewHolder(final View view) {
            super(view);

            mealNameText = (TextView) view.findViewById(R.id.searchedMealName);
            mealTypeText = (TextView) view.findViewById(R.id.searchedMealType);
            cuisineTypeText = (TextView) view.findViewById(R.id.searchedCuisineType);
            mealAllergensText = (TextView) view.findViewById(R.id.searchedMealAllergens);
            mealIngredientsText = (TextView) view.findViewById(R.id.searchedMealIngredients);
            mealDescriptionText = (TextView) view.findViewById(R.id.searchedMealDescription);
            mealPriceText = (TextView) view.findViewById(R.id.searchedMealPrice);

            cookNameText = (TextView) view.findViewById(R.id.searchedCookName);
            cookEmailText = (TextView) view.findViewById(R.id.searchedCookEmail);
            cookAddressText = (TextView) view.findViewById(R.id.searchedCookAddress);
            cookDescriptionText = (TextView) view.findViewById(R.id.searchedCookDescription);
            cookRatingText = (TextView) view.findViewById(R.id.searchedCookRating);

            pickUpTimeInput = (EditText) view.findViewById(R.id.pickUpTimeInput);

            dbPurchaseRequests = FirebaseDatabase.getInstance().getReference("purchaseRequests");

            view.findViewById(R.id.purchaseMealButton).setOnClickListener(itemView -> {

                try {
                    int adapterPosition = getAdapterPosition();
                    String[] pickUpTime = pickUpTimeInput.getText().toString().split("/");
                    if(pickUpTime[0].length() == 2 && pickUpTime[1].length() == 2 && pickUpTime[2].length() == 2 && pickUpTime[3].length() == 2 && pickUpTime[4].length() == 4  && pickUpTime.length == 5){
                        int hours = Integer.parseInt(pickUpTime[0]);
                        int minutes = Integer.parseInt(pickUpTime[1]);
                        int day = Integer.parseInt(pickUpTime[2]);
                        int month = Integer.parseInt(pickUpTime[3]);
                        int year = Integer.parseInt(pickUpTime[4]);
                        if ((day >= 0 && day <= 31) && (hours >= 0 && hours <= 24) && (minutes >= 0 && minutes <= 59) && (month >= 0 && month <= 12) && year >= 0) {
                            String id = dbPurchaseRequests.push().getKey();
                            Meal meal = meals.get(adapterPosition);
                            String pickUpTimeString = hours + ":" + minutes + " on " + day + "/" + month + "/" + year;
                            PurchaseRequest purchaseRequest = new PurchaseRequest(id, meal.getCookID(), clientID, meal.getMealID(), pickUpTimeString);
                            dbPurchaseRequests.child(id).setValue(purchaseRequest);
                            Toast.makeText(view.getContext(), "Purchase Request Sent Successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(view.getContext(), "Invalid Pickup Time", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(view.getContext(), "Invalid Pickup Time", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Toast.makeText(view.getContext(), "Invalid Pickup Time", Toast.LENGTH_LONG).show();
                }
            });

        }

        public MyViewHolder linkAdapter(RecyclerAdapterSearchMeal adapter){
            this.adapter = adapter;
            return this;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.searched_meal_items, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String mealName = meals.get(position).getMealName();
        String mealType = meals.get(position).getMealType();
        String cuisineType = meals.get(position).getCuisineType();
        String mealAllergens = meals.get(position).getAllergens();
        String mealIngredients = meals.get(position).getIngredients();
        String mealDescription = meals.get(position).getDescription();
        String mealPrice = meals.get(position).getPrice();

        Cook cook = getCookFromMeal(meals.get(position).getCookID());
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
        holder.mealPriceText.setText(Html.fromHtml("<b>" + "Price: " + "</b>" + mealPrice + "$"));

        holder.cookNameText.setText(Html.fromHtml("<b>" + "Cook Name: " + "</b>" + cookName));
        holder.cookEmailText.setText(Html.fromHtml("<b>" + "Cook Email: " + "</b>" + cookEmail));
        holder.cookAddressText.setText(Html.fromHtml("<b>" + "Cook Address: " + "</b>" + cookAddress));
        holder.cookDescriptionText.setText(Html.fromHtml("<b>" + "Cook Description: " + "</b>" + cookDescription));
        holder.cookRatingText.setText(Html.fromHtml("<b>" + "Cook Rating: " + "</b>" + cookRating + "/5"));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public Cook getCookFromMeal(String cookID){
        for(Cook cook : cooks){
            if(cook.id.equals(cookID)){
                return cook;
            }
        }
        return null;
    }

}
