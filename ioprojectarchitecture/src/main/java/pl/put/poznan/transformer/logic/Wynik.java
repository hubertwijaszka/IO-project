package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Wynik {

    private int[] rezultat;
    // indeksy
    // 0 -> ileSlowKluczowych
    // 1 -> ileKrokowMaKeyword
    // 2 -> ileKrokow
    // 3 -> zaglebienie kroku ( jesli przeszukuje wszystkie kroki ,to zwracana jest głebokość( wysokosć ?) grafu)
    private List<List<String>> listy = new ArrayList<List<String>>(3);
    // indeksy
    // 0 -> wadliwe kroki
    // 1 -> scenariusz z numeracją krokow do jakiegos poziomu
    // 2 -> lista krokow zawierajaca daną frazę

    public List<List<String>> getListy() {
        return listy;
    }

    public void setListy(List<List<String>> listy) {
        this.listy = listy;
    }

    public int[] getRezultat() {
        return rezultat;
    }

    public void setRezultat(int[] rezultat) {
        this.rezultat = rezultat;
    }

 /*   public List<String> getWadliweKroki() {
        return wadliweKroki;
    }

    public void setWadliweKroki(List<String> wadliweKroki) {
        this.wadliweKroki = wadliweKroki;
    }
    */
}
