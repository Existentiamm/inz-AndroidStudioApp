package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //typ toolbar, bo taki mamy typ, podpięcie xml'a z javą poprzez R.id.toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //podstawowa metodą do stworzenia navigation bar. setSupportActionBar ustawia toolbar jako pasek aplikacji dla danej aktywności.
        setSupportActionBar(toolbar);

        //przekazanie DrawerLayout do zmiennej
        drawer = findViewById(R.id.drawer_layout);

        //przekazanie już scalonego w activity_main nav_header i drawer_menu jako NavigationView
        //setNavigationItemSelectedListener jest interfejsem, który przechwytuje nam NavigationView
        NavigationView navigationView = findViewById(R.id.pełny_widok_navigation);
        navigationView.setNavigationItemSelectedListener(this);


        //klasa, która zapewnia powiązanie funkcjonalności DrawerLayout oraz ActionBar użytego w NavigationBar,
        //zapewnia ona ten przycisk po lewej stronie
        //new ActionBarDrawerToggle(context, layout_którego używamy, pasek aplikacji(toolbar), przekazanie string open, przekazanie string close);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //bedzie nam się kręci przycisk(przełącznik==toggle) razem z działaniem całego navigationBar

    }

    @Override
    //nie wchodzimy od razu z aplikacji po wciśnięciu przycisku powróć
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            //zgoda na przejście do stworzonego nowego fragmentu a tam odwalam reszte
            case R.id.nav_kalendarz:
                //getSupportFragmentManager zwraca nam fragmentManager, który jest połączony z danych fragmentem
                //replace ( z czego podmieniam, na co podmieniam, wywołaj)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new KalendarzFragment()).commit();
                break;
            case R.id.nav_leki:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LekiFragment()).commit();
                break;
            case R.id.nav_zabiegi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ZabiegiFragment()).commit();
                break;
            case R.id.nav_choroby:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChorobyFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        //zamykanie się navigation bar po wybraniu case
        return true;
    }

    //przycisk Dodaj
    public void Dodaj(View view) {
        Intent i = new Intent(this, AddAnimalActivity.class);
        startActivity(i);
    }
}