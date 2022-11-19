package com.uottawa.seg2105_group20_project;

import android.annotation.SuppressLint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerAdapterOfferedMenu extends RecyclerView.Adapter<RecyclerAdapterOfferedMenu.MyViewHolder> {

    private List<Meal> menuList;
    private String cookID;

    public RecyclerAdapterOfferedMenu(List<Meal> menuList, String cookID){
        this.cookID = cookID;
        this.menuList = menuList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView mealNameOText;
        private final TextView mealTypeOText;
        private final TextView cuisineTypeOText;
        private final TextView ingredientsOText;
        private final TextView allergensOText;
        private final TextView priceOText;
        private final TextView descriptionOText;
        private RecyclerAdapterOfferedMenu adapter;

        DatabaseReference dbMenu;

        public MyViewHolder(final View view){
            super(view);
            mealNameOText = view.findViewById(R.id.mealNameOText);
            mealTypeOText = view.findViewById(R.id.mealTypeOText);
            cuisineTypeOText = view.findViewById(R.id.cuisineTypeOText);
            ingredientsOText = view.findViewById(R.id.ingredientsOText);
            allergensOText = view.findViewById(R.id.allergensOText);
            priceOText = view.findViewById(R.id.priceOText);
            descriptionOText = view.findViewById(R.id.descriptionOText);

            dbMenu = FirebaseDatabase.getInstance().getReference("meals").child(cookID);

            view.findViewById(R.id.unofferBtn).setOnClickListener(itemView -> {
                int adapterPosition = getAdapterPosition();
                dbMenu.child(menuList.get(adapterPosition).getMealID()).child("offered").setValue(false);
                adapter.menuList.remove(adapterPosition);
                adapter.notifyItemChanged(adapterPosition);
                Toast.makeText(itemView.getContext(), "Meal Unoffered", Toast.LENGTH_LONG).show();

            });

        }

        public MyViewHolder linkAdapter(RecyclerAdapterOfferedMenu adapter){
            this.adapter = adapter;
            return this;
        }
    }
    @NonNull
    @Override
    public RecyclerAdapterOfferedMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.offered_menu_items, parent, false);
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

        holder.mealNameOText.setText(Html.fromHtml("<b>" + "Name: " + "</b>" + mealName));
        holder.mealTypeOText.setText(Html.fromHtml("<b>" + "Meal Type: " + "</b>" + mealType));
        holder.cuisineTypeOText.setText(Html.fromHtml("<b>" + "Cuisine Type: " + "</b>" + cuisineType));

        holder.ingredientsOText.setText(Html.fromHtml("<b>" + "Ingredients: " + "</b>" + mealIngredients));
        holder.allergensOText.setText(Html.fromHtml("<b>" + "Allergens: " + "</b>" + mealAllergens));
        holder.priceOText.setText(Html.fromHtml("<b>" + "Price: " + "</b>" + mealPrice + "$"));

        holder.descriptionOText.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + mealDescription));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}