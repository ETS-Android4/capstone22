package com.example.carolina_coffee;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView drinkName;
    public TextView drinkPrice;
    public RecyclerView addins;
    private ItemClickListener itemClickListener;

    public OrderHistoryViewHolder(View itemView) {
        super(itemView);

        addins = (RecyclerView)itemView.findViewById(R.id.order_item_recycler);
        drinkName = (TextView)itemView.findViewById(R.id.order_item_name);
        drinkPrice = (TextView)itemView.findViewById(R.id.order_item_price);

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
