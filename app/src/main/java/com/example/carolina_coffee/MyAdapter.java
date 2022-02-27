package com.example.carolina_coffee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {




        List<MessageData> list;

    public MyAdapter(  List<MessageData> list) {

        this.list = list;
    }

    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MessageData messageData = list.get(position);
        holder.message.setText(messageData.getMessage());
        holder.subject.setText(messageData.getSubject());
        holder.body.setText(messageData.getBody());
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder{

            TextView message, subject, body;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);
            subject = itemView.findViewById(R.id.subject);
            body = itemView.findViewById(R.id.body);
        }
    }


}
