package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetsFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList images, names;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pets, container, false);
        recyclerView = view.findViewById(R.id.recyclewViewPets);
        images = new ArrayList();
        names = new ArrayList();

        for(int i =0; i<DataPets.names.length;i++)
        {
            images.add(DataPets.images);
            names.add(DataPets.names);
        }
        HelperAdapter helperAdapter = new HelperAdapter(getContext(), images, names);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        return view;
    }
}
