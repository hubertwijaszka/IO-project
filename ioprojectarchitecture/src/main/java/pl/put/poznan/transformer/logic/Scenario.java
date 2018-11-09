package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String title;
    private String actors[];
    private String systemActor;
    private Step steps[];

   // public Scenario(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String getSystemActor() {
        return systemActor;
    }

    public void setSystemActor(String systemActor) {
        this.systemActor = systemActor;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    public int przeszukiwanie(Step krok,int[] p,List<String> w) // metoda sprawdza ile elementow w jednym kroku zawiera/zaczyna się od
                                          // słowa kluczowego
                                            //param : 0 -> ile krokow
    {
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
        return 0;
    }

    public int zaczynaSieOdSlowaKlucz(String text) {
            //Sprawdzam, czy krok zaczyna się od słowa kluczowego
            Keyword k = new Keyword();
            int wyn=0;int i;
            for (i=0; i< k.getKeywords().length; i++)
            {
                if(text.startsWith(k.getKeywords()[i])){wyn=1;break;}
            }
            return wyn;
    }

    public int zawieraSlowoKluczowe(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        Keyword k = new Keyword();
        int wyn=0,i;
        for(i=0; i< k.getKeywords().length; i++)
        {
            if (text.contains(k.getKeywords()[i])){wyn=1;break;}
        }
        return wyn;
    }

    public int ileSlowKluczowych(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        Keyword k = new Keyword();
        int wyn=0,i;
        for(i=0; i< k.getKeywords().length; i++)
        {
            if (text.contains(k.getKeywords()[i])){wyn++;}
        }
        return wyn;
    }

    public boolean nieZaczynaSieOdAktora(String text, String aktor)
    {
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
        if(org.startsWith(aktor))return false;//success
        else return true;//porazka, krok nie zaczyna sie od aktora
    }
}

