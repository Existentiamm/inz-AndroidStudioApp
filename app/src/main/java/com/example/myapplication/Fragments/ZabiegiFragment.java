package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ZabiegiAdapter;
import com.example.myapplication.DataPets;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ZabiegiFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList treatments, dodatkowe_informacje;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zabiegi, container, false);

        recyclerView = view.findViewById(R.id.recyclewViewTreatments);
        treatments = new ArrayList();
        dodatkowe_informacje = new ArrayList();

        for(int i = 0; i< DataPets.treatment.length; i++)
        {
            treatments.add(DataPets.treatment);
        }
        ZabiegiAdapter helperAdapter = new ZabiegiAdapter(getContext(), treatments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        return view;
    }
}
