package com.example.myapplication;

public class SpisZwierzat {

    private int id;
    private SpisZabiegow nazwaZabiegu;
    private SpisLekow nazwaLeku;
    private SpisChorob nazwaChoroby;
    private Kalendarz data;
    private String rokUrodzenia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpisZabiegow getNazwaZabiegu() {
        return nazwaZabiegu;
    }

    public void setNazwaZabiegu(SpisZabiegow nazwaZabiegu) {
        this.nazwaZabiegu = nazwaZabiegu;
    }

    public SpisLekow getNazwaLeku() {
        return nazwaLeku;
    }

    public void setNazwaLeku(SpisLekow nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }

    public SpisChorob getNazwaChoroby() {
        return nazwaChoroby;
    }

    public void setNazwaChoroby(SpisChorob nazwaChoroby) {
        this.nazwaChoroby = nazwaChoroby;
    }

    public Kalendarz getData() {
        return data;
    }

    public void setData(Kalendarz data) {
        this.data = data;
    }

    public String getRokUrodzenia() {
        return rokUrodzenia;
    }

    public void setRokUrodzenia(String rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }
}
