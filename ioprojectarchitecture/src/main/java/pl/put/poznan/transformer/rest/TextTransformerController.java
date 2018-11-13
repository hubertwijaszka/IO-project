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
    public String ileKrokow(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false , defaultValue = "-1") Integer deepLevel,
                            @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory ) {
        return scenarioSerwis.ileKrokow(scenario,deepLevel,ktory-1,"");
    }

    @RequestMapping(value = "ileKrokowMaKeyword", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String ileKrokowMaKeyword(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false,defaultValue = "-1") Integer deepLevel,
                                     @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory)
    {
        return scenarioSerwis.ileKrokowMaKeyword(scenario,deepLevel,ktory-1,"");
    }

    @RequestMapping(value = "bledneKroki", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<String> bledneKroki(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false,defaultValue = "-1") Integer deepLevel,
                                    @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory) {
        return scenarioSerwis.bledneKroki(scenario,deepLevel,ktory-1,"");
    }

    @RequestMapping(value = "scenariuszDoPoziomu", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<String>scenariuszDoPoziomu(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false,defaultValue = "-1") Integer deepLevel,
                                           @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory) {
        return scenarioSerwis.scenariuszDoPoziomu(scenario,deepLevel,ktory-1,"");
    }

    @RequestMapping(value = "ileSlowKluczowych", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String ileSlowKluczowych(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false,defaultValue = "-1") Integer deepLevel,
                                    @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory) {
        return scenarioSerwis.ileSlowKluczowych(scenario,deepLevel,ktory-1,"");
    }

    @RequestMapping(value = "maxGlebokosc", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String maxGlebokosc(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false,defaultValue = "-1") Integer deepLevel,
                               @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory) {
        return scenarioSerwis.maxGlebokosc(scenario,deepLevel,ktory-1,"");
    }

    @RequestMapping(value = "krokMaFraze", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<String>krokMaFraze(@RequestBody Scenario scenario,@RequestParam(value = "deepLevel", required=false,defaultValue = "-1") Integer deepLevel,
                                   @RequestParam(value = "ktory", required=false , defaultValue = "-1") Integer ktory,
                                   @RequestParam(value = "fraza", required=false , defaultValue = "") String fraza) {
        return scenarioSerwis.krokMaFraze(scenario,deepLevel,ktory-1,fraza);
    }
}