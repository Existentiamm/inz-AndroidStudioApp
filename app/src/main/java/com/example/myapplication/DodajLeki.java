package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class DodajLeki extends AppCompatActivity {
    EditText dodaj_leki_edit_text, dodaj_leki_dodatkowe_informacje_edit_text;
    Button zapisz_dane_leki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_leki);

        dodaj_leki_edit_text = (EditText) findViewById(R.id.dodaj_leki_edit_text);
        dodaj_leki_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_leki_dodatkowe_informacje_edit_text);
        zapisz_dane_leki = findViewById(R.id.zapisz_dane_leki);

        zapisz_dane_leki.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CatsHeathBookOpenHelper myDB = new CatsHeathBookOpenHelper(DodajLeki.this);
                myDB.addMed(dodaj_leki_edit_text.getText().toString().trim(),
                        dodaj_leki_dodatkowe_informacje_edit_text.getText().toString().trim());
            }
        });
    }


}