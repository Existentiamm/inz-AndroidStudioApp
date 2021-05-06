package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

public class KalendarzView extends AppCompatActivity {
    TextView data_extra;
    EditText data_details;
    Button zapisz_dane_kalendarz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendarz_view);

        data_extra = findViewById(R.id.data_extra);
        data_details = findViewById(R.id.data_details);
        zapisz_dane_kalendarz = findViewById(R.id.zapisz_dane_kalendarz);
        String date = getIntent().getStringExtra("date");

        if(date!= null) {

            data_extra.setText(date);
        }

        zapisz_dane_kalendarz.setOnClickListener(v -> {
            CatsHeathBookOpenHelper myDB = new CatsHeathBookOpenHelper(KalendarzView.this);
            myDB.addDate(data_extra.getText().toString().trim(),
                    data_details.getText().toString().trim());
        });
    }



}
