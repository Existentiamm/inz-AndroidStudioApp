package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListImagine;
    ArrayList arrayListName;
    ArrayList arrayListDisease;
    ArrayList arrayListTreatment;
    ArrayList arrayListMeds;

    public HelperAdapter(Context context, ArrayList arrayListImagine, ArrayList arrayListName) {
        this.context = context;
        this.arrayListImagine = arrayListImagine;
        this.arrayListName = arrayListName;
    }

    public HelperAdapter(ArrayList arrayListDisease, ArrayList arrayListTreatment, ArrayList arrayListMeds) {
        this.arrayListDisease = arrayListDisease;
        this.arrayListTreatment = arrayListTreatment;
        this.arrayListMeds = arrayListMeds;
    }

    public HelperAdapter(ArrayList arrayListTreatment){
        this.arrayListTreatment = arrayListTreatment;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view_pets = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pets, parent, false);
        View view_treatments = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_treatments, parent, false);

        ViewHolderClass viewHolderClass = new ViewHolderClass(view_pets);
        ViewHolderClass viewHolderClass1 = new ViewHolderClass(view_treatments);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ViewHolderClass viewHolderClass =(ViewHolderClass)holder;
    viewHolderClass.imageView.setImageResource(DataPets.images[position]);
    viewHolderClass.textView.setText(DataPets.names[position]);
    viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Wybrano zwierzaka", Toast.LENGTH_SHORT).show();

        }
    });

    }

    @Override
    public int getItemCount() {
        return arrayListName.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPets);
            textView = itemView.findViewById(R.id.textViewPets);

        }
    }
}
