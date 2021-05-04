package com.example.myapplication.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.DodajLeki;
import com.example.myapplication.R;

import java.util.Calendar;

public class DodanieFragment extends Fragment {
    DatePickerDialog dataPickerDialog;
    Calendar calendar;
    EditText imie_edit_text, wiek_edit_text, choroby_edit_text, leki_edit_text, zabiegi_edit_text, data_edit_text;
    Button zapisz_dane, pokaz_kalendarz;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dodanie, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        imie_edit_text = getView().findViewById(R.id.imie_edit_text);
        wiek_edit_text = getView().findViewById(R.id.wiek_edit_text);
        choroby_edit_text = getView().findViewById(R.id.choroby_edit_text);
        data_edit_text = getView().findViewById(R.id.data_edit_text);
        leki_edit_text = getView().findViewById(R.id.leki_edit_text);
        zabiegi_edit_text = getView().findViewById(R.id.zabiegi_edit_text);
        pokaz_kalendarz = getView().findViewById(R.id.pokaz_kalendarz);
        zapisz_dane = getView().findViewById(R.id.zapisz_dane);

        zapisz_dane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatsHeathBookOpenHelper myDB = new CatsHeathBookOpenHelper(getActivity());
                myDB.addCat(zabiegi_edit_text.getText().toString().trim(),
                        leki_edit_text.getText().toString().trim(), choroby_edit_text.getText().toString().trim(),
                        data_edit_text.getText().toString().trim(),
                        wiek_edit_text.getText().toString().trim());
            }
        });

        pokaz_kalendarz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get (Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dataPickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        data_edit_text.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, day, month, year);
                dataPickerDialog.show();
            }
        });

    }


}
