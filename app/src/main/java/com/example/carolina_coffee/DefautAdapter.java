package com.example.carolina_coffee;


import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class DefautAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<String> messages;

    public DefautAdapter() {
        this.messages.addAll(Arrays.asList("1","2","mesage 3"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myHoler  = (MyViewHolder) holder;
            myHoler.view.setText(this.messages.get(position));
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }


    private class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView view;

        public MyViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card,parent,false));
            view = parent.findViewById(R.id.cardText);


        }
    }
}
