package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.Step;

import java.util.ArrayList;
import java.util.List;

public class ScenarioWrapper {
    private List<Step> steps = new ArrayList<Step>();
    private Scenario scenario = new Scenario(null, null, null, null, null, null, null) {
        public void replay(Formatter formatter) {
            // Do nothing
        }
    };

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void replay(Formatter formatter) {
        scenario.replay(formatter);
        for (Step step : steps) {
            step.replay(formatter);
        }
    }
}
