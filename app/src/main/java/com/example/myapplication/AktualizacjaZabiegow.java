package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class AktualizacjaZabiegow extends AppCompatActivity {

    Button edytuj_dane_zabiegi_button, button_usun_z_listy_zabiegow;
    AutoCompleteTextView edytuj_zabiegi_edit_text, edytuj_zabiegi_imie_kota_edit_text;
    EditText edytuj_zabiegi_dodatkowe_informacje_edit_text;
    String _id, imie_kota, treatment, dodatkowe_informacje;
    String[] zabiegi_lista;
    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_zabiegow);

        edytuj_zabiegi_imie_kota_edit_text = findViewById(R.id.edytuj_zabiegi_imie_kota_edit_text);
        edytuj_zabiegi_edit_text = findViewById(R.id.edytuj_zabiegi_edit_text);
        edytuj_zabiegi_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_zabiegi_dodatkowe_informacje_edit_text);
        edytuj_dane_zabiegi_button = findViewById(R.id.edytuj_dane_zabiegi_button);
        button_usun_z_listy_zabiegow = findViewById(R.id.button_usun_z_listy_zabiegow);

        getIntentData();
        UpdateTreatment();
        deleteTreatment();

    }
    private void deleteTreatment() {
        button_usun_z_listy_zabiegow.setOnClickListener(v -> {
            confirmDialog();
        });
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usunąć " + treatment+ "?");
        builder.setMessage("Jesteś pewien, że chcesz usunąć " + treatment + "?");
        builder.setPositiveButton("Tak", (dialog, which) -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaZabiegow.this);
            myDB.deleteTreatment(_id);
        });
        builder.setNegativeButton("Nie", (dialog, which) -> {

        });
        builder.create().show();
    }

    private void getIntentData() {
        if ( getIntent().hasExtra("imie_kota_zabiegi") && getIntent().hasExtra("id_zabiegu") && getIntent().hasExtra("treatment") && getIntent().hasExtra("dodatkowe_informacje")) {
            imie_kota = getIntent().getStringExtra("imie_kota_zabiegi");
            _id = getIntent().getStringExtra("id_zabiegu");
            treatment = getIntent().getStringExtra("treatment");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");

            if ((_id != null)&& (imie_kota != null) && (treatment != null) && (dodatkowe_informacje != null)) {

                edytuj_zabiegi_imie_kota_edit_text.setText(imie_kota);
                edytuj_zabiegi_edit_text.setText(treatment);
                edytuj_zabiegi_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "Brak danych do wyswietlenia", Toast.LENGTH_SHORT).show();
        }

    }

    private void UpdateTreatment() {
        pokazImieKotaAutoComplete();
        pokazDaneAutoCompleteZabiegi();
        edytuj_dane_zabiegi_button.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaZabiegow.this);
            imie_kota = edytuj_zabiegi_imie_kota_edit_text.getText().toString().trim();
            treatment = edytuj_zabiegi_edit_text.getText().toString().trim();
            dodatkowe_informacje = edytuj_zabiegi_dodatkowe_informacje_edit_text.getText().toString().trim();
            myDB.updateTreatment(imie_kota, _id, treatment, dodatkowe_informacje);
        });


    }

    private void pokazDaneAutoCompleteZabiegi() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.treatment);
        edytuj_zabiegi_edit_text.setAdapter(adapter);
    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(AktualizacjaZabiegow.this);
        myDB.readFromDatabaseOnlyImieKota();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        zabiegi_lista = new String[0];
        zabiegi_lista = new String[cursor.getCount()];

        int i = 0;
        do {
            zabiegi_lista[i] = cursor.getString(0);
            i++;
        } while (cursor.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AktualizacjaZabiegow.this,android.R.layout.simple_dropdown_item_1line, zabiegi_lista);
        edytuj_zabiegi_imie_kota_edit_text.setAdapter(adapter);
    }
}