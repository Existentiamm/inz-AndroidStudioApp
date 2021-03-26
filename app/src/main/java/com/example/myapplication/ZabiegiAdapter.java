package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZabiegiAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListTreatments;


    public ZabiegiAdapter(Context context, ArrayList arrayListTreatments){
        this.arrayListTreatments = arrayListTreatments;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view_treatment = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_treatments, parent, false);
        ViewHolderClass viewHolderClass1 = new ViewHolderClass(view_treatment);
        return viewHolderClass1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ViewHolderClass viewHolderClass =(ViewHolderClass)holder;
    viewHolderClass.textView.setText(DataPets.treatment[position]);
    /*viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Wybrano zwierzaka", Toast.LENGTH_SHORT).show();

        }
    }); */

    }

    @Override
    public int getItemCount() {
        return arrayListTreatments.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.zabiegi_title);

        }
    }
}
