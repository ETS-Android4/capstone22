package com.example.carolina_coffee;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtDrinkName;
    public TextView txtDrinkPrice;
    public TextView txtDrinkAddOns;
    public AppCompatButton removeButton;

    private ItemClickListener itemClickListener;

    public CartViewHolder(View itemView) {
        super(itemView);

        txtDrinkName = (TextView)itemView.findViewById(R.id.cart_item_name);
        txtDrinkPrice = (TextView)itemView.findViewById(R.id.cart_item_price);
        txtDrinkAddOns = (TextView)itemView.findViewById(R.id.cart_item_addons);
        removeButton = (AppCompatButton)itemView.findViewById(R.id.cart_remove_button);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
