package com.example.myapplication.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.LekiAdapter;
import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.R;


import java.util.ArrayList;

public class LekiFragment extends Fragment {
    private RecyclerView recyclerView;
    private LekiAdapter lekiAdapter;
    private CatsHeathBookOpenHelper myDB;
    private ArrayList<String>  imie_kota_leki, id_leku,  nazwaLeku, dodatkoweInformacje;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leki, container, false);

        myDB = new CatsHeathBookOpenHelper(getActivity());
        imie_kota_leki = new ArrayList<>();
        id_leku = new ArrayList<>();
        nazwaLeku = new ArrayList<>();
        dodatkoweInformacje = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclewViewMeds);

        StoreDataInArrays();

        lekiAdapter = new LekiAdapter(getContext(),imie_kota_leki, id_leku, nazwaLeku, dodatkoweInformacje);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(lekiAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void StoreDataInArrays() {
        Cursor cursor = myDB.readAllDataMeds();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id_leku.add(cursor.getString(0));
                imie_kota_leki.add(cursor.getString(1));
                nazwaLeku.add(cursor.getString(2));
                dodatkoweInformacje.add(cursor.getString(3));
            }
        }
    }
}