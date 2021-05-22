package com.example.myapplication.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Adapters.KalendarzAdapter;
import com.example.myapplication.Adapters.LekiAdapter;
import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ZapisaneDatyFragment extends Fragment {
    private RecyclerView recyclerView;
    private KalendarzAdapter kalendarzAdapter;
    private CatsHeathBookOpenHelper myDB;
    private ArrayList<String> imie_kota_kalendarz, id_daty,  daty, dodatkoweInformacje;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_zapisane_daty_fragment, container, false);

        myDB = new CatsHeathBookOpenHelper(getActivity());
        imie_kota_kalendarz = new ArrayList<>();
        id_daty = new ArrayList<>();
        daty = new ArrayList<>();
        dodatkoweInformacje = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclewViewZapisaneDaty);

        StoreDataInArrays();
        kalendarzAdapter = new KalendarzAdapter(getContext(),imie_kota_kalendarz, id_daty, daty, dodatkoweInformacje);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(kalendarzAdapter);

        return view;
    }

    private void StoreDataInArrays() {
        Cursor cursor = myDB.readAllDataCalendar();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id_daty.add(cursor.getString(0));
                imie_kota_kalendarz.add(cursor.getString(1));
                daty.add(cursor.getString(2));
                dodatkoweInformacje.add(cursor.getString(3));
            }
        }
    }
}