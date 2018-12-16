package pl.put.poznan.transformer.rest;

import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TextTransformerControllerTest {

    TextTransformerController scenarioSerwis;
    Scenario scenario;
    @Before
    public void setUp() throws Exception {
        String[] strings;
        strings=new String[3];
        strings[0]="JA";
        strings[1]="TY";
        strings[2]="ON";
        this.scenarioSerwis=new TextTransformerController();
        Step step=new Step();
        step.setText("FOR EACHwlej jajka do IF rondelka");
        Step steps[]=new Step[1];
        Step steps2[]=new Step[0];
        Step step2=new Step();
        step2.setText("roztop ELSEmaslo klarowane");
        step2.setSubsteps(steps2);
        steps[0]=step2;
        step.setSubsteps(steps);
        Scenario scenario=new Scenario();
        scenario.setActors(strings);
        scenario.setSteps(steps);
        this.scenario=scenario;
    }

    @Test
    public void ileJest() {
        assertEquals("1",scenarioSerwis.ileKrokow(scenario,1,-1));
    }

    @Test
    public void maxGlebokosc() {
        assertEquals("1",scenarioSerwis.maxGlebokosc(scenario,1,1));
    }

    @Test
    public void ileKrokow() {
        assertEquals("1",scenarioSerwis.ileKrokow(scenario,1,1));
    }

    @Test
    public void ileKrokowMaKeyword() {
        assertEquals("1",scenarioSerwis.ileKrokowMaKeyword(scenario,1,1));
    }

    @Test
    public void ileSlowKluczowych() {
        assertEquals("1",scenarioSerwis.ileSlowKluczowych(scenario,1,1));
    }

    @Test
    public void bledneKroki() {
        ArrayList arrayList=new ArrayList<String>();
        arrayList.add("roztop ELSEmaslo klarowane");
        assertEquals(arrayList,scenarioSerwis.bledneKroki(scenario,1,1));

    }

    @Test
    public void scenariuszDoPoziomu() {
        ArrayList arrayList=new ArrayList<String>();
        // arrayList.add("roztop ELSEmaslo klarowane");
        assertEquals(arrayList,scenarioSerwis.scenariuszDoPoziomu(scenario,0,0));
    }

    @Test
    public void krokMaFraze() {
        ArrayList arrayList=new ArrayList<String>();
        // arrayList.add("roztop ELSEmaslo klarowane");
        assertEquals(arrayList,scenarioSerwis.krokMaFraze(scenario,0,0,"costam"));
    }
}