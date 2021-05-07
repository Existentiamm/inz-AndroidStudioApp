package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;

import org.w3c.dom.Text;

public class AktualizacjaChorob extends AppCompatActivity {
    AutoCompleteTextView edytuj_choroby_edit_text;
    TextView edytuj_choroby_dodatkowe_informacje_edit_text;
    Button edytuj_dane_choroby_button;
    String _id, diseases, dodatkowe_informacje;

    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_chorob);

        edytuj_choroby_edit_text = findViewById(R.id.edytuj_choroby_edit_text);
        edytuj_choroby_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_choroby_dodatkowe_informacje_edit_text);
        edytuj_dane_choroby_button = findViewById(R.id.edytuj_dane_choroby_button);


        getIntentData();

        edytuj_dane_choroby_button.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaChorob.this);
            myDB.updateDisease(_id, diseases,dodatkowe_informacje);
        });
    }

    private void getIntentData() {

        if (getIntent().hasExtra("diseases") && getIntent().hasExtra("dodatkowe_informacje")) {

            //odebraie danych z intencji, muszą być stworzone zmienne typu String by odebrać
            diseases = getIntent().getStringExtra("diseases");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");
            //podpięcie danych pod odpowiedni TextView

            if ((diseases != null) && (dodatkowe_informacje != null)) {
                edytuj_choroby_edit_text.setText(diseases);
                edytuj_choroby_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "Brak dnaych do wyświetlenia", Toast.LENGTH_SHORT).show();
        }


    }

    private void UpdateDisease() {


    }
}