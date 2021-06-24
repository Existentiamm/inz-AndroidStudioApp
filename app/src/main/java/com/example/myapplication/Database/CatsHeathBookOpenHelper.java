package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static com.example.myapplication.Database.CatsHealthBookDatabaseContract.KalendarzEntry.COLUMN_DATA;
import static com.example.myapplication.Database.CatsHealthBookDatabaseContract.SpisChorobEntry.COLUMN_IMIE_KOTA;
import static com.example.myapplication.Database.CatsHealthBookDatabaseContract.SpisChorobEntry.COLUMN_NAZWA_CHOROBY;
import static com.example.myapplication.Database.CatsHealthBookDatabaseContract.SpisLekowEntry.COLUMN_DODATKOWE_INFORMACJE;
import static com.example.myapplication.Database.CatsHealthBookDatabaseContract.SpisLekowEntry.COLUMN_NAZWA_LEKU;
import static com.example.myapplication.Database.CatsHealthBookDatabaseContract.SpisZabiegowEntry.COLUMN_NAZWA_ZABIEGU;

public class CatsHeathBookOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private Context context;
    private static final String DATABASE_NAME = "CatsHealthBook.db"; //nazwa aszego pliku, w którym będzie baza.
    private static final int DATABASE_VERSION = 1;
    protected Cursor cursor;

    public Cursor getCursor() {
        return cursor;
    }

    public CatsHeathBookOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CatsHealthBookDatabaseContract.SpisZwierzatEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabaseContract.KalendarzEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabaseContract.SpisZabiegowEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabaseContract.SpisLekowEntry.SQL_CREATE_TABLE);
        db.execSQL(CatsHealthBookDatabaseContract.SpisChorobEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabaseContract.SpisZwierzatEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabaseContract.KalendarzEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabaseContract.SpisZabiegowEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabaseContract.SpisLekowEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CatsHealthBookDatabaseContract.SpisChorobEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public void saveToDatabase() {
        db = this.getWritableDatabase();
    }

    public void readFromDatabase() {
        db = this.getReadableDatabase();
    }


    public void addMedicament(String imie_kota, String nazwa_leku, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.SpisLekowEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_NAZWA_LEKU, nazwa_leku);
        cv.put(COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);

        long result = db.insert(CatsHealthBookDatabaseContract.SpisLekowEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }


    public void addDisease(String imie_kota, String nazwa_choroby, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_NAZWA_CHOROBY, nazwa_choroby);
        cv.put(CatsHealthBookDatabaseContract.SpisChorobEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);
        long result = db.insert(CatsHealthBookDatabaseContract.SpisChorobEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }


    public void addTreatment(String imie_kota, String nazwa_zabiegu, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.SpisZabiegowEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_NAZWA_ZABIEGU, nazwa_zabiegu);
        cv.put(CatsHealthBookDatabaseContract.SpisZabiegowEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);
        long result = db.insert(CatsHealthBookDatabaseContract.SpisZabiegowEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void addCat(String imie_kota, String rok_urodzenia) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.SpisZwierzatEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(CatsHealthBookDatabaseContract.SpisZwierzatEntry.COLUMN_ROK_URODZENIA, rok_urodzenia);
        long result = db.insert(CatsHealthBookDatabaseContract.SpisZwierzatEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void addDate(String imie_kota, String data, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.KalendarzEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_DATA, data);
        cv.put(CatsHealthBookDatabaseContract.KalendarzEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);
        long result = db.insert(CatsHealthBookDatabaseContract.KalendarzEntry.TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllDataCalendar() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabaseContract.KalendarzEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    public Cursor readAllDataMeds() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabaseContract.SpisLekowEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    public Cursor readAllDataTreatments() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabaseContract.SpisZabiegowEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllDataDiseases() {
        readFromDatabase();
        String query = "SELECT * FROM " + CatsHealthBookDatabaseContract.SpisChorobEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Cursor readFromDatabaseOnlyOneCat() {
        readFromDatabase();
        String query = "SELECT " + CatsHealthBookDatabaseContract.SpisZwierzatEntry.COLUMN_IMIE_KOTA + " FROM " + CatsHealthBookDatabaseContract.SpisZwierzatEntry.TABLE_NAME;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToFirst();
        return cursor;
    }

    public void updateDate(String imie_kota, String row_id, String data, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.KalendarzEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_DATA, data);
        cv.put(CatsHealthBookDatabaseContract.KalendarzEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);

        long result = db.update(CatsHealthBookDatabaseContract.KalendarzEntry.TABLE_NAME, cv, "_id = ?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "NIE DZIAŁA FUNKCJAAAAA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show();
        }


    }

    public void updateDisease(String imie_kota, String row_id, String nazwa_choroby, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IMIE_KOTA, imie_kota);
        cv.put(CatsHealthBookDatabaseContract.SpisChorobEntry.COLUMN_NAZWA_CHOROBY, nazwa_choroby);
        cv.put(CatsHealthBookDatabaseContract.SpisChorobEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);

        long result = db.update(CatsHealthBookDatabaseContract.SpisChorobEntry.TABLE_NAME, cv, "_id = ?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "NIE DZIAŁA FUNKCJAAAAA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show();
        }


    }

    public void updateMedicament(String imie_kota , String row_id, String nazwa_leku, String dodatkowe_informacje) {

        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.SpisLekowEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_NAZWA_LEKU, nazwa_leku);
        cv.put(CatsHealthBookDatabaseContract.SpisLekowEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);

        long result = db.update(CatsHealthBookDatabaseContract.SpisLekowEntry.TABLE_NAME, cv, "_id = ?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "NIE DZIAŁA FUNKCJAAAAA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show();
        }

    }


    public void updateTreatment(String imie_kota, String row_id, String nazwa_zabiegu, String dodatkowe_informacje) {
        saveToDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CatsHealthBookDatabaseContract.SpisZabiegowEntry.COLUMN_IMIE_KOTA, imie_kota);
        cv.put(COLUMN_NAZWA_ZABIEGU, nazwa_zabiegu);
        cv.put(CatsHealthBookDatabaseContract.SpisZabiegowEntry.COLUMN_DODATKOWE_INFORMACJE, dodatkowe_informacje);

        long result = db.update(CatsHealthBookDatabaseContract.SpisZabiegowEntry.TABLE_NAME, cv, "_id = ?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "NIE DZIAŁA FUNKCJAAAAA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteDateFromCalendar(String id) {
        saveToDatabase();
        long result = db.delete(CatsHealthBookDatabaseContract.KalendarzEntry.TABLE_NAME, "_id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Not done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteDisease(String id) {
        saveToDatabase();
        long result = db.delete(CatsHealthBookDatabaseContract.SpisChorobEntry.TABLE_NAME, "_id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Not done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMedicament(String id) {
        saveToDatabase();
        long result = db.delete(CatsHealthBookDatabaseContract.SpisLekowEntry.TABLE_NAME, "_id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Not done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTreatment(String id) {
        saveToDatabase();
        long result = db.delete(CatsHealthBookDatabaseContract.SpisZabiegowEntry.TABLE_NAME, "_id = ?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Not done", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
