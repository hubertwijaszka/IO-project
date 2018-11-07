package pl.put.poznan.transformer.logic;

import java.util.List;

public class ZmienneGlobalne {
    public static String[] keywords={"IF","ELSE","FOR EACH"};
    public static int liczba_keywords; //ogolna liczba slow kluczowych
    public static int ile_steps_ma_keywords;// liczba krokow zawierajacych co najmniej jedno slowo kluczowe
    public static int ile_krokow;// ile jest krokow i podkrokow w calym scenariuszu
    public static List<String> wadliwekroki; // lista zawierajca wadliwe kroki (tzn. takie ktore nie zaczynaja sie od aktora systemowego -
                                             // slowa kluczowe ignoruje)
}
