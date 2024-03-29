package com.example.myapplication.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ChorobyAdapter;
import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChorobyFragment extends Fragment {
    RecyclerView recyclerView;
    ChorobyAdapter chorobyAdapter;
    CatsHeathBookOpenHelper myDB;
    ArrayList<String> imie_kota_choroby, id_choroby, nazwaChoroby, dodatkoweInformacje;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_choroby, container, false);

        myDB = new CatsHeathBookOpenHelper(getActivity());
        imie_kota_choroby = new ArrayList<>();
        id_choroby = new ArrayList<>();
        nazwaChoroby = new ArrayList<>();
        dodatkoweInformacje = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclewViewDiseases);

        StoreDataInArrays();
        chorobyAdapter = new ChorobyAdapter(getContext(), imie_kota_choroby, id_choroby, nazwaChoroby, dodatkoweInformacje);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chorobyAdapter);

        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void StoreDataInArrays() {
        Cursor cursor = myDB.readAllDataDiseases();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                imie_kota_choroby.add(cursor.getString(1));
                id_choroby.add(cursor.getString(0));
                nazwaChoroby.add(cursor.getString(2));
                dodatkoweInformacje.add(cursor.getString(3));
            }
        }
    }
}
