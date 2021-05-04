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

public class ChorobyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<SpisChorob>arrayListDisease;


    public ChorobyAdapter(Context context, ArrayList arrayListDisease){
        this.arrayListDisease = arrayListDisease;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View view_pets = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pets, parent, false);
        View view_diseases = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_diseases, parent, false);

        //ViewHolderClass viewHolderClass = new ViewHolderClass(view_pets);
        ViewHolderClass viewHolderClass1 = new ViewHolderClass(view_diseases);
        return viewHolderClass1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ViewHolderClass viewHolderClass =(ViewHolderClass)holder;
    viewHolderClass.textView.setText(DataPets.diseases[position]);
    /*viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Wybrano zwierzaka", Toast.LENGTH_SHORT).show();

        }
    }); */

    }

    @Override
    public int getItemCount() {
        return arrayListDisease.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.choroby_title);

        }
    }
}
