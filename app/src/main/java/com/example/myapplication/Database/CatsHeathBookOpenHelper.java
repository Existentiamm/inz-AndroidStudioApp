package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static com.example.myapplication.Database.CatsHealthBookDatabseContract.KalendarzEntry.COLUMN_DATA;
import static com.example.myapplication.Database.CatsHealthBookDatabseContract.SpisChorobEntry.COLUMN_NAZWA_CHOROBY;
import static com.example.myapplication.Database.CatsHealthBookDatabseContract.SpisLekowEntry.COLUMN_DODATKOWE_INFORMACJE;
import static com.example.myapplication.Database.CatsHealthBookDatabseContract.SpisLekowEntry.COLUMN_NAZWA_LEKU;
import static com.example.myapplication.Database.CatsHealthBookDatabseContract.SpisZabiegowEntry.COLUMN_NAZWA_ZABIEGU;

public class CatsHeathBookOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private Context context;
    private static final String DATABSE_NAME = "CatsHealthBook.db"; //nazwa aszego pliku, w którym będzie baza.
    private static final int DATABASE_VERSION = 1;
    protected Cursor cursor;

    public Cursor getCursor() {
        return cursor;
    }

    public CatsHeathBookOpenHelper(@Nullable Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CatsHealthBookDatabseContract.SpisZwierzatEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabseContract.KalendarzEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabseContract.SpisZabiegowEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabseContract.SpisLekowEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabseContract.SpisChorobEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabseContract.SpisZwierzatEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabseContract.KalendarzEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabseContract.SpisZabiegowEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabseContract.SpisLekowEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabseContract.SpisChorobEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public void saveToDatabase() {
        db = this.getWritableDatabase();
    }

    public void readFromDatabase() {
        db = this.getReadableDatabase();
    }


    public void addMed(String nazwa_leku, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAZWA_LEKU, nazwa_leku);
        cv.put(COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);

        long result = db.insert(CatsHealthBookDatabseContract.SpisLekowEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    ;

    public void addDisease(String nazwa_choroby, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAZWA_CHOROBY, nazwa_choroby);
        cv.put(CatsHealthBookDatabseContract.SpisChorobEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);
        long result = db.insert(CatsHealthBookDatabseContract.SpisChorobEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }


    public void addTreatment(String nazwa_zabiegu, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAZWA_ZABIEGU, nazwa_zabiegu);
        cv.put(CatsHealthBookDatabseContract.SpisZabiegowEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);
        long result = db.insert(CatsHealthBookDatabseContract.SpisZabiegowEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void addCat(String imie_kota, String nazwa_zabiegu, String nazwa_leku, String nazwa_choroby, String data, String rok_urodzenia) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_NAZWA_ZABIEGU, nazwa_zabiegu);
        cv.put(CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_NAZWA_LEKU, nazwa_leku);
        cv.put(CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_NAZWA_CHOROBY, nazwa_choroby);
        cv.put(CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_DATA, data);
        cv.put(CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_ROK_URODZENIA, rok_urodzenia);
        long result = db.insert(CatsHealthBookDatabseContract.SpisZwierzatEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void addDate(String data, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATA, data);
        cv.put(CatsHealthBookDatabseContract.KalendarzEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);
        long result = db.insert(CatsHealthBookDatabseContract.KalendarzEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor readAllDataMeds() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabseContract.SpisLekowEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    public Cursor readAllDataTreatments() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabseContract.SpisZabiegowEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllDataDiseases() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabseContract.SpisChorobEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readFromDatabaseOnlyNazwaLeku() {
        readFromDatabase();
        String query = "SELECT " + CatsHealthBookDatabseContract.SpisLekowEntry.COLUMN_NAZWA_LEKU + " FROM " + CatsHealthBookDatabseContract.SpisLekowEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToFirst();
        return cursor;

    }

    public Cursor readFromDatabaseOnlyNazwaChoroby() {
        readFromDatabase();
        String query = "SELECT " + CatsHealthBookDatabseContract.SpisChorobEntry.COLUMN_NAZWA_CHOROBY + " FROM " + CatsHealthBookDatabseContract.SpisChorobEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor readFromDatabaseOnlyNazwaZabiegu() {
        readFromDatabase();
        String query = "SELECT " + CatsHealthBookDatabseContract.SpisZabiegowEntry.COLUMN_NAZWA_ZABIEGU + " FROM " + CatsHealthBookDatabseContract.SpisZabiegowEntry.TABLE_NAME;
        if (db != null) //jezeli baza danych nie jest pusta to wykonaj query
             {
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToFirst();
        return cursor;
        //kurson przechowuje nasze query
    }

    public Cursor readFromDatabaseOnlyImieKota(){
        readFromDatabase();
        String query = "SELECT " + CatsHealthBookDatabseContract.SpisZwierzatEntry.COLUMN_IMIE_KOTA + " FROM " + CatsHealthBookDatabseContract.SpisZwierzatEntry.TABLE_NAME;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToFirst();
        return cursor;
    }

}
