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

import com.example.myapplication.Adapters.ChorobyAdapter;
import com.example.myapplication.DataPets;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChorobyFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList diseases, dodatkowe_informacje;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_choroby, container, false);

        recyclerView = view.findViewById(R.id.recyclewViewDiseases);
        diseases = new ArrayList();
        dodatkowe_informacje = new ArrayList();

        for(int i = 0; i< DataPets.diseases.length; i++)
        {
            diseases.add(DataPets.diseases);
        }
        ChorobyAdapter helperAdapter = new ChorobyAdapter(getContext(), diseases);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        return view;
    }
}
