package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;


import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/scenario")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private ScenarioSerwis scenarioSerwis = new ScenarioSerwis();
/*

    @RequestMapping( method = RequestMethod.GET, produces = "application/json")
    public String get( @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {


        // do the transformation, you should run your logic here, below just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        return transformer.transform(text);
    }
*/

    @RequestMapping(value = "ileKrokow", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String ileKrokow(@RequestBody Scenario scenario) {
        return scenarioSerwis.ileKrokow(scenario);
    }

    @RequestMapping(value = "bledneKroki", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<String> bledneKroki(@RequestBody Scenario scenario) {
        return scenarioSerwis.bledneKroki(scenario);
    }

    @RequestMapping(value = "ileSlowKluczowych", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String ileSlowKluczowych(@RequestBody Scenario scenario) {
        return scenarioSerwis.ileSlowKluczowych(scenario);
    }
}