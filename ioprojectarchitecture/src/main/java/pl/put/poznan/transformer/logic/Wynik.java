package pl.put.poznan.transformer.logic;

import java.util.List;

public class Wynik {

    private int[] rezultat;
    private List<String>wadliweKroki;

    public int[] getRezultat() {
        return rezultat;
    }

    public void setRezultat(int[] rezultat) {
        this.rezultat = rezultat;
    }

    public List<String> getWadliweKroki() {
        return wadliweKroki;
    }

    public void setWadliweKroki(List<String> wadliweKroki) {
        this.wadliweKroki = wadliweKroki;
    }
}
