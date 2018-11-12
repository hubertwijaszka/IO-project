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
    public int przeszukiwanie(Step krok,int[] p,List<String> w) // metoda sprawdza ile elementow w jednym kroku zawiera/zaczyna się od
                                          // słowa kluczowego
                                            //param : 0 -> ile krokow
    {
        logger.info("przeszukiwanie Begin");
        //przeszukiwanie grafu
        //sprawdzam, czy krok jest wadliwy ( jest taki, gdy nie zaczyna się od aktora systemowego )
        if(nieZaczynaSieOdAktora(krok.getText(),systemActor)) w.add(krok.getText());
        //ile jest ogolnie slow kluczowych
        p[0]+= ileSlowKluczowych(krok.getText());
        //ile krokow zawiera choc jedno slowo kluczowe
        p[1]+= zawieraSlowoKluczowe(krok.getText());
        //jesli ta funkcja zostala wywolana tzn. ze wystapil kolejny krok
        p[2]++;
        // podliczenie wszystkich krokow wykona sie poprawnie jesli w mainie() (czy gdziekolwiek indziej)
        // wywolane zostanie:
        //                      for(i=0;i<obiekt.steps.length;i++)
        //                      {
        //                          obiekt.przeszukiwanie(obiekt.steps[i]);
        //                      }
        int i;
        for(i=0;i<krok.getSubsteps().length;i++)
        {
            przeszukiwanie(krok.getSubsteps()[i],p,w);
        }
        logger.info("przeszukiwanie End");

        return 0;
    }

    /**
     * Method checks whether the Step starts with a keyword.
     * @param text Step that we want to check.
     * @return 1 if keyword found, 0 otherwise.
     */
    public int zaczynaSieOdSlowaKlucz(String text) {
        logger.info("zaczynaSieOdSlowaKlucz Begin");

        Keyword k = new Keyword();
            int wyn=0;int i;
            for (i=0; i< k.getKeywords().length; i++)
            {
                if(text.startsWith(k.getKeywords()[i])){wyn=1;break;}
            }
        logger.info("zaczynaSieOdSlowaKlucz End");
        return wyn;
    }

    /**
     * Method checks whether the Step conatins the keyword.
     * @param text Step that we want to check.
     * @return 1 if keyword found, 0 otherwise.
     */
    public int zawieraSlowoKluczowe(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        logger.info("zawieraSlowoKluczowe Begin");

        Keyword k = new Keyword();
        int wyn=0,i;
        for(i=0; i< k.getKeywords().length; i++)
        {
            if (text.contains(k.getKeywords()[i])){wyn=1;break;}
        }
        logger.info("zawieraSlowoKluczowe End");

        return wyn;
    }


    /**
     * Counts how many keywords appeared in a particular text
     * @param text Text that we want to get amount of keywords from.
     * @return Amount of keywords found.
     */
    public int ileSlowKluczowych(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        logger.info("ileSlowKluczowych Begin");
        Keyword k = new Keyword();
        int wyn=0,i;
        for(i=0; i< k.getKeywords().length; i++)
        {
            if (text.contains(k.getKeywords()[i])){wyn++;}
        }
        logger.info("ileSlowKluczowych End");
        return wyn;
    }

    /**
     * Method checks whether the Step starts with a keyword.
     * @param text Step that we want to check.
     * @param aktor Actor we want to check our text with.
     * @return true if text begins with an actor, false otherwise.
     */
    public boolean nieZaczynaSieOdAktora(String text, String aktor)
    {
        logger.info("nieZaczynaSieOdAktora Begin");

        //musze uwzglednic ze moze byc slowo kluczowe
        Keyword k = new Keyword();
        String org=text;
        //ignoruje wszystkie slowa kluczowe
        int i;
        for(i=0; i< k.getKeywords().length; i++)
        {
            //jesli krok zawiera slowo kluczowe, to usuwam wszystkie wystapienia danego slowa kluczowego
            if(org.contains(k.getKeywords()[i]))org=org.replaceAll(k.getKeywords()[i],"");
        }
        //usuwam spacje z przodu
        i=0;
        while(org.charAt(i)==' '){org=org.replaceFirst(" ","");}
        //org=org.replaceAll(" ","");
        //sprawdzam ,czy krok zaczyna sie od aktora

        if(org.startsWith(aktor)){
            logger.info("nieZaczynaSieOdAktora End False");
            return false;}//success
        else{
            logger.info("nieZaczynaSieOdAktora End True");
            return true;//porazka, krok nie zaczyna sie od aktora
        }

    }
}

