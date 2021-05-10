package com.example.myapplication.Database;

import android.provider.BaseColumns;

public final class CatsHealthBookDatabseContract {

    private CatsHealthBookDatabseContract() {
    } //prywatny konstruktor


    public static final class SpisZwierzatEntry  implements BaseColumns {
        public static final String TABLE_NAME = "spis_zwierzat";
        public static final String COLUMN_ID_KOTA = "id_kota"; //zamiast tego jest nasze globalne _ID
        public static final String COLUMN_IMIE_KOTA = "imie_kota";
        public static final String COLUMN_NAZWA_ZABIEGU = "nazwa_zabiegu";
        public static final String COLUMN_NAZWA_LEKU = "nazwa_leku";
        public static final String COLUMN_NAZWA_CHOROBY = "nazwa_choroby";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_ROK_URODZENIA = "rok_urodzenia";


        //create table spis_zwierzat (id_kota, nazwa_zabiegu, nazwa_leku, nazwa_choroby, data, rok_urodzenia);

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_IMIE_KOTA + " TEXT NOT NULL UNIQUE, " +
                        COLUMN_NAZWA_ZABIEGU + " TEXT, " +
                        COLUMN_NAZWA_LEKU + " TEXT, " +
                        COLUMN_NAZWA_CHOROBY + " TEXT, " +
                        COLUMN_DATA + " TEXT, " +
                        COLUMN_ROK_URODZENIA + " TEXT NOT NULL) ";

    }

    public static final class KalendarzEntry implements BaseColumns{
        public static final String TABLE_NAME = "kalendarz";
        public static final String COLUMN_ID_KOTA = "id_kota"; //zamiast tego jest nasze globalne _ID
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_DODATKOWE_INFORMACJE = "dodatkowe_informacje";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATA + " TEXT NOT NULL, " +
                        COLUMN_DODATKOWE_INFORMACJE + " TEXT NOT NULL) ";

    }

    public static final class SpisZabiegowEntry implements BaseColumns{
        public static final String TABLE_NAME = "spis_zabiegow";
        public static final String COLUMN_NAZWA_ZABIEGU = "nazwa_zabiegu";
        public static final String COLUMN_DODATKOWE_INFORMACJE = "dodatkowe_informacje";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAZWA_ZABIEGU + " TEXT NOT NULL UNIQUE, " +
                        COLUMN_DODATKOWE_INFORMACJE + " TEXT NOT NULL) ";
    }

    public static final class SpisLekowEntry implements BaseColumns{
        public static final String TABLE_NAME = "spis_lekow";
        public static final String COLUMN_NAZWA_LEKU = "nazwa_leku";
        public static final String COLUMN_DODATKOWE_INFORMACJE = "dodatkowe_informacje";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAZWA_LEKU + " TEXT NOT NULL UNIQUE, " +
                        COLUMN_DODATKOWE_INFORMACJE + " TEXT NOT NULL) ";
    }

    public static final class SpisChorobEntry implements BaseColumns{
        public static final String TABLE_NAME = "spis_chorob";
        public static final String COLUMN_NAZWA_CHOROBY = "nazwa_choroby";
        public static final String COLUMN_DODATKOWE_INFORMACJE = "dodatkowe_informacje";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAZWA_CHOROBY + " TEXT NOT NULL UNIQUE, " +
                        COLUMN_DODATKOWE_INFORMACJE + " TEXT NOT NULL) ";
    }

}
