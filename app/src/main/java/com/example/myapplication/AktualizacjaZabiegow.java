package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AktualizacjaZabiegow extends AppCompatActivity {

    Button edytuj_dane_zabiegi_button;
    AutoCompleteTextView edytuj_zabiegi_edit_text;
    EditText edytuj_zabiegi_dodatkowe_informacje_edit_text;
    String treatment, dodatkowe_informacje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_zabiegow);

        UpdateTreatment();
        getIntentData();
    }

    private void getIntentData() {
        if (getIntent().hasExtra("treatment") && getIntent().hasExtra("dodatkowe_informacje")) {
            treatment = getIntent().getStringExtra("treatment");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");

            if ((treatment != null) && (dodatkowe_informacje != null)) {
                edytuj_zabiegi_edit_text.setText(treatment);
                edytuj_zabiegi_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "Brak danych do wyswietlenia", Toast.LENGTH_SHORT).show();
        }

    }

    private void UpdateTreatment() {

        edytuj_zabiegi_edit_text = findViewById(R.id.edytuj_zabiegi_edit_text);
        edytuj_zabiegi_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_zabiegi_dodatkowe_informacje_edit_text);
        edytuj_dane_zabiegi_button = findViewById(R.id.edytuj_dane_zabiegi_button);

        edytuj_dane_zabiegi_button.setOnClickListener(v -> getIntentData());


    }
}