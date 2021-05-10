package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class AktualizacjaLekow extends AppCompatActivity {
    AutoCompleteTextView edytuj_leki_edit_text;
    EditText edytuj_leki_dodatkowe_informacje_edit_text;
    Button edytuj_dane_leki_button;
    String _id, meds, dodatkowe_informacje;
    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_lekow);

        edytuj_leki_edit_text = findViewById(R.id.edytuj_leki_edit_text);
        edytuj_leki_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_leki_dodatkowe_informacje_edit_text);
        edytuj_dane_leki_button = findViewById(R.id.edytuj_dane_leki_button);


        getIntentData();
        UpdateMed();

    }

    private void UpdateMed() {

       edytuj_dane_leki_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myDB = new CatsHeathBookOpenHelper(AktualizacjaLekow.this);
               meds = edytuj_leki_edit_text.getText().toString().trim();
               dodatkowe_informacje = edytuj_leki_dodatkowe_informacje_edit_text.getText().toString().trim();
               myDB.updateMed(_id, meds, dodatkowe_informacje);
           }
       });
    }

    private void getIntentData() {
        if (getIntent().hasExtra("id_leku") && getIntent().hasExtra("med") && (getIntent().hasExtra("dodatkowe_informacje"))) {
            _id = getIntent().getStringExtra("id_leku");
            meds = getIntent().getStringExtra("med");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");

            if ((_id != null) && ((meds != null) && dodatkowe_informacje != null)) {
                edytuj_leki_edit_text.setText(meds);
                edytuj_leki_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);

            }

        } else {
            Toast.makeText(this, "Brak danych do wyświetlenia", Toast.LENGTH_SHORT).show();

        }

    }

}