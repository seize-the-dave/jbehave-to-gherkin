package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.model.*;
import org.apache.commons.lang.StringUtils;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if (scenario.getExamplesTable().getHeaders().isEmpty()) {
            scenarioWrapper.setScenario(new gherkin.formatter.model.Scenario(noComments(), noTags(), "Scenario", scenario.getTitle(), "", 2, null));
        } else {
            scenarioWrapper.setScenarioOutline(new ScenarioOutline(noComments(), noTags(), "Scenario Outline", scenario.getTitle(), "", 2, null));
            scenarioWrapper.setExamples(translate(scenario.getExamplesTable()));
        }
        scenarioWrapper.setSteps(translateSteps(scenario.getSteps()));
        return scenarioWrapper;
    }

    private List<Tag> noTags() {
        return emptyList();
    }

    private List<Comment> noComments() {
        return emptyList();
    }

    private Examples translate(ExamplesTable examples) {
        List<ExamplesTableRow> rows = new ArrayList<ExamplesTableRow>();
        rows.add(new ExamplesTableRow(noComments(), examples.getHeaders(), 0, ""));
        for (Map<String, String> row : examples.getRows()) {
            List<String> jBehaveRow = new ArrayList<String>();
            for (String header : examples.getHeaders()) {
                jBehaveRow.add(row.get(header));
            }
            rows.add(new ExamplesTableRow(noComments(), jBehaveRow, 0, ""));
        }
        return new Examples(noComments(), noTags(), "Examples", "", "", 1, "", rows);
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
            Feature feature = new Feature(noComments(), noTags(), "Feature", story.getDescription().asString(), description.toString(), 1, "");
            featureWrapper.setFeature(feature);
        }

        featureWrapper.setScenarios(translateScenarios(story.getScenarios()));

        return featureWrapper;
    }

    public Step translate(String step) {
        String[] stepParts = StringUtils.split(step, " ", 2);
        return new Step(noComments(), stepParts[0], " " + stepParts[1], 1, noTableData(), null);
    }

    private List<DataTableRow> noTableData() {
        return emptyList();
    }
}