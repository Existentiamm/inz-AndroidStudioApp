package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class DodajZabiegi extends AppCompatActivity {

    EditText dodaj_zabiegi_dodatkowe_informacje_edit_text;
    Button zapisz_dane_zabiegi;
    private CatsHeathBookOpenHelper myDB;
    private AutoCompleteTextView dodaj_zabiegi_edit_text, dodaj_zabiegi_imie_kota_edit_text;
    String[] zabiegi_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zabiegi);

        dodaj_zabiegi_edit_text = findViewById(R.id.dodaj_zabiegi_edit_text);
        dodaj_zabiegi_imie_kota_edit_text = findViewById(R.id.dodaj_zabiegi_imie_kota_edit_text);
        dodaj_zabiegi_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_zabiegi_dodatkowe_informacje_edit_text);
        zapisz_dane_zabiegi = findViewById(R.id.zapisz_dane_zabiegi);

        pokazImieKotaAutoComplete();
        pokazDaneAutoCompleteZabiegi();
        zapiszDane();

    }


    private void pokazDaneAutoCompleteZabiegi() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.treatment);
        dodaj_zabiegi_edit_text.setAdapter(adapter);

    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(DodajZabiegi.this);
        myDB.readFromDatabaseOnlyImieKota();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        zabiegi_lista = new String[0];
        zabiegi_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() >0 ) {
            do {
                zabiegi_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DodajZabiegi.this,android.R.layout.simple_dropdown_item_1line, zabiegi_lista);
        dodaj_zabiegi_imie_kota_edit_text.setAdapter(adapter);
    }

    private void zapiszDane() {
        zapisz_dane_zabiegi.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(DodajZabiegi.this);
            myDB.addTreatment(dodaj_zabiegi_imie_kota_edit_text.getText().toString().trim(),
                    dodaj_zabiegi_edit_text.getText().toString().trim(),
                    dodaj_zabiegi_dodatkowe_informacje_edit_text.getText().toString().trim());
        });
    }
}