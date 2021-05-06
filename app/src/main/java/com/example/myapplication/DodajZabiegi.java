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

    EditText dodaj_zabiegi_edit_text, dodaj_zabiegi_dodatkowe_informacje_edit_text;
    Button zapisz_dane_zabiegi;
    private CatsHeathBookOpenHelper myDB;
    private AutoCompleteTextView zabiegiAutoComplete;
    String[] zabiegi_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zabiegi);

        zabiegiAutoComplete = findViewById(R.id.dodaj_zabiegi_edit_text);
        dodaj_zabiegi_edit_text = findViewById(R.id.dodaj_zabiegi_edit_text);
        dodaj_zabiegi_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_zabiegi_dodatkowe_informacje_edit_text);
        zapisz_dane_zabiegi = findViewById(R.id.zapisz_dane_zabiegi);


        zapiszDane();
        pokazDaneAutoComplete();
    }

    private void pokazDaneAutoComplete() {
        try {
        myDB = new CatsHeathBookOpenHelper(DodajZabiegi.this);
        myDB.readFromDatabaseOnlyNazwaZabiegu();
        Cursor cursor = myDB.getCursor();
        zabiegi_lista = new String[0];
        zabiegi_lista = new String[cursor.getCount()];
        int i = 0;
        do {
            zabiegi_lista[i] = cursor.getString(0);
            i++;
        } while (cursor.moveToNext());}
        catch (NullPointerException e){
            System.out.print("brak rekordu przy tworzeniu listy complete text dla zabiegów");
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, zabiegi_lista);
        zabiegiAutoComplete.setAdapter(adapter);
    }

    private void zapiszDane() {
        zapisz_dane_zabiegi.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(DodajZabiegi.this);
            myDB.addTreatment(dodaj_zabiegi_edit_text.getText().toString().trim(),
                    dodaj_zabiegi_dodatkowe_informacje_edit_text.getText().toString().trim());
        });
    }
}