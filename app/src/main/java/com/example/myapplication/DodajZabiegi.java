package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class DodajZabiegi extends AppCompatActivity {

    EditText dodaj_zabiegi_edit_text, dodaj_zabiegi_dodatkowe_informacje_edit_text;
    Button zapisz_dane_zabiegi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zabiegi);

        dodaj_zabiegi_edit_text = findViewById(R.id.dodaj_zabiegi_edit_text);
        dodaj_zabiegi_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_zabiegi_dodatkowe_informacje_edit_text);
        zapisz_dane_zabiegi = findViewById(R.id.zapisz_dane_zabiegi);

        zapisz_dane_zabiegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatsHeathBookOpenHelper myDB = new CatsHeathBookOpenHelper(DodajZabiegi.this);
                myDB.addTreatment(dodaj_zabiegi_edit_text.getText().toString().trim(),
                        dodaj_zabiegi_dodatkowe_informacje_edit_text.getText().toString().trim());
            }
        });
    }
}