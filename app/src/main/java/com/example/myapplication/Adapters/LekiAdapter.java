package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AktualizacjaLekow;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LekiAdapter extends RecyclerView.Adapter<LekiAdapter.MyViewHolder> {

    private Context context;
    private ArrayList imie_kota_leki, id_leku, meds, dodatkowe_informacje;

    public LekiAdapter(Context context, ArrayList imie_kota_leki, ArrayList id_leku, ArrayList meds, ArrayList dodatkowe_informacje) {
        this.context = context;
        this.imie_kota_leki = imie_kota_leki;
        this.id_leku = id_leku;
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
        holder.imie_kota_leki.setText(String.valueOf(imie_kota_leki.get(position)));
        holder.id_leku.setText(String.valueOf(id_leku.get(position)));
        holder.leki_title.setText(String.valueOf(meds.get(position)));
        holder.leki_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
        holder.leki_static_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AktualizacjaLekow.class);
                intent.putExtra("imie_kota_leki", String.valueOf(imie_kota_leki.get(position)));
                intent.putExtra("id_leku", String.valueOf(id_leku.get(position)));
                intent.putExtra("med", String.valueOf(meds.get(position)));
                intent.putExtra("dodatkowe_informacje", String.valueOf(dodatkowe_informacje.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView imie_kota_leki, id_leku, leki_title, leki_title_dodatkowe_informacje;
        ConstraintLayout leki_static_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imie_kota_leki = itemView.findViewById(R.id.imie_kota_leki);
            id_leku = itemView.findViewById(R.id.id_leku);
            leki_title = itemView.findViewById(R.id.leki_title);
            leki_title_dodatkowe_informacje = itemView.findViewById(R.id.leki_title_dodatkowe_informacje);
            leki_static_layout = itemView.findViewById(R.id.leki_static_layout);
        }
    }
}