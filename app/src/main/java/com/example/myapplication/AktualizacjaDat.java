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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class AktualizacjaDat extends AppCompatActivity {
    AutoCompleteTextView edytuj_date_edit_text, edytuj_date_imie_kota_edit_text;
    TextView  edytuj_date_dodatkowe_informacje_edit_text;
    Button edytuj_dane_daty_button, button_usun_z_listy_dat;
    String _id, imie_kota, daty, dodatkowe_informacje;
    String[] daty_lista;
    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_dat);

        edytuj_date_imie_kota_edit_text = findViewById(R.id.edytuj_date_imie_kota_edit_text);
        edytuj_date_edit_text = findViewById(R.id.edytuj_date_edit_text);
        edytuj_date_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_date_dodatkowe_informacje_edit_text);
        edytuj_dane_daty_button = findViewById(R.id.edytuj_dane_daty_button);
        button_usun_z_listy_dat = findViewById(R.id.button_usun_z_listy_dat);


        getIntentData();
        deleteDataFromCalendar();
        updateDate();
    }

    private void deleteDataFromCalendar() {
        button_usun_z_listy_dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    private void confirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usuwanie");
        builder.setMessage("Jesteś pewien, że chcesz usunąć ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDB = new CatsHeathBookOpenHelper(AktualizacjaDat.this);
                myDB.deleteDateFromCalendar(_id);
                finish();
            }
        });
        builder.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(AktualizacjaDat.this);
        myDB.readFromDatabaseOnlyOneCat();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        daty_lista = new String[0];
        daty_lista = new String[cursor.getCount()];

        int i = 0;
        if(cursor.getCount() > 0 ) {
            do {
                daty_lista[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AktualizacjaDat.this,android.R.layout.simple_dropdown_item_1line, daty_lista);
        edytuj_date_imie_kota_edit_text.setAdapter(adapter);
    }



    private void getIntentData() {

        if (getIntent().hasExtra("imie_kota_kalendarz") && getIntent().hasExtra("id_daty") && (getIntent().hasExtra("daty") && getIntent().hasExtra("dodatkowe_informacje"))) {

            //odebraie danych z intencji, muszą być stworzone zmienne typu String by odebrać
            imie_kota = getIntent().getStringExtra("imie_kota_kalendarz");
            _id = getIntent().getStringExtra("id_daty");
            daty = getIntent().getStringExtra("daty");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");
            //podpięcie danych pod odpowiedni TextView

            if ((_id != null)&& (imie_kota != null) && (daty != null) && (dodatkowe_informacje != null)) {
                edytuj_date_imie_kota_edit_text.setText(imie_kota);
                edytuj_date_edit_text.setText(daty);
                edytuj_date_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "sprawdzam dlaczego ", Toast.LENGTH_SHORT).show();
        }


    }

    private void updateDate() {
        pokazImieKotaAutoComplete();
        edytuj_dane_daty_button.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaDat.this);
            imie_kota = edytuj_date_imie_kota_edit_text.getText().toString().trim();
            daty = edytuj_date_edit_text.getText().toString().trim();
            dodatkowe_informacje = edytuj_date_dodatkowe_informacje_edit_text.getText().toString().trim();
            myDB.updateDate(imie_kota, _id, daty, dodatkowe_informacje);
        });


    }
}