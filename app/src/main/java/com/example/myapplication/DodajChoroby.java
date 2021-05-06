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

    EditText dodaj_choroby_edit_text, dodaj_choroby_dodatkowe_informacje_edit_text;
    Button zapisz_dane_choroby;
    private CatsHeathBookOpenHelper myDB;
    private AutoCompleteTextView chorobyAutoComplete;
    String[] choroby_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_choroby);

        chorobyAutoComplete = findViewById(R.id.dodaj_choroby_edit_text);
        dodaj_choroby_edit_text = (EditText) findViewById(R.id.dodaj_choroby_edit_text);
        dodaj_choroby_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_choroby_dodatkowe_informacje_edit_text);
        zapisz_dane_choroby = findViewById(R.id.zapisz_dane_choroby);


        zapiszDane();
        pokazDaneAutoComplete();


    }

    private void zapiszDane() {
        zapisz_dane_choroby.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(DodajChoroby.this);
            myDB.addDisease(dodaj_choroby_edit_text.getText().toString().trim(),
                    dodaj_choroby_dodatkowe_informacje_edit_text.getText().toString().trim());
        });
    }

    private void pokazDaneAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(DodajChoroby.this);
        myDB.readFromDatabaseOnlyNazwaChoroby();
        Cursor cursor = myDB.getCursor();
        choroby_lista = new String[0];
        choroby_lista = new String[cursor.getCount()];
        int i = 0;
        do {
            choroby_lista[i] = cursor.getString(0);
            i++;
        } while (cursor.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, choroby_lista);
        chorobyAutoComplete.setAdapter(adapter);

    }

}
