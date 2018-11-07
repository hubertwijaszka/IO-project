package pl.put.poznan.transformer.logic;

public class Scenario {
    public String title;
    public String actors[];
    public String systemActor;
    public Step steps[];


    public int przeszukiwanie(Step krok) // metoda sprawdza ile elementow w jednym kroku zawiera/zaczyna się od
                                          // słowa kluczowego
    {
        //przeszukiwanie grafu
        //mam dostęp do tekstu
        //System.out.println(krok.text);
        //sprawdzam, czy krok jest wadliwy ( jest taki, gdy nie zaczyna się od aktora systemowego )
        if(nieZaczynaSieOdAktora(krok.text,systemActor)) ZmienneGlobalne.wadliwekroki.add(krok.text);
        //ile jest ogolnie slow kluczowych
        ZmienneGlobalne.liczba_keywords+= ileSlowKluczowych(krok.text);
        //ile krokow zawiera choc jedno slowo kluczowe
        ZmienneGlobalne.ile_steps_ma_keywords+= zawieraSlowoKluczowe(krok.text);
        //jesli ta funkcja zostala wywolana tzn. ze wystapil kolejny krok
        ZmienneGlobalne.ile_krokow++;
        // podliczenie wszystkich krokow wykona sie poprawnie jesli w mainie() (czy gdziekolwiek indziej)
        // wywolane zostanie:
        //                      for(i=0;i<obiekt.steps.length;i++)
        //                      {
        //                          obiekt.przeszukiwanie(obiekt.steps[i]);
        //                      }
        int i;
        for(i=0;i<krok.substeps.length;i++)
        {
            przeszukiwanie(krok.substeps[i]);
        }
        return 0;
    }

    public int zaczynaSieOdSlowaKlucz(String text) {
            //Sprawdzam, czy krok zaczyna się od słowa kluczowego
            int wyn=0;int i;
            for (i=0; i< ZmienneGlobalne.keywords.length; i++)
            {
                if(text.startsWith(ZmienneGlobalne.keywords[i])){wyn=1;break;}
            }
            return wyn;
    }

    public int zawieraSlowoKluczowe(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        int wyn=0,i;
        for(i=0; i< ZmienneGlobalne.keywords.length; i++)
        {
            if (text.contains(ZmienneGlobalne.keywords[i])){wyn=1;break;}
        }
        return wyn;
    }

    public int ileSlowKluczowych(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        int wyn=0,i;
        for(i=0; i< ZmienneGlobalne.keywords.length; i++)
        {
            if (text.contains(ZmienneGlobalne.keywords[i])){wyn++;}
        }
        return wyn;
    }

    public boolean nieZaczynaSieOdAktora(String text, String aktor)
    {
        //musze uwzglednic ze moze byc slowo kluczowe
        String org=text;
        //ignoruje wszystkie slowa kluczowe
        int i;
        for(i=0; i< ZmienneGlobalne.keywords.length; i++)
        {
            //jesli krok zawiera slowo kluczowe, to usuwam wszystkie wystapienia danego slowa kluczowego
            if(org.contains(ZmienneGlobalne.keywords[i]))org=org.replaceAll(ZmienneGlobalne.keywords[i],"");
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

