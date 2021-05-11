package com.example.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AktualizacjaChorob;
import com.example.myapplication.DodajChoroby;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChorobyAdapter extends RecyclerView.Adapter<ChorobyAdapter.MyViewHolder> {
    private Context context;
    private Activity activity; //do odświeżania Activity
    private ArrayList imie_kota_choroby, id_choroby, diseases, dodatkowe_informacje;

    public ChorobyAdapter(Activity activity, Context context, ArrayList id_choroby, ArrayList diseases, ArrayList dodatkowe_informacje) {
        this.activity = activity;
        this.context = context;
        this.id_choroby = id_choroby;
        this.diseases = diseases;
        this.dodatkowe_informacje = dodatkowe_informacje;
    }

    public ChorobyAdapter(Context context, ArrayList imie_kota_choroby, ArrayList id_choroby, ArrayList diseases, ArrayList dodatkowe_informacje) {
        this.imie_kota_choroby = imie_kota_choroby;
        this.context = context;
        this.id_choroby = id_choroby;
        this.diseases = diseases;
        this.dodatkowe_informacje = dodatkowe_informacje;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_diseases, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imie_kota_choroby.setText(String.valueOf(imie_kota_choroby.get(position)));
        holder.id_choroby.setText(String.valueOf(id_choroby.get(position)));
        holder.choroby_title.setText(String.valueOf(diseases.get(position)));
        holder.choroby_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
        holder.choroby_static_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AktualizacjaChorob.class);
                intent.putExtra("imie_kota_choroby", String.valueOf(imie_kota_choroby.get(position)));
                intent.putExtra("id_choroby", String.valueOf(id_choroby.get(position)));
                intent.putExtra("diseases", String.valueOf(diseases.get(position)));
                intent.putExtra("dodatkowe_informacje", String.valueOf(dodatkowe_informacje.get(position)));
               context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout choroby_static_layout;
        TextView imie_kota_choroby, id_choroby, choroby_title, choroby_title_dodatkowe_informacje;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imie_kota_choroby = itemView.findViewById(R.id.imie_kota_choroby);
            id_choroby= itemView.findViewById(R.id.id_choroby);
            choroby_title = itemView.findViewById(R.id.choroby_title);
            choroby_title_dodatkowe_informacje = itemView.findViewById(R.id.choroby_title_dodatkowe_informacje);
            choroby_static_layout = itemView.findViewById(R.id.choroby_static_layout);
        }
    }
}
