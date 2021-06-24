package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class DodajLeki extends AppCompatActivity {
    private TextView dodaj_leki_dodatkowe_informacje_edit_text;
    private Button zapisz_dane_leki;
    private AutoCompleteTextView dodaj_leki_edit_text , dodaj_leki_imie_kota_edit_text;
    private CatsHeathBookOpenHelper myDB;
    private String[] leki_lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_leki);

        dodaj_leki_imie_kota_edit_text = findViewById(R.id.dodaj_leki_imie_kota_edit_text);
        dodaj_leki_edit_text = findViewById(R.id.dodaj_leki_edit_text);
        dodaj_leki_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_leki_dodatkowe_informacje_edit_text);
        zapisz_dane_leki = findViewById(R.id.zapisz_dane_leki);

        pokazImieKotaAutoComplete();
        pokazDaneAutoCompleteLeki();
        zapiszDane();


    }
    private void pokazDaneAutoCompleteLeki() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.meds);
        dodaj_leki_edit_text.setAdapter(adapter);

    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(DodajLeki.this);
        myDB.readFromDatabaseOnlyOneCat();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        leki_lista = new String[0];
        leki_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() >0 ) {
            do {
                leki_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DodajLeki.this,android.R.layout.simple_dropdown_item_1line, leki_lista);
        dodaj_leki_imie_kota_edit_text.setAdapter(adapter);
    }

    public void zapiszDane() {
        zapisz_dane_leki.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(DodajLeki.this);
            myDB.addMedicament(dodaj_leki_imie_kota_edit_text.getText().toString().trim(), dodaj_leki_edit_text.getText().toString().trim(),
                    dodaj_leki_dodatkowe_informacje_edit_text.getText().toString().trim());
        });

    }
}