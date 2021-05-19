package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class AktualizacjaLekow extends AppCompatActivity {
    AutoCompleteTextView edytuj_leki_edit_text, edytuj_leki_imie_kota_edit_text;
    EditText edytuj_leki_dodatkowe_informacje_edit_text;
    Button edytuj_dane_leki_button, button_usun_z_listy_lekow;
    String _id, imie_kota,  meds, dodatkowe_informacje;
    CatsHeathBookOpenHelper myDB;
    String[] leki_lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_lekow);

        edytuj_leki_imie_kota_edit_text = findViewById(R.id.edytuj_leki_imie_kota_edit_text);
        edytuj_leki_edit_text = findViewById(R.id.edytuj_leki_edit_text);
        edytuj_leki_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_leki_dodatkowe_informacje_edit_text);
        edytuj_dane_leki_button = findViewById(R.id.edytuj_dane_leki_button);
        button_usun_z_listy_lekow  = findViewById(R.id.button_usun_z_listy_lekow);

        getIntentData();
        deleteMed();
        UpdateMed();

    }


    private void deleteMed() {
        button_usun_z_listy_lekow.setOnClickListener(v->{
            confirmDialog();
        });
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usunąć " + meds +"? ");
        builder.setMessage("Na pewno usunąć " + meds +"? ");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDB = new CatsHeathBookOpenHelper(AktualizacjaLekow.this);
                myDB.deleteMed(_id);
            }
        });

        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    private void UpdateMed() {
        pokazImieKotaAutoComplete();
        pokazDaneAutoCompleteLeki();

       edytuj_dane_leki_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myDB = new CatsHeathBookOpenHelper(AktualizacjaLekow.this);
               imie_kota = edytuj_leki_imie_kota_edit_text.getText().toString().trim();
               meds = edytuj_leki_edit_text.getText().toString().trim();
               dodatkowe_informacje = edytuj_leki_dodatkowe_informacje_edit_text.getText().toString().trim();
               myDB.updateMed(imie_kota, _id, meds, dodatkowe_informacje);
           }
       });
    }

    private void pokazDaneAutoCompleteLeki() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.meds);
        edytuj_leki_edit_text.setAdapter(adapter);
    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(AktualizacjaLekow.this);
        myDB.readFromDatabaseOnlyImieKota();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        leki_lista = new String[0];
        leki_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() >0 ) {
            do {
                leki_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AktualizacjaLekow.this,android.R.layout.simple_dropdown_item_1line, leki_lista);
        edytuj_leki_imie_kota_edit_text.setAdapter(adapter);
    }

    private void getIntentData() {
        if (getIntent().hasExtra("imie_kota_leki") && getIntent().hasExtra("id_leku") && getIntent().hasExtra("med") && (getIntent().hasExtra("dodatkowe_informacje"))) {
            imie_kota = getIntent().getStringExtra("imie_kota_leki");
            _id = getIntent().getStringExtra("id_leku");
            meds = getIntent().getStringExtra("med");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");

            if ((_id != null) && (imie_kota != null)  && ((meds != null) && dodatkowe_informacje != null)) {
                edytuj_leki_imie_kota_edit_text.setText(imie_kota);
                edytuj_leki_edit_text.setText(meds);
                edytuj_leki_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);

            }

        } else {
            Toast.makeText(this, "Brak danych do wyświetlenia", Toast.LENGTH_SHORT).show();

        }

    }

}