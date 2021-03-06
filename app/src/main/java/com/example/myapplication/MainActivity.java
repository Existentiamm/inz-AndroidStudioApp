package com.example.myapplication;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    //floating button
    public FloatingActionButton floatingActionButton;

    public boolean isPop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true; //false : nie pokazuj menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true; //po kliknięciu na przycisk pokazuje jako Toast tytuł z extra_menu.
            }

            @Override
            public void onMenuClosed() {

            }
        });


       /* //floatingButton
        floatingActionButton = findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAnimalActivity.class);
                startActivity(intent);
            }
        }); inny sposób na zrobienie buttona*/

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

    } //koniec klasy onCreate





    @Override
    //nie wchodzimy od razu z aplikacji po wciśnięciu przycisku powróć
    public void onBackPressed() {
       if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
           super.onBackPressed();
           finish();
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
            case R.id.nav_dodanie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DodanieFragment()).commit();
                break;
            case R.id.nav_lista:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PetsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        //zamykanie się navigation bar po wybraniu case
        return true;
    }



    public void ZapiszDate(View view) {
        Toast.makeText(getApplicationContext(), "ma zapisywać date", Toast.LENGTH_SHORT).show();
    }

}//koniec klasy