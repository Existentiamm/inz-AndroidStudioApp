package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    TextView id_choroby, edytuj_choroby_dodatkowe_informacje_edit_text;
    Button edytuj_dane_choroby_button, button_usun_z_listy_chorob;
    String _id, diseases, dodatkowe_informacje;

    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualizacja_chorob);

        edytuj_choroby_edit_text = findViewById(R.id.edytuj_choroby_edit_text);
        edytuj_choroby_dodatkowe_informacje_edit_text = findViewById(R.id.edytuj_choroby_dodatkowe_informacje_edit_text);
        edytuj_dane_choroby_button = findViewById(R.id.edytuj_dane_choroby_button);
        button_usun_z_listy_chorob = findViewById(R.id.button_usun_z_listy_chorob);


        getIntentData();
        deleteDisease();
        UpdateDisease();

    }

    private void confirmDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usunąć " + diseases+ "?" );
        builder.setMessage("Jesteś pewien, że chcesz usunąć " + diseases + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDB = new CatsHeathBookOpenHelper(AktualizacjaChorob.this);
                myDB.deleteDisease(_id);
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

    private void deleteDisease() {
        button_usun_z_listy_chorob.setOnClickListener(v -> {
            confirmDialog();

        });
    }
    private void getIntentData() {

        if (getIntent().hasExtra("id_choroby") && (getIntent().hasExtra("diseases") && getIntent().hasExtra("dodatkowe_informacje"))) {

            //odebraie danych z intencji, muszą być stworzone zmienne typu String by odebrać
            _id = getIntent().getStringExtra("id_choroby");
            diseases = getIntent().getStringExtra("diseases");
            dodatkowe_informacje = getIntent().getStringExtra("dodatkowe_informacje");
            //podpięcie danych pod odpowiedni TextView

            if ((_id != null) && (diseases != null) && (dodatkowe_informacje != null)) {
                edytuj_choroby_edit_text.setText(diseases);
                edytuj_choroby_dodatkowe_informacje_edit_text.setText(dodatkowe_informacje);
            }

        } else {
            Toast.makeText(this, "Brak danych do wyświetlenia", Toast.LENGTH_SHORT).show();
        }


    }

    private void UpdateDisease() {
        edytuj_dane_choroby_button.setOnClickListener(v -> {
            myDB = new CatsHeathBookOpenHelper(AktualizacjaChorob.this);
            diseases = edytuj_choroby_edit_text.getText().toString().trim();
            dodatkowe_informacje = edytuj_choroby_dodatkowe_informacje_edit_text.getText().toString().trim();
            myDB.updateDisease(_id, diseases, dodatkowe_informacje);
        });


    }
}