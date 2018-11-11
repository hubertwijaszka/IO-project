package pl.put.poznan.transformer.rest;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Wynik;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ScenarioSerwis is used to return amount of steps,
 * amount of keywords and Steps that do not start with
 * keywords.
 */

public class ScenarioSerwis {

    //metody
    //ile jest wszystkich krokow   p[2]
    //ile jest slow kluczowych    p[0]
    //ile jest krokow ze slowami kluczowymi p[1]
    //lista krokow, ktora nie zaczyna się od aktora systemowego  List<String> w

    /**
     * Method creates a {@link Wynik} object and iterates
     * over the Steps to get data about them.
     * @param obiekt Scenario to get information from.
     * @return Wynik object that stores collected data.
     */

    public Wynik ileJest(Scenario obiekt)
    {
        int[] p={0,0,0};int i;
        List<String> w = new ArrayList<>();
        Wynik wynik=new Wynik();
        //przejdz po WSZYSTKICH krokach scenariusza
        for(i=0;i<obiekt.getSteps().length;i++)
        {
            obiekt.przeszukiwanie(obiekt.getSteps()[i],p,w);
        }
        wynik.setRezultat(p);
        wynik.setWadliweKroki(w);
        return wynik;
    }

    /**
     * @return List of steps as a whole.
     */
    public String ileKrokow(Scenario scenario){
        return Integer.toString(ileJest(scenario).getRezultat()[2]);
    }
    /**
     * @return List of Keywords
     */
    public String ileSlowKluczowych(Scenario scenario){
        return Integer.toString(ileJest(scenario).getRezultat()[1]);
    }
    /**
     * @return List of Steps that do not start with keywords.
     */
    public List<String> bledneKroki(Scenario scenario){
        return ileJest(scenario).getWadliweKroki();
    }
}
