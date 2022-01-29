package com.example.carolina_coffee;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class AddinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtAddinType;
    public RecyclerView addinFlavors;

    private ItemClickListener itemClickListener;

    public AddinViewHolder(View itemView) {
        super(itemView);

        txtAddinType = (TextView)itemView.findViewById(R.id.addin_type_name);
        addinFlavors = (RecyclerView)itemView.findViewById(R.id.addin_flavor_view);

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
