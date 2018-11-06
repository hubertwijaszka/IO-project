package pl.put.poznan.transformer.logic;

public class scenario {
    public String title;
    public String actors[];
    public String systemActor;
    public steps steps[];


    public int przeszukiwanie(steps krok) // metoda sprawdza ile elementow w jednym kroku zawiera/zaczyna się od
                                          // słowa kluczowego
    {
        //przeszukiwanie grafu
        //mam dostęp do tekstu
        //System.out.println(krok.text);
        //sprawdzam, czy krok jest wadliwy ( jest taki, gdy nie zaczyna się od aktora systemowego )
        if(nie_zaczyna_sie_od_aktora(krok.text,systemActor))zmienne_globalne.wadliwekroki.add(krok.text);
        //ile jest ogolnie slow kluczowych
        zmienne_globalne.liczba_keywords+=ile_slowkluczowych(krok.text);
        //ile krokow zawiera choc jedno slowo kluczowe
        zmienne_globalne.ile_steps_ma_keywords+=zawiera_slowokluczowe(krok.text);
        //jesli ta funkcja zostala wywolana tzn. ze wystapil kolejny krok
        zmienne_globalne.ile_krokow++;
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

    public int zaczyna_sie_od_slowaklucz(String text) {
            //Sprawdzam, czy krok zaczyna się od słowa kluczowego
            int wyn=0;int i;
            for (i=0;i<zmienne_globalne.keywords.length;i++)
            {
                if(text.startsWith(zmienne_globalne.keywords[i])){wyn=1;break;}
            }
            return wyn;
    }

    public int zawiera_slowokluczowe(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        int wyn=0,i;
        for(i=0;i<zmienne_globalne.keywords.length;i++)
        {
            if (text.contains(zmienne_globalne.keywords[i])){wyn=1;break;}
        }
        return wyn;
    }

    public int ile_slowkluczowych(String text){
        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        int wyn=0,i;
        for(i=0;i<zmienne_globalne.keywords.length;i++)
        {
            if (text.contains(zmienne_globalne.keywords[i])){wyn++;}
        }
        return wyn;
    }

    public boolean nie_zaczyna_sie_od_aktora(String text, String aktor)
    {
        //musze uwzglednic ze moze byc slowo kluczowe
        String org=text;
        //ignoruje wszystkie slowa kluczowe
        int i;
        for(i=0;i<zmienne_globalne.keywords.length;i++)
        {
            //jesli krok zawiera slowo kluczowe, to usuwam wszystkie wystapienia danego slowa kluczowego
            if(org.contains(zmienne_globalne.keywords[i]))org=org.replaceAll(zmienne_globalne.keywords[i],"");
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

