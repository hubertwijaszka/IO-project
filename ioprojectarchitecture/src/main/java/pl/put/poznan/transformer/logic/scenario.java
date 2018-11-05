package pl.put.poznan.transformer.logic;

public class scenario {
    public String title;
    public String actors[];
    public String systemActor;
    public steps steps[];

    public int[] slowa_kluczowe(steps krok) // metoda sprawdza ile elementow w jednym kroku zawiera/zaczyna się od
                                          // słowa kluczowego
    {
        String[] keywords={"IF","ELSE","FOR EACH"};
        int wyn1=0,wyn2=0,i,j;boolean tak =false;
        //wyn1 -> ile krokow zawiera w sobie slowa kluczowe
        //wyn2 -> ile jest ogolnie slow kluczowych
        //sprawdzam text i jego podkroki

        //czy krok.text zawiera jakiekolwiek slowo kluczowe
        for(i=0;i<keywords.length;i++)
        {
            if (krok.text.contains(keywords[i])){tak=true;wyn2++;}
        }
        if(tak)wyn1++;//jesli zawiera to dobrze
        //sprawdzam podkroki

        //czy krok.substeps[j] zawiera jakiekolwiek slowo kluczowe
        for(j=0;j<krok.substeps.length;j++) {
            tak=false;
            for (i = 0; i < keywords.length; i++) {
                if (krok.substeps[j].text.contains(keywords[i])) {tak = true;wyn2++;}
            }
            if(tak)wyn1++;
        }

        int wyn[] = {wyn1,wyn2};
        return wyn;
    }

    public boolean zaczyna_sie_od_slowaklucz(String krok) {
            //Sprawdzam, czy krok zaczyna się od słowa kluczowego
            String[] keywords={"IF","ELSE","FOR EACH"};
            boolean wyn=false;int i;
            for (i=0;i<keywords.length;i++)
            {
                if(krok.startsWith(keywords[i])){wyn=true;break;}
            }
            return wyn;
    }


}
