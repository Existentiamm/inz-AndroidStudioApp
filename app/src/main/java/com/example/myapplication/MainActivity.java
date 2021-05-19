package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHealthBookDatabseContract;
import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.Fragments.ChorobyFragment;
import com.example.myapplication.Fragments.DodanieFragment;
import com.example.myapplication.Fragments.KalendarzFragment;
import com.example.myapplication.Fragments.LekiFragment;
import com.example.myapplication.Fragments.ZabiegiFragment;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.example.myapplication.KalendarzView;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    final private String TAG = getClass().getSimpleName();
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FabSpeedDial fabSpeedDial;
    private Button addMeds, addDiseases, addTreatments;
    CatsHeathBookOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDB = new CatsHeathBookOpenHelper(MainActivity.this);

        openDatabase();
        createFragments();
        //handlingFAB();
        handlingNavigationDrawer();


    }

    private void openDatabase() {
        //BAZA DANYCH
        myDB = new CatsHeathBookOpenHelper(this);
        myDB.getReadableDatabase();//wywołanie bazy danych do stworzenia
    }


    private void handlingNavigationDrawer() {

        //typ toolbar, bo taki mamy typ, podpięcie xml'a z javą poprzez R.id.toolbar
        toolbar = findViewById(R.id.toolbar);
        //podstawowa metodą do stworzenia navigation bar. setSupportActionBar ustawia toolbar jako pasek aplikacji dla danej aktywności.
        setSupportActionBar(toolbar);

        //przekazanie DrawerLayout do zmiennej
        drawerLayout = findViewById(R.id.drawer_layout);

        //przekazanie już scalonego w activity_main nav_header i drawer_menu jako NavigationView
        //setNavigationItemSelectedListener jest interfejsem, który przechwytuje nam NavigationView
        NavigationView navigationView = findViewById(R.id.pełny_widok_navigation);
        navigationView.setNavigationItemSelectedListener(this);


        //klasa, która zapewnia powiązanie funkcjonalności DrawerLayout oraz ActionBar użytego w NavigationBar,
        //zapewnia ona ten przycisk po lewej stronie
        //new ActionBarDrawerToggle(context, layout_którego używamy, pasek aplikacji(toolbar), przekazanie string open, przekazanie string close);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //bedzie nam się kręci przycisk(przełącznik==toggle) razem z działaniem całego navigationBar
    }

    private void handlingFAB() {
        //fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
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
    }


    @Override
    protected void onDestroy() {
        //kiedy activity wejdzie w stan Destroy zamykasz połączenie z bazą
        myDB.close();
        super.onDestroy();
    }

    @Override

    public void onBackPressed() {
        //nie wchodzimy od razu z aplikacji po wciśnięciu przycisku powróć
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
            finish();

            Log.d(TAG, "onBackPressed");
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

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        //zamykanie się navigation bar po wybraniu case
        return true;
    }



    public void showViewForAddMeds(View view) {
        addMeds = (Button) findViewById(R.id.button_dodaj_do_listy_leki);
        addMeds.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DodajLeki.class);
            startActivity(intent);
        }

        );

    }

    public void showViewForAddTreatments(View view) {
        addTreatments = (Button) findViewById(R.id.button_dodaj_do_listy_zabiegi);
        addTreatments.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DodajZabiegi.class);
            startActivity(intent);
        }

        );

    }

    public void showViewForAddDiseases(View view) {
        addDiseases = (Button) findViewById(R.id.button_dodaj_do_listy_choroby);
        addDiseases.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DodajChoroby.class);
            startActivity(intent);
        }

        );

    }

    public void createFragments() {
        //fragmentChorobyRecyclerView
        ChorobyFragment recyclerviewChorobyFragment = new ChorobyFragment();
        FragmentManager fragmentChorobyManager = getSupportFragmentManager();
        FragmentTransaction fragmentChorobyTransaction = fragmentChorobyManager.beginTransaction();
        fragmentChorobyTransaction.add(R.id.linearlayout_activitymain, recyclerviewChorobyFragment);
        fragmentChorobyTransaction.commit();


        //fragmentLekiRecyclerView
        LekiFragment recyclerviewLekiFragment = new LekiFragment();
        FragmentManager fragmentLekiManager = getSupportFragmentManager();
        FragmentTransaction fragmentLekiTransaction = fragmentLekiManager.beginTransaction();
        fragmentLekiTransaction.add(R.id.linearlayout_activitymain, recyclerviewLekiFragment);
        fragmentLekiTransaction.commit();

        //fragmentZabiegiRecyclerView
        ZabiegiFragment recyclerviewZabiegiFragment = new ZabiegiFragment();
        FragmentManager fragmentZabiegiManager = getSupportFragmentManager();
        FragmentTransaction fragmentZabiegiTransaction = fragmentZabiegiManager.beginTransaction();
        fragmentZabiegiTransaction.add(R.id.linearlayout_activitymain, recyclerviewZabiegiFragment);
        fragmentZabiegiTransaction.commit();

    }


}//koniec klasy