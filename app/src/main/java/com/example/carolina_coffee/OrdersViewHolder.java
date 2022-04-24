package com.example.carolina_coffee;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView orderName;
    public RecyclerView cartRecycler;
    public TextView orderPrice;
    public TextView paymentUsed;

    private ItemClickListener itemClickListener;

    public OrdersViewHolder(View itemView) {
        super(itemView);

        orderName = (TextView)itemView.findViewById(R.id.order_history_number);
        cartRecycler = (RecyclerView)itemView.findViewById(R.id.order_history_recycler);
        orderPrice = (TextView)itemView.findViewById(R.id.order_price);
        paymentUsed = (TextView)itemView.findViewById(R.id.order_payment_used);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        //itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}