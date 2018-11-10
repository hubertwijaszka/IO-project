package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private static ScenarioSerwis scenarioSerwis;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        return transformer.transform(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                       @RequestBody Scenario obiekt
            /*String[] transforms*/) {

        // log the parameters
        logger.debug(text);
      //  logger.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example

        //inicjalizacja wartosci zmiennych globalnych
        ZmienneGlobalne.liczba_keywords=0;
        ZmienneGlobalne.ile_steps_ma_keywords=0;
        ZmienneGlobalne.ile_krokow=0;
        ZmienneGlobalne.wadliwekroki= new ArrayList<String>();

        System.out.println("Tytuł: "+obiekt.title);
        System.out.print("Aktorzy: ");
        for(j=0;j<obiekt.actors.length;j++)
        {
            System.out.print(obiekt.actors[j]+ ", ");
        }
        System.out.println();
        System.out.println("Aktor systemowy: "+obiekt.systemActor);


        //przejdz po WSZYSTKICH krokach scenariusza
        for(i=0;i<obiekt.steps.length;i++)
        {
            obiekt.przeszukiwanie(obiekt.steps[i]);
        }

        System.out.println("Statystyki: ");
        System.out.println("Liczba krokow: "+ ZmienneGlobalne.ile_krokow);
        System.out.println("Liczba podkrokow: "+(ZmienneGlobalne.ile_krokow-obiekt.steps.length));
        System.out.println("Liczba krokow zawierająca slowo kluczowe: "+ ZmienneGlobalne.ile_steps_ma_keywords);
        System.out.println("Liczba slow kluczowych: "+ ZmienneGlobalne.liczba_keywords);

        System.out.println("Wadliwe kroki dla aktora systemowego = "+obiekt.systemActor);
        for(i=0; i< ZmienneGlobalne.wadliwekroki.size(); i++)
        {
            System.out.print(ZmienneGlobalne.wadliwekroki.get(i)+", ");
        }
        ZmienneGlobalne.wadliwekroki.clear();

        return "ok ;)"; //jakis string
    }
}


