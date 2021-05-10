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
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Database.CatsHeathBookOpenHelper;
import com.example.myapplication.Fragments.ChorobyFragment;
import com.example.myapplication.Fragments.DodanieFragment;
import com.example.myapplication.Fragments.KalendarzFragment;
import com.example.myapplication.Fragments.LekiFragment;
import com.example.myapplication.Fragments.ZabiegiFragment;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.example.myapplication.KalendarzView;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    final private String TAG = getClass().getSimpleName();
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Spinner mySpinner;
    private CatsHeathBookOpenHelper mDbOpenHelper;
    private FabSpeedDial fabSpeedDial;
    private Button addMeds, addDiseases, addTreatments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDatabase();
        createFragments();
        handlingSpinner();
        handlingFAB();
        handlingNavigationDrawer();


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
        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
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

    private void handlingSpinner() {
        //dodanie spinera w actionBar
        mySpinner = findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_spinner_item, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(R.layout.custrom_spinner_item_dropdown);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                    Toast.makeText(MainActivity.this,
                                                            mySpinner.getSelectedItem().toString(),
                                                            Toast.LENGTH_SHORT)
                                                            .show();
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            }
        );
    }

    private void openDatabase() {
        //BAZA DANYCH
        mDbOpenHelper = new CatsHeathBookOpenHelper(this);
        mDbOpenHelper.getReadableDatabase();//wywołanie bazy danych do stworzenia
    }


    @Override
    protected void onDestroy() {
        //kiedy activity wejdzie w stan Destroy zamykasz połączenie z bazą
        mDbOpenHelper.close();
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



    public void addMeds(View view) {
        addMeds = (Button) findViewById(R.id.button_dodaj_do_listy_leki);
        addMeds.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, DodajLeki.class);
                                           startActivity(intent);
                                       }
                                   }

        );

    }

    public void AddTreatments(View view) {
        addTreatments = (Button) findViewById(R.id.button_dodaj_do_listy_zabiegi);
        addTreatments.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, DodajZabiegi.class);
                                           startActivity(intent);
                                       }
                                   }

        );

    }

    public void addDiseases(View view) {
        addDiseases = (Button) findViewById(R.id.button_dodaj_do_listy_choroby);
        addDiseases.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, DodajChoroby.class);
                                           startActivity(intent);
                                       }
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