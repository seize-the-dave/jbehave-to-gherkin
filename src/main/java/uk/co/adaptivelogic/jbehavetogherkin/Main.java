package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.PrettyFormatter;
import gherkin.formatter.model.Step;
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
        List<Step> gherkin = translate(jbehave);
        writeGherkin(gherkinOut, gherkin);
    }

    private static void writeGherkin(OutputStreamWriter gherkinOut, List<Step> gherkin) {
        Formatter formatter = new PrettyFormatter(gherkinOut, true, false);
        for (Step step : gherkin) {
            formatter.step(step);
        }
        formatter.eof();
        formatter.close();
    }

    private static List<Step> translate(Story story) {
        List<Step> steps = new ArrayList<Step>();
        for (Scenario scenario : story.getScenarios()) {
            for (String step : scenario.getSteps()) {
                Step gherkinStep = translateStep(step);
                steps.add(gherkinStep);
            }
        }
        return steps;
    }

    private static Step translateStep(String step) {
        return new Step(EMPTY_LIST, "", step, 1, EMPTY_LIST, null);
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
