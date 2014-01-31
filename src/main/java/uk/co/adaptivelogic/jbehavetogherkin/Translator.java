package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Step;
import org.apache.commons.lang.StringUtils;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;

import static java.util.Collections.EMPTY_LIST;

import java.util.ArrayList;
import java.util.List;

public class Translator {
    public List<ScenarioWrapper> translateScenarios(List<Scenario> scenarios) {
        List<ScenarioWrapper> scenarioWrappers = new ArrayList<ScenarioWrapper>();
        for (Scenario scenario : scenarios) {
            scenarioWrappers.add(translate(scenario));
        }
        return scenarioWrappers;
    }

    private ScenarioWrapper translate(Scenario scenario) {
        ScenarioWrapper scenarioWrapper = new ScenarioWrapper();
        scenarioWrapper.setScenario(new gherkin.formatter.model.Scenario(EMPTY_LIST, EMPTY_LIST, "Scenario", scenario.getTitle(), "", 2, null));
        scenarioWrapper.setSteps(translateSteps(scenario.getSteps()));
        return scenarioWrapper;
    }

    private List<Step> translateSteps(List<String> jBehaveSteps) {
        List<Step> steps = new ArrayList<Step>();
        for (String step : jBehaveSteps) {
            steps.add(translate(step));
        }
        return steps;
    }

    public FeatureWrapper translate(Story story) {
        FeatureWrapper featureWrapper = new FeatureWrapper();
        if (!story.getNarrative().asA().isEmpty()) {
            StringBuilder description = new StringBuilder();
            if (!story.getNarrative().inOrderTo().isEmpty()) {
                description.append("In order to ");
                description.append(story.getNarrative().inOrderTo());
                description.append('\n');
                description.append("As a ");
                description.append(story.getNarrative().asA());
                description.append('\n');
                description.append("I want to ");
                description.append(story.getNarrative().iWantTo());
            } else {
                description.append("As a ");
                description.append(story.getNarrative().asA());
                description.append('\n');
                description.append("I want to ");
                description.append(story.getNarrative().iWantTo());
                description.append('\n');
                description.append("So that ");
                description.append(story.getNarrative().soThat());
            }
            Feature feature = new Feature(EMPTY_LIST, EMPTY_LIST, "Feature", story.getDescription().asString(), description.toString(), 1, "");
            featureWrapper.setFeature(feature);
        }

        featureWrapper.setScenarios(translateScenarios(story.getScenarios()));

        return featureWrapper;
    }

    public Step translate(String step) {
        String[] stepParts = StringUtils.split(step, " ", 2);
        return new Step(EMPTY_LIST, stepParts[0], " " + stepParts[1], 1, EMPTY_LIST, null);
    }
}