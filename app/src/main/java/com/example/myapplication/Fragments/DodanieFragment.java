package com.example.myapplication.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.R;

import java.util.Calendar;
import java.util.Date;

public class DodanieFragment extends Fragment {
    private DatePickerDialog dataPickerDialog;
    private Calendar calendar;
    private EditText imie_edit_text, wiek_edit_text, choroby_edit_text, leki_edit_text, zabiegi_edit_text, data_edit_text;
    private Button zapisz_dane, pokaz_kalendarz;
    private String[] leki_lista, choroby_lista, zabiegi_lista, koty_lista;
    private CatsHeathBookOpenHelper myDB;
    private AutoCompleteTextView lekiAutoComplete, chorobyAutoComplete, zabiegiAutoComplete, kotyAutoComplete;
    int checkingDate;
    int actualYear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dodanie, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        kotyAutoComplete = getView().findViewById(R.id.imie_edit_text);
        imie_edit_text = getView().findViewById(R.id.imie_edit_text);
        wiek_edit_text = getView().findViewById(R.id.wiek_edit_text);
        zapisz_dane = getView().findViewById(R.id.zapisz_dane);

        calendar = Calendar.getInstance();
        actualYear = calendar.get(Calendar.YEAR);
        ZapiszDane();
        pokazDaneAutoComplete();

    }

    private void pokazDaneAutoComplete() {
        //pokazZabiegiAutoComplete();
        //pokazLekiAutoComplete();
        //pokazChorobyAutoComplete();
        pokazImieKotaAutoComplete();
    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(getActivity());
        myDB.readFromDatabaseOnlyImieKota();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        koty_lista = new String[0];
        koty_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() > 0) {
            do {
                koty_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, koty_lista);
        kotyAutoComplete.setAdapter(adapter);
    }

    private void pokazZabiegiAutoComplete() {

        myDB = new CatsHeathBookOpenHelper(getActivity());
        myDB.readFromDatabaseOnlyNazwaZabiegu();
        Cursor cursor = myDB.getCursor();
        zabiegi_lista = new String[0];
        zabiegi_lista = new String[cursor.getCount()];//create array string based on numbers of row
        int i = 0;
        do {
            zabiegi_lista[i] = cursor.getString(0);
            i++;
        } while (cursor.moveToNext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, zabiegi_lista);
        zabiegiAutoComplete.setAdapter(adapter);
    }


    private void pokazLekiAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(getActivity());
        myDB.readFromDatabaseOnlyNazwaLeku();
        Cursor cursor = myDB.getCursor();
        leki_lista = new String[0];
        leki_lista = new String[cursor.getCount()];
        int i = 0;
        do {
            leki_lista[i] = cursor.getString(0);
            i++;
        } while (cursor.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, leki_lista);
        lekiAutoComplete.setAdapter(adapter);

    }

    private void pokazChorobyAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(getActivity());
        myDB.readFromDatabaseOnlyNazwaChoroby();
        Cursor cursor = myDB.getCursor();
        choroby_lista = new String[0];
        choroby_lista = new String[cursor.getCount()];

        int i = 0;
        do {
            choroby_lista[i] = cursor.getString(0);
            i++;
        }
        while (cursor.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, choroby_lista);
        chorobyAutoComplete.setAdapter(adapter);

    }


    private void ZapiszDane() {

        zapisz_dane.setOnClickListener(v -> {
            checkingDate = Integer.parseInt(wiek_edit_text.getText().toString());
            if(actualYear < checkingDate){
                Toast.makeText(getActivity(), "Niepoprawny rok", Toast.LENGTH_SHORT).show();
            }
            else {
                myDB = new CatsHeathBookOpenHelper(getActivity());

                myDB.addCat(imie_edit_text.getText().toString().trim(),
                        wiek_edit_text.getText().toString().trim());
            }
        });
    }


}
