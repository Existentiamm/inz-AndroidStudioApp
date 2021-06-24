package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class AktualizacjaChorob extends AppCompatActivity {
    AutoCompleteTextView edytuj_choroby_edit_text, edytuj_choroby_imie_kota_edit_text;
    TextView id_choroby, edytuj_choroby_dodatkowe_informacje_edit_text;
    Button edytuj_dane_choroby_button, button_usun_z_listy_chorob;
    String _id, imie_kota, diseases, dodatkowe_informacje;
    String[] choroby_lista;
    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_chorob);

        edytuj_choroby_imie_kota_edit_text = findViewById(R.id.edytuj_choroby_imie_kota_edit_text);
        edytuj_choroby_edit_text = findViewById(R.id.edytuj_choroby_edit_text);
        edytuj_choroby_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_choroby_dodatkowe_informacje_edit_text);
        edytuj_dane_choroby_button = findViewById(R.id.edytuj_dane_choroby_button);
        button_usun_z_listy_chorob = findViewById(R.id.button_usun_z_listy_chorob);


        getIntentData();
        deleteDisease();
        UpdateDisease();

    }
    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(AktualizacjaChorob.this);
        myDB.readFromDatabaseOnlyOneCat();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        choroby_lista = new String[0];
        choroby_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() > 0 ) {
            do {
                choroby_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AktualizacjaChorob.this,android.R.layout.simple_dropdown_item_1line, choroby_lista);
        edytuj_choroby_imie_kota_edit_text.setAdapter(adapter);
    }

    private void pokazDaneAutoCompleteChoroby() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.diseases);
        edytuj_choroby_edit_text.setAdapter(adapter);

    }

    private void confirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usunąć " + diseases + "?");
        builder.setMessage("Jesteś pewien, że chcesz usunąć " + diseases + "?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDB = new CatsHeathBookOpenHelper(AktualizacjaChorob.this);
                myDB.deleteDisease(_id);
                finish();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    private void deleteDisease() {
        button_usun_z_listy_chorob.setOnClickListener(v -> {
            confirmDialog();

        });
    }

    private void getIntentData() {

        if (getIntent().hasExtra("imie_kota_choroby") && getIntent().hasExtra("id_choroby") && (getIntent().hasExtra("diseases") && getIntent().hasExtra("dodatkowe_informacje"))) {

            //odebraie danych z intencji, muszą być stworzone zmienne typu String by odebrać
            imie_kota = getIntent().getStringExtra("imie_kota_choroby");
            _id = getIntent().getStringExtra("id_choroby");
            diseases = getIntent().getStringExtra("diseases");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");
            //podpięcie danych pod odpowiedni TextView

            if ((_id != null)&& (imie_kota != null) && (diseases != null) && (dodatkowe_informacje != null)) {
                edytuj_choroby_imie_kota_edit_text.setText(imie_kota);
                edytuj_choroby_edit_text.setText(diseases);
                edytuj_choroby_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "Brak danych do wyświetlenia", Toast.LENGTH_SHORT).show();
        }


    }

    private void UpdateDisease() {
        pokazImieKotaAutoComplete();
        pokazDaneAutoCompleteChoroby();
        edytuj_dane_choroby_button.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaChorob.this);
            imie_kota = edytuj_choroby_imie_kota_edit_text.getText().toString().trim();
            diseases = edytuj_choroby_edit_text.getText().toString().trim();
            dodatkowe_informacje = edytuj_choroby_dodatkowe_informacje_edit_text.getText().toString().trim();
            myDB.updateDisease(imie_kota, _id, diseases, dodatkowe_informacje);
        });


    }
}