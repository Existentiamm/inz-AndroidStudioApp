package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AktualizacjaLekow extends AppCompatActivity {
    AutoCompleteTextView edytuj_leki_edit_text;
    EditText edytuj_leki_dodatkowe_informacje_edit_text;
    Button edytuj_dane_leki_button;
    String meds, dodatkowe_informacje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_lekow);

        UpdateMed();
        getIntentData();
    }

    private void UpdateMed() {
        edytuj_leki_edit_text = findViewById(R.id.edytuj_leki_edit_text);
        edytuj_leki_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_leki_dodatkowe_informacje_edit_text);
        edytuj_dane_leki_button = findViewById(R.id.edytuj_dane_leki_button);

        edytuj_dane_leki_button.setOnClickListener(v -> getIntentData());
    }

    private void getIntentData() {
        if (getIntent().hasExtra("med") && (getIntent().hasExtra("dodatkowe_informacje"))) {
            meds = getIntent().getStringExtra("med");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");

            if ((meds != null) && dodatkowe_informacje != null) {
                edytuj_leki_edit_text.setText(meds);
                edytuj_leki_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);

            }
            ;

        } else {
            Toast.makeText(this, "Brak danych do wyświetlenia", Toast.LENGTH_SHORT).show();

        }

    }

}