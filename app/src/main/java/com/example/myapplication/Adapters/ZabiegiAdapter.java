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

import com.example.myapplication.AktualizacjaZabiegow;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ZabiegiAdapter extends RecyclerView.Adapter<ZabiegiAdapter.MyViewHolder> {

    private Context context;
    private ArrayList imie_kota_zabiegi, id_zabiegu, treatments, dodatkowe_informacje;


    public ZabiegiAdapter(Context context, ArrayList imie_kota_zabiegi, ArrayList id_zabiegu, ArrayList treatments, ArrayList dodatkowe_informacje) {
        this.context = context;
        this.imie_kota_zabiegi= imie_kota_zabiegi;
        this.id_zabiegu = id_zabiegu;
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
        holder.imie_kota_zabiegi.setText(String.valueOf(imie_kota_zabiegi.get(position)));
        holder.id_zabiegu.setText(String.valueOf(id_zabiegu.get(position)));
        holder.zabiegi_title.setText(String.valueOf(treatments.get(position)));
        holder.zabiegi_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
        holder.zabiegi_static_layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, AktualizacjaZabiegow.class);
            intent.putExtra("imie_kota_zabiegi", String.valueOf(imie_kota_zabiegi.get(position)));
            intent.putExtra("id_zabiegu", String.valueOf(id_zabiegu.get(position)));
            intent.putExtra("treatment", String.valueOf(treatments.get(position)));
            intent.putExtra("dodatkowe_informacje", String.valueOf(dodatkowe_informacje.get(position)));
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return treatments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView imie_kota_zabiegi, id_zabiegu, zabiegi_title, zabiegi_title_dodatkowe_informacje;
        ConstraintLayout zabiegi_static_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imie_kota_zabiegi = itemView.findViewById(R.id.imie_kota_zabiegi);
            id_zabiegu = itemView.findViewById(R.id.id_zabiegu);
            zabiegi_title = itemView.findViewById(R.id.zabiegi_title);
            zabiegi_title_dodatkowe_informacje = itemView.findViewById(R.id.zabiegi_title_dodatkowe_informacje);
            zabiegi_static_layout = itemView.findViewById(R.id.zabiegi_static_layout);

        }
    }
}
