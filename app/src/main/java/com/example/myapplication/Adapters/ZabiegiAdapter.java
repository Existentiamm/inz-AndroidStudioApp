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

public class ZabiegiAdapter extends RecyclerView.Adapter<ZabiegiAdapter.MyViewHolder> {

    private Context context;
    private ArrayList treatments, dodatkowe_informacje;


    public ZabiegiAdapter(Context context, ArrayList treatments, ArrayList dodatkowe_informacje) {
        this.context = context;
        this.treatments = treatments;
        this.dodatkowe_informacje = dodatkowe_informacje;
    }

    @NonNull
    @Override
    public ZabiegiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_treatments, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZabiegiAdapter.MyViewHolder holder, int position) {
        holder.zabiegi_title.setText(String.valueOf(treatments.get(position)));
        holder.zabiegi_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
    }



    @Override
    public int getItemCount() {
        return treatments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView zabiegi_title, zabiegi_title_dodatkowe_informacje;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            zabiegi_title = itemView.findViewById(R.id.zabiegi_title);
            zabiegi_title_dodatkowe_informacje = itemView.findViewById(R.id.zabiegi_title_dodatkowe_informacje);
        }
    }
}
