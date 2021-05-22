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

import com.example.myapplication.AktualizacjaDat;
import com.example.myapplication.AktualizacjaLekow;
import com.example.myapplication.R;

import java.util.ArrayList;

public class KalendarzAdapter extends RecyclerView.Adapter<KalendarzAdapter.MyViewHolder> {


    private Context context;
    private ArrayList imie_kota_kalendarz, id_daty, daty, dodatkowe_informacje;

    public KalendarzAdapter(Context context, ArrayList imie_kota_kalendarz, ArrayList id_daty, ArrayList daty, ArrayList dodatkowe_informacje) {
        this.context = context;
        this.imie_kota_kalendarz = imie_kota_kalendarz;
        this.id_daty = id_daty;
        this.daty = daty;
        this.dodatkowe_informacje = dodatkowe_informacje;
    }


    @NonNull
    @Override
    public KalendarzAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_kalendarz, parent, false);
        return new KalendarzAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KalendarzAdapter.MyViewHolder holder, int position) {
        holder.imie_kota_kalendarz.setText(String.valueOf(imie_kota_kalendarz.get(position)));
        holder.id_daty.setText(String.valueOf(id_daty.get(position)));
        holder.data_title.setText(String.valueOf(daty.get(position)));
        holder.data_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
        holder.kalendarz_static_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AktualizacjaDat.class);
                intent.putExtra("imie_kota_kalendarz", String.valueOf(imie_kota_kalendarz.get(position)));
                intent.putExtra("id_daty", String.valueOf(id_daty.get(position)));
                intent.putExtra("daty", String.valueOf(daty.get(position)));
                intent.putExtra("dodatkowe_informacje", String.valueOf(dodatkowe_informacje.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daty.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView imie_kota_kalendarz, id_daty, data_title, data_title_dodatkowe_informacje;
        ConstraintLayout kalendarz_static_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imie_kota_kalendarz = itemView.findViewById(R.id.imie_kota_kalendarz);
            id_daty = itemView.findViewById(R.id.id_daty);
            data_title = itemView.findViewById(R.id.data_title);
            data_title_dodatkowe_informacje = itemView.findViewById(R.id.data_title_dodatkowe_informacje);
            kalendarz_static_layout = itemView.findViewById(R.id.kalendarz_static_layout);
        }
    }
}
