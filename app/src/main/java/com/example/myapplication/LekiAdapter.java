package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LekiAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListMeds;


    public LekiAdapter(Context context, ArrayList arrayListMeds){
        this.arrayListMeds = arrayListMeds;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view_meds = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_meds, parent, false);
        ViewHolderClass viewHolderClass1 = new ViewHolderClass(view_meds);
        return viewHolderClass1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ViewHolderClass viewHolderClass =(ViewHolderClass)holder;
    viewHolderClass.textView.setText(DataPets.meds[position]);
    /*viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Wybrano zwierzaka", Toast.LENGTH_SHORT).show();

        }
    }); */

    }

    @Override
    public int getItemCount() {
        return arrayListMeds.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.leki_title);

        }
    }
}
