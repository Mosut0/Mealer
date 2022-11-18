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

public class RecyclerAdapterMenu extends RecyclerView.Adapter<RecyclerAdapterMenu.MyViewHolder> {

    private List<Meal> menuList;
    private String cookID;

    public RecyclerAdapterMenu(List<Meal> menuList, String cookID){
        this.cookID = cookID;
        this.menuList = menuList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView mealNameText;
        private final TextView mealTypeText;
        private final TextView cuisineTypeText;
        private final TextView ingredientsText;
        private final TextView allergensText;
        private final TextView priceText;
        private final TextView descriptionText;
        private RecyclerAdapterMenu adapter;

        DatabaseReference dbMenu;

        public MyViewHolder(final View view){
            super(view);
            mealNameText = view.findViewById(R.id.mealNameText);
            mealTypeText = view.findViewById(R.id.mealTypeText);
            cuisineTypeText = view.findViewById(R.id.cuisineTypeText);
            ingredientsText = view.findViewById(R.id.ingredientsText);
            allergensText = view.findViewById(R.id.allergensText);
            priceText = view.findViewById(R.id.priceText);
            descriptionText = view.findViewById(R.id.descriptionText);

            dbMenu = FirebaseDatabase.getInstance().getReference("meals").child(cookID);

            view.findViewById(R.id.deleteMealBtn).setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                dbMenu.child(menuList.get(adapterPosition).getMealID()).removeValue();
                adapter.menuList.remove(adapterPosition);
                adapter.notifyItemChanged(adapterPosition);
                Toast.makeText(itemView.getContext(), "Menu Deleted", Toast.LENGTH_LONG).show();

            });

            view.findViewById(R.id.offerBtn).setOnClickListener(itemView -> {
                //TO BE IMPLEMENTED
            });

        }

        public MyViewHolder linkAdapter(RecyclerAdapterMenu adapter){
            this.adapter = adapter;
            return this;
        }
    }
    @NonNull
    @Override
    public RecyclerAdapterMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items, parent, false);
        return new MyViewHolder(itemView).linkAdapter(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String mealName = menuList.get(position).getMealName();
        String mealType = menuList.get(position).getMealType();
        String cuisineType = menuList.get(position).getCuisineType();
        String mealIngredients = menuList.get(position).getIngredients();
        String mealAllergens = menuList.get(position).getAllergens();
        String mealPrice = menuList.get(position).getPrice();
        String mealDescription = menuList.get(position).getDescription();

        holder.mealNameText.setText(Html.fromHtml("<b>" + "Name: " + "</b>" + mealName));
        holder.mealTypeText.setText(Html.fromHtml("<b>" + "Meal Type: " + "</b>" + mealType));
        holder.cuisineTypeText.setText(Html.fromHtml("<b>" + "Cuisine Type: " + "</b>" + cuisineType));

        holder.ingredientsText.setText(Html.fromHtml("<b>" + "Ingredients: " + "</b>" + mealIngredients));
        holder.allergensText.setText(Html.fromHtml("<b>" + "Allergens: " + "</b>" + mealAllergens));
        holder.priceText.setText(Html.fromHtml("<b>" + "Price: " + "</b>" + mealPrice + "$"));

        holder.descriptionText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + mealDescription));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
