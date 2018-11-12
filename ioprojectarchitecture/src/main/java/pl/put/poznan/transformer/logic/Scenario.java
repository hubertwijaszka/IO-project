package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Scenario made out of json structure
 */

public class Scenario {
    private String title;
    private String actors[];
    private String systemActor;
    private Step steps[];
    private static final Logger logger = LoggerFactory.getLogger(Scenario.class);

    /**
     * @return title of scenario
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method sets title of Scenario
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Array conatining actors.
     */
    public String[] getActors() {
        return actors;
    }

    /**
     * Method sets actors for scenario
     * @param actors Array containing new Actors
     */
    public void setActors(String[] actors) {
        this.actors = actors;
    }

    /**
     * @return System actor of scenario.
     */
    public String getSystemActor() {
        return systemActor;
    }

    /**
     * @param systemActor new SystemActor.
     */
    public void setSystemActor(String systemActor) {
        this.systemActor = systemActor;
    }

    /**
     * @return Array conatining steps.
     */
    public Step[] getSteps() {
        return steps;
    }

    /**
     * @param steps Array conatining steps.
     */
    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    /**
     * Method checks how many elements in one step
     * begin with/include keywords.
     * @param krok Step that we want to check.
     * @param p Array in which number of keywords,
     *          steps containing keywords and steps
     *          in general will be stored.
     * @param w List in which Steps that do not start with
     *          keyword will be stored.
     * @return amount of elements
     */
    public int przeszukiwanie(Step krok, int[] p, List<String> w) // metoda sprawdza ile elementow w jednym kroku zawiera/zaczyna się od
    // słowa kluczowego
    //param : 0 -> ile krokow
    {
        //przeszukiwanie grafu
        //sprawdzam, czy krok jest wadliwy ( jest taki, gdy nie zaczyna się od aktora systemowego )
        if (zlyKrok(krok.getText(),actors,systemActor )) w.add(krok.getText());
        //ile jest ogolnie slow kluczowych
        p[0] += ileSlowKluczowych(krok.getText());
        //ile krokow zawiera choc jedno slowo kluczowe
        p[1] += zawieraSlowoKluczowe(krok.getText());
        //jesli ta funkcja zostala wywolana tzn. ze wystapil kolejny krok
        p[2]++;
        // podliczenie wszystkich krokow wykona sie poprawnie jesli w mainie() (czy gdziekolwiek indziej)
        // wywolane zostanie:
        //                      for(i=0;i<obiekt.steps.length;i++)
        //                      {
        //                          obiekt.przeszukiwanie(obiekt.steps[i]);
        //                      }
        int i;
        for (i = 0; i < krok.getSubsteps().length; i++) {
            przeszukiwanie(krok.getSubsteps()[i], p, w);
        }
        return 0;
    }
    /**
     * Method checks whether the Step starts with a keyword.
     * @param text Step that we want to check.
     * @return 1 if keyword found, 0 otherwise.
     */
    public int zaczynaSieOdSlowaKlucz(String text) {
        //Sprawdzam, czy krok zaczyna się od słowa kluczowego
        Keyword k = new Keyword();
        int wyn = 0;
        int i;
        for (i = 0; i < k.getKeywords().length; i++) {
            if (text.startsWith(k.getKeywords()[i])) {
                wyn = 1;
                break;
            }
        }
        return wyn;
    }
    /**
     * Method checks whether the Step conatins the keyword.
     * @param text Step that we want to check.
     * @return 1 if keyword found, 0 otherwise.
     */
    public int zawieraSlowoKluczowe(String text) {
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        Keyword k = new Keyword();
        int wyn = 0, i;
        for (i = 0; i < k.getKeywords().length; i++) {
            if (text.contains(k.getKeywords()[i])) {
                wyn = 1;
                break;
            }
        }
        return wyn;
    }

    /**
     * Counts how many keywords appeared in a particular text
     * @param text Text that we want to get amount of keywords from.
     * @return Amount of keywords found.
     */
    public int ileSlowKluczowych(String text) {
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        Keyword k = new Keyword();
        int wyn = 0, i;
        for (i = 0; i < k.getKeywords().length; i++) {
            if (text.contains(k.getKeywords()[i])) {
                wyn++;
            }
        }
        return wyn;
    }

    public boolean zlyKrok(String text, String[] aktor, String aktorsys) {
        //musze uwzglednic ze moze byc slowo kluczowe
        Keyword k = new Keyword();
        String org = text;
        boolean wyn = true;
        int i, good, bad;
        //usuwam spacje z przodu
        i = 0;good = 0;bad = 0;
        while (org.charAt(i) == ' ') {
            org = org.replaceFirst(" ", "");
        }
        //jesli jest slowo kluczowe na poczatku, to krok jest w porządku
        for (i = 0; i < k.getKeywords().length; i++) {
            //jesli krok zaczyna się od slowa kluczowego, to dobrze
            if (org.startsWith(k.getKeywords()[i])) {
                good = 1;
            } else if (org.contains(k.getKeywords()[i])) {
                bad = 1;
            }//jesli slowo kluczowe jest w srodku zdania to zle
        }
        if (bad == 1) return true;
        else if (good == 1 && bad == 0) return false;

        //jesli nie, to sprawdzam, czy występuje ktoryś z aktorów lub aktor systemowy
        //sprawdzam ,czy krok zaczyna sie od jakiegokolwiek aktora
        for (i = 0; i < aktor.length + 1; i++) {
            if (i < aktor.length) {
                if (org.startsWith(aktor[i])) {
                    wyn = false;
                    break;
                } //success
            } else //sprawdzam aktora systemowego
            {
                if (org.startsWith(aktorsys)) {
                    wyn = false;
                    break;
                } //success
            }
        }
        return wyn;
    }
}

