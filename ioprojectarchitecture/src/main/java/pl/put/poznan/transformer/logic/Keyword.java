package pl.put.poznan.transformer.logic;

/**
 * Class containing list of keywords that we search for in Scenario
 */

public class Keyword {

    private static String[] keywords={"IF","ELSE","FOR EACH"};

    /**
     * @return list of keywords
     */
    public static String[] getKeywords() {
        return keywords;
    }
}
