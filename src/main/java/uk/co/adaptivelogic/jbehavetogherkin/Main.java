package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.PrettyFormatter;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Step;
import org.apache.commons.lang.StringUtils;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.parsers.RegexStoryParser;
import org.apache.commons.io.IOUtils;

import static java.util.Collections.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputStreamReader jBehaveIn = new InputStreamReader(System.in, Charset.defaultCharset());
        OutputStreamWriter gherkinOut = new OutputStreamWriter(System.out, Charset.defaultCharset());

        translate(jBehaveIn, gherkinOut);
    }

    private static void translate(InputStreamReader jBehaveIn, OutputStreamWriter gherkinOut) {
        Story jbehave = readJBehave(jBehaveIn);
        FeatureWrapper gherkin = translate(jbehave);
        writeGherkin(gherkinOut, gherkin);
    }

    private static void writeGherkin(OutputStreamWriter gherkinOut, FeatureWrapper gherkin) {
        Formatter formatter = new PrettyFormatter(gherkinOut, true, false);
        gherkin.replay(formatter);

        formatter.eof();
        formatter.close();
    }

    private static FeatureWrapper translate(Story story) {
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
            Feature feature = new Feature(EMPTY_LIST, EMPTY_LIST, "Feature", "", description.toString(), 1, "");
            featureWrapper.setFeature(feature);
        }

        List<ScenarioWrapper> scenarioWrappers = new ArrayList<ScenarioWrapper>();
        for (Scenario scenario : story.getScenarios()) {
            ScenarioWrapper scenarioWrapper = new ScenarioWrapper();
            gherkin.formatter.model.Scenario gherkinScenario = new gherkin.formatter.model.Scenario(EMPTY_LIST, EMPTY_LIST, "Scenario", "", "", 2, null);
            scenarioWrapper.setScenario(gherkinScenario);

            List<Step> steps = new ArrayList<Step>();
            for (String step : scenario.getSteps()) {
                Step gherkinStep = translateStep(step);
                steps.add(gherkinStep);
            }
            scenarioWrapper.setSteps(steps);
            scenarioWrappers.add(scenarioWrapper);
        }
        featureWrapper.setScenarios(scenarioWrappers);

        return featureWrapper;
    }

    private static Step translateStep(String step) {
        String[] stepParts = StringUtils.split(step, " ", 2);
        return new Step(EMPTY_LIST, stepParts[0], " " + stepParts[1], 1, EMPTY_LIST, null);
    }

    private static Story readJBehave(InputStreamReader jBehaveIn) {
        try {
            RegexStoryParser storyParser = new RegexStoryParser();
            return storyParser.parseStory(IOUtils.toString(jBehaveIn));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
