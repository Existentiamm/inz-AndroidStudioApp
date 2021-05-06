package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class LekiAdapter extends RecyclerView.Adapter<LekiAdapter.MyViewHolder> {

    private Context context;
    private ArrayList meds, dodatkowe_informacje;

    public LekiAdapter(Context context, ArrayList meds, ArrayList dodatkowe_informacje) {
        this.context = context;
        this.meds = meds;
        this.dodatkowe_informacje = dodatkowe_informacje;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_meds, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.leki_title.setText(String.valueOf(meds.get(position)));
        holder.leki_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView leki_title, leki_title_dodatkowe_informacje;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            leki_title = itemView.findViewById(R.id.leki_title);
            leki_title_dodatkowe_informacje = itemView.findViewById(R.id.leki_title_dodatkowe_informacje);
        }
    }
}