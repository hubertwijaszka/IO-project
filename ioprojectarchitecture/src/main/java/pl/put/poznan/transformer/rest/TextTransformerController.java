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
    private ScenarioSerwis scenarioSerwis;

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

    @RequestMapping(method = RequestMethod.POST, produces = "application/json",consumes = "application/json")
    public String post(@PathVariable String text,
                       @RequestBody Scenario obiekt
            /*String[] transforms*/) {

        // log the parameters
        logger.debug(text);
      //  logger.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example



        int i,j;
        Wynik w;
        ScenarioSerwis s = new ScenarioSerwis();
        System.out.printf("Tytuł: %s%n",obiekt.getTitle());
        System.out.print("Aktorzy: ");
        for(j=0;j<obiekt.getActors().length;j++)
        {
            System.out.print(obiekt.getActors()[j]+ ", ");
        }
        System.out.println();
        System.out.println("Aktor systemowy: "+obiekt.getSystemActor());

        //przejdz po WSZYSTKICH krokach scenariusza
        w=s.ileJest(obiekt);

        System.out.println("Statystyki: ");
        System.out.println("Liczba krokow: "+ w.getRezultat()[2]);
        System.out.println("Liczba podkrokow: "+(w.getRezultat()[2]-obiekt.getSteps().length));
        System.out.println("Liczba krokow zawierająca slowo kluczowe: "+ w.getRezultat()[1]);
        System.out.println("Liczba slow kluczowych: "+ w.getRezultat()[0]);

        System.out.println("Wadliwe kroki dla aktora systemowego = "+obiekt.getSystemActor());
        for(i=0; i< w.getWadliweKroki().size(); i++)
        {
            System.out.print(w.getWadliweKroki().get(i)+", ");
        }
        return "ok ;)"; //jakis string
    }
}


