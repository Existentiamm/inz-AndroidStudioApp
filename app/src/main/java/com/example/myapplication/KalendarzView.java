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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class KalendarzView extends AppCompatActivity {
    TextView data_extra;
    EditText data_details;
    Button zapisz_dane_kalendarz, usun_dane_kalendarz;
    AutoCompleteTextView dodaj_datę_imie_kota_edit_text;
    CatsHeathBookOpenHelper myDB;
    String[] koty_lista;
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendarz_view);

        dodaj_datę_imie_kota_edit_text= findViewById(R.id.dodaj_datę_imie_kota_edit_text);
        data_extra = findViewById(R.id.data_extra);
        data_details = findViewById(R.id.data_details);
        zapisz_dane_kalendarz = findViewById(R.id.zapisz_dane_kalendarz);
        usun_dane_kalendarz = findViewById(R.id.usun_dane_kalendarz);

        getIntentData();
        SaveDateInCalendar();
        deleteDataFromCalendar();

    }

    private void deleteDataFromCalendar() {
        usun_dane_kalendarz.setOnClickListener(new View.OnClickListener() {
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
                myDB = new CatsHeathBookOpenHelper(KalendarzView.this);
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

    private void getIntentData() {
        String date = getIntent().getStringExtra("date");

        if(date!= null) {

            data_extra.setText(date);
        }
    }
    private void SaveDateInCalendar() {
        //pokazImieKotaAutoComplete();
        zapisz_dane_kalendarz.setOnClickListener(v -> {
            CatsHeathBookOpenHelper myDB = new CatsHeathBookOpenHelper(KalendarzView.this);
            myDB.addDate(dodaj_datę_imie_kota_edit_text.getText().toString().trim(), data_extra.getText().toString().trim(),
                    data_details.getText().toString().trim());
        });
    }

    private void pokazImieKotaAutoComplete() {
        myDB = new CatsHeathBookOpenHelper(KalendarzView.this);
        myDB.readFromDatabaseOnlyImieKota();
        Cursor cursor = myDB.getCursor(); //pobranie kursora z Helpera
        koty_lista = new String[0];
        koty_lista = new String[cursor.getCount()];

        int i = 0;
        do {
            koty_lista[i] = cursor.getString(0);
            i++;
        } while (cursor.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(KalendarzView.this,android.R.layout.simple_dropdown_item_1line, koty_lista);
        dodaj_datę_imie_kota_edit_text.setAdapter(adapter);
    }



}
