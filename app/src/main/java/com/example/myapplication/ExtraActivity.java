package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        TextView textView = findViewById(R.id.data_extra);
        EditText editText = findViewById(R.id.data_details);

        String date = getIntent().getStringExtra("date");

        if(date!= null) {

            textView.setText(date);

        }
    }

    public void ZapiszDate(View view) {
        Toast.makeText(getApplicationContext(), "ma zapisywać date", Toast.LENGTH_SHORT).show();
    }
}

