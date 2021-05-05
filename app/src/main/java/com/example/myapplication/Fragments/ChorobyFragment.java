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

import com.example.myapplication.Adapters.ChorobyAdapter;
import com.example.myapplication.Adapters.LekiAdapter;
import com.example.myapplication.DataPets;
import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChorobyFragment extends Fragment {
    RecyclerView recyclerView;
    ChorobyAdapter chorobyAdapter;
    CatsHeathBookOpenHelper myDB;
    ArrayList<String> nazwaChoroby, dodatkoweInformacje;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_choroby, container, false);

        myDB = new CatsHeathBookOpenHelper(getActivity());
        nazwaChoroby = new ArrayList<>();
        dodatkoweInformacje = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclewViewDiseases);

        StoreDataInArrays();
        chorobyAdapter = new ChorobyAdapter(getContext(), nazwaChoroby, dodatkoweInformacje);
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
                nazwaChoroby.add(cursor.getString(0));
                dodatkoweInformacje.add(cursor.getString(1));
            }
        }
    }
}
