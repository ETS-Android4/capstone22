package com.example.carolina_coffee;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtDrinkName;
    public TextView txtDrinkPrice;
    public TextView txtDrinkAddOns;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public CartViewHolder(View itemView) {
        super(itemView);

        txtDrinkName = (TextView)itemView.findViewById(R.id.cart_item_name);
        txtDrinkPrice = (TextView)itemView.findViewById(R.id.cart_item_price);
        txtDrinkAddOns = (TextView)itemView.findViewById(R.id.cart_item_addons);
        imageView = (ImageView)itemView.findViewById(R.id.cart_item_image);

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
