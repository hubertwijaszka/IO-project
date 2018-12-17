package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ScenarioSerwis.class);

    //metody
    //ile jest wszystkich krokow   p[2]
    //ile jest slow kluczowych    p[0]
    //ile jest krokow ze slowami kluczowymi p
    //głebokośc kroku p[3]


    /**
     * Method creates a {@link Wynik} object and iterates
     * over the Steps to get data about them.
     * @param obiekt Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return Wynik object that stores collected data.
     */

    public Wynik ileJest(Scenario obiekt,int glebokosc,int ktory,String fraza)
    {
        logger.info("ileJest Begin");

        int[] p={0,0,0,0};int i,siz;
        String[] nr={"1","",""};
        nr[2]=fraza;//szukana fraza
        //tworzenie listy list
        List<List<String>> w = new ArrayList<List<String>>(3);
        for(i=0;i<3;i++)
        {
            List<String> a=new ArrayList<>();
            w.add(a);
        }
        Wynik wynik=new Wynik();
        siz=obiekt.getSteps().length;
        //przejdz po WSZYSTKICH krokach scenariusza
        for(i=0;i<siz;i++)
        {
            if(ktory>=0 && ktory<siz)   //wyspecyfikowano, ktory krok ma zostac poddany analizie
            {
                if(i==ktory){
                    nr[0]=Integer.toString(i+1);nr[1]="";
                    obiekt.przeszukiwanie(obiekt.getSteps()[i],p,w,glebokosc,0,nr);
                }
            }
            else {                      //wszystkie kroki maja byc sprawdzone
                nr[0] = Integer.toString(i + 1);nr[1] = "";
                obiekt.przeszukiwanie(obiekt.getSteps()[i], p, w, glebokosc, 0, nr);
            }
        }
        wynik.setRezultat(p);
        wynik.setListy(w);
        logger.info("ileJest End");
        return wynik;
    }

    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return Maximum Scenario Depth (constraint: variable "glebokosc" is greater than returned value, variable "ktory" - which step will be taken into account)
     */
    public String maxGlebokosc(Scenario scenario,int glebokosc,int ktory,String fraza){
        return Integer.toString(ileJest(scenario,glebokosc,ktory,fraza).getRezultat()[3]);
    }
    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return Count of Steps (constraints: variable "glebokosc", variable "ktory" - which step will be taken into account)
     */
    public String ileKrokow(Scenario scenario,int glebokosc,int ktory,String fraza){
        return Integer.toString(ileJest(scenario,glebokosc,ktory,fraza).getRezultat()[2]);
    }
    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return Count of Steps with Keywords (constraints: variable "glebokosc", variable "ktory" - which step will be taken into account)
     */
    public String ileKrokowMaKeyword(Scenario scenario,int glebokosc,int ktory,String fraza){
        return Integer.toString(ileJest(scenario,glebokosc,ktory,fraza).getRezultat()[1]);
    }
    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return Count of Keywords (constraints: variable "glebokosc", variable "ktory" - which step will be taken into account)
     */
    public String ileSlowKluczowych(Scenario scenario,int glebokosc,int ktory,String fraza){
        return Integer.toString(ileJest(scenario,glebokosc,ktory,fraza).getRezultat()[0]);
    }
    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return List of Steps that do not start with keywords
     * (constraints: variable "glebokosc", variable "ktory" - which step will be taken into account)
     */
    public List<String> bledneKroki(Scenario scenario,int glebokosc,int ktory,String fraza){
        return ileJest(scenario,glebokosc,ktory,fraza).getListy().get(0);
    }
    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return List of Steps (constraints: variable "glebokosc", variable "ktory" - which step will be taken into account)
     */
    public List<String>scenariuszDoPoziomu(Scenario scenario,int glebokosc,int ktory,String fraza){
        return ileJest(scenario,glebokosc,ktory,fraza).getListy().get(1);
    }
    /**
     * @param scenario Scenario to get information from
     * @param glebokosc Level of depth to which scenario is parsed
     * @param ktory number of step which is to be parsed
     * @param fraza phrase which will be searched for in scenario
     * @return List of Steps that contain "fraza"(constraints: variable "glebokosc", variable "ktory" - which step will be taken into account)
     */
    public List<String>krokMaFraze(Scenario scenario,int glebokosc,int ktory,String fraza){
        return ileJest(scenario,glebokosc,ktory,fraza).getListy().get(2);
    }
}
