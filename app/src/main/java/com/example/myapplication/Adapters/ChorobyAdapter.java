package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataPets;
import com.example.myapplication.R;
import com.example.myapplication.SpisChorob;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChorobyAdapter extends RecyclerView.Adapter<ChorobyAdapter.MyViewHolder> {
    private Context context;

    private ArrayList diseases, dodatkowe_informacje;

    public ChorobyAdapter(Context context, ArrayList diseases, ArrayList dodatkowe_informacje) {
        this.context = context;
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
        holder.choroby_title.setText(String.valueOf(diseases.get(position)));
        holder.choroby_title_dodatkowe_informacje.setText(String.valueOf(dodatkowe_informacje.get(position)));
    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView choroby_title, choroby_title_dodatkowe_informacje;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            choroby_title = itemView.findViewById(R.id.choroby_title);
            choroby_title_dodatkowe_informacje = itemView.findViewById(R.id.choroby_title_dodatkowe_informacje);
        }
    }
}
