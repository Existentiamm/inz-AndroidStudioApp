package com.example.myapplication.Fragments;

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

import com.example.myapplication.Adapters.ZabiegiAdapter;
import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ZabiegiFragment extends Fragment {
    RecyclerView recyclerView;
    ZabiegiAdapter zabiegiAdapter;
    CatsHeathBookOpenHelper myDB;
    ArrayList<String> nazwaZabiegu, dodatkoweInformacje;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zabiegi, container, false);

        myDB = new CatsHeathBookOpenHelper(getActivity());
        nazwaZabiegu = new ArrayList<>();
        dodatkoweInformacje = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclewViewTreatments);

        StoreDataInArrays();

        zabiegiAdapter = new ZabiegiAdapter(getContext(), nazwaZabiegu, dodatkoweInformacje);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(zabiegiAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
        private void StoreDataInArrays() {
            Cursor cursor = myDB.readAllDataTreatments();
            if (cursor.getCount() == 0) {
                Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    nazwaZabiegu.add(cursor.getString(0));
                    dodatkoweInformacje.add(cursor.getString(1));
                }
            }
    }
}
