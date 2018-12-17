package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *Class that keeps data obtained from parsing scenario
 */

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

    /**
     * @return list with
     *		  failed steps on index 0
     *		  scenario cut from the certain point of depth on index 1
     *		  steps containing a certain phrase on index 2
     */

    public List<List<String>> getListy() {
        return listy;
    }

    /**
     * @param listy list with data to be saved
     */

    public void setListy(List<List<String>> listy) {
        this.listy = listy;
    }

    /**
     * @return int[] table containing
     *		  amount of keywords on index 0
     *		  amount of steps containing keywords on index 1
     *		  amount of steps, that Scenario has on index 2
     *		  level of depth of step given (if program parses the whole scenario, max depth of scenario is returned) on index 3
     */

    public int[] getRezultat() {
        return rezultat;
    }

    /**
     * @param rezultat int[] list with data to be saved
     */

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
