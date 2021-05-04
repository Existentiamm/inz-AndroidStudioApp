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

import com.example.myapplication.Adapters.LekiAdapter;
import com.example.myapplication.DataPets;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LekiFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList meds, dodatkowe_informacje;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leki, container, false);

        recyclerView = view.findViewById(R.id.recyclewViewMeds);
        meds = new ArrayList();
        dodatkowe_informacje = new ArrayList();

        for(int i = 0; i< DataPets.meds.length; i++)
        {
            meds.add(DataPets.meds);
        }
        LekiAdapter helperAdapter = new LekiAdapter(getContext(), meds);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        return view;
    }
}