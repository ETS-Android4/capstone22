package com.example.carolina_coffee;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CartAddinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cart_addin_txt;

    private ItemClickListener itemClickListener;

    public CartAddinViewHolder(View itemView) {
        super(itemView);

        cart_addin_txt = (TextView)itemView.findViewById(R.id.cart_addin_text);

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
