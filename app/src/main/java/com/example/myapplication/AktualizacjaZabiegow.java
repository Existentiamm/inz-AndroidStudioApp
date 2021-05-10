package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class AktualizacjaZabiegow extends AppCompatActivity {

    Button edytuj_dane_zabiegi_button;
    AutoCompleteTextView edytuj_zabiegi_edit_text;
    EditText edytuj_zabiegi_dodatkowe_informacje_edit_text;
    String _id, treatment, dodatkowe_informacje;
    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_zabiegow);

        edytuj_zabiegi_edit_text = findViewById(R.id.edytuj_zabiegi_edit_text);
        edytuj_zabiegi_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_zabiegi_dodatkowe_informacje_edit_text);
        edytuj_dane_zabiegi_button = findViewById(R.id.edytuj_dane_zabiegi_button);


        UpdateTreatment();
        getIntentData();
    }

    private void getIntentData() {
        if ( getIntent().hasExtra("id_zabiegu") && getIntent().hasExtra("treatment") && getIntent().hasExtra("dodatkowe_informacje")) {
            _id = getIntent().getStringExtra("id_zabiegu");
            treatment = getIntent().getStringExtra("treatment");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");

            if ((_id != null) && (treatment != null) && (dodatkowe_informacje != null)) {
                edytuj_zabiegi_edit_text.setText(treatment);
                edytuj_zabiegi_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "Brak danych do wyswietlenia", Toast.LENGTH_SHORT).show();
        }

    }

    private void UpdateTreatment() {
        edytuj_dane_zabiegi_button.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaZabiegow.this);
            treatment = edytuj_zabiegi_edit_text.getText().toString().trim();
            dodatkowe_informacje = edytuj_zabiegi_dodatkowe_informacje_edit_text.getText().toString().trim();
            myDB.updateTreatment(_id, treatment, dodatkowe_informacje);
        });


    }
}