package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class DodajChoroby extends AppCompatActivity {

    EditText dodaj_choroby_edit_text, dodaj_choroby_dodatkowe_informacje_edit_text;
    Button zapisz_dane_choroby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_choroby);

        dodaj_choroby_edit_text = (EditText) findViewById(R.id.dodaj_choroby_edit_text);
        dodaj_choroby_dodatkowe_informacje_edit_text = findViewById(R.id.dodaj_choroby_dodatkowe_informacje_edit_text);
        zapisz_dane_choroby = findViewById(R.id.zapisz_dane_choroby);

        zapisz_dane_choroby.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CatsHeathBookOpenHelper myDB = new CatsHeathBookOpenHelper(DodajChoroby.this);
                myDB.addDisease(dodaj_choroby_edit_text.getText().toString().trim(),
                        dodaj_choroby_dodatkowe_informacje_edit_text.getText().toString().trim());
            }
        });
    }

}
