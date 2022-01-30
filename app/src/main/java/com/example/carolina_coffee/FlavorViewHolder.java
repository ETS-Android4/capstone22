package com.example.carolina_coffee;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FlavorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CheckBox flavorCheckbox;

    private ItemClickListener itemClickListener;

    public FlavorViewHolder(View itemView) {
        super(itemView);

        flavorCheckbox = (CheckBox)itemView.findViewById(R.id.addin_checkbox);

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
