package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class DodajChoroby extends AppCompatActivity {

    EditText dodaj_choroby_dodatkowe_informacje_edit_text;
    Button zapisz_dane_choroby;
    private CatsHeathBookOpenHelper myDB;
    private AutoCompleteTextView dodaj_choroby_imie_kota_edit_text, dodaj_choroby_edit_text;
    String[] choroby_lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_choroby);

        dodaj_choroby_imie_kota_edit_text = findViewById(R.id.dodaj_choroby_imie_kota_edit_text);
        dodaj_choroby_edit_text = findViewById(R.id.dodaj_choroby_edit_text);
        dodaj_choroby_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_choroby_dodatkowe_informacje_edit_text);
        zapisz_dane_choroby = findViewById(R.id.zapisz_dane_choroby);


        pokazImieKotaAutoComplete();
        pokazDaneAutoCompleteChoroby();
        zapiszDane();



    }

    private void pokazDaneAutoCompleteChoroby() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.diseases);
        dodaj_choroby_edit_text.setAdapter(adapter);

    }

    private void zapiszDane() {
        zapisz_dane_choroby.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(DodajChoroby.this);
            myDB.addDisease(dodaj_choroby_imie_kota_edit_text.getText().toString().trim(), dodaj_choroby_edit_text.getText().toString().trim(),
                    dodaj_choroby_dodatkowe_informacje_edit_text.getText().toString().trim());
        });
    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(DodajChoroby.this);
        myDB.readFromDatabaseOnlyImieKota();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        choroby_lista = new String[0];
        choroby_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() >0 ) {
            do {
                choroby_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DodajChoroby.this,android.R.layout.simple_dropdown_item_1line, choroby_lista);
        dodaj_choroby_imie_kota_edit_text.setAdapter(adapter);
    }

}
