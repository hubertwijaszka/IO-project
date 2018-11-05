package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.scenario;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

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
                       @RequestBody scenario obiekt
            /*String[] transforms*/) {

        // log the parameters
        logger.debug(text);
      //  logger.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example
  //      TextTransformer transformer = new TextTransformer(transforms);

        //System.out.print(obiekt.kroki[0].nazwakroku);
        //System.out.print(obiekt.kroki[1].nazwakroku);
        //return transformer.transform(text);
        int i,j;
        System.out.println("Tytuł: "+obiekt.title);
        System.out.print("Aktorzy: ");
        for(j=0;j<obiekt.actors.length;j++)
        {
            System.out.print(obiekt.actors[j]+ ", ");
        }
        System.out.println();
        System.out.println("Aktor systemowy: "+obiekt.systemActor);

        for (i=0;i<obiekt.steps.length;i++)
        {
            System.out.print(obiekt.steps[i].text+" : ");
            for(j=0;j<obiekt.steps[i].substeps.length;j++)
            {
                System.out.print(obiekt.steps[i].substeps[j].text+",");
            }
            System.out.println();
        }

        int[] sumy={0,0},pom;
        for(i=0;i<obiekt.steps.length;i++)
        {
            pom=obiekt.slowa_kluczowe(obiekt.steps[i]);
            sumy[0]+=pom[0];
            sumy[1]+=pom[1];
        }
        System.out.println(" W scenariuszu "+sumy[0]+" krokow i podkrokow zawiera slowa kluczowe");
        System.out.println(" W scenariuszu jest "+sumy[1]+" slow kluczowych");


        System.out.println(obiekt.zaczyna_sie_od_slowaklucz(obiekt.steps[0].substeps[0].text));
        return "ok ;)"; //jakis string
    }



}


