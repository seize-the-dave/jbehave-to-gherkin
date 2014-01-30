package uk.co.adaptivelogic.jbehavetogherkin;

import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.parsers.RegexStoryParser;

import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        InputStreamReader jBehaveIn = new InputStreamReader(System.in, Charset.defaultCharset());
        OutputStreamWriter gherkinOut = new OutputStreamWriter(System.out, Charset.defaultCharset());

        translate(jBehaveIn, gherkinOut);
    }

    private static void translate(InputStreamReader jBehaveIn, OutputStreamWriter gherkinOut) {
        Story jbehave = readJBehave(jBehaveIn);
        String gherkin = translate(jbehave);
        writeGherkin(gherkinOut, gherkin);
    }

    private static void writeGherkin(OutputStreamWriter gherkinOut, String gherkin) {
        try {
            gherkinOut.write(gherkin);
            gherkinOut.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static String translate(Story story) {
        StringBuilder builder = new StringBuilder();
        for (Scenario scenario : story.getScenarios()) {
            for (String step : scenario.getSteps()) {
                builder.append(step);
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    private static Story readJBehave(InputStreamReader jBehaveIn) {
        BufferedReader bufferedReader = new BufferedReader(jBehaveIn);
        StringBuilder jbehaveBuilder = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jbehaveBuilder.append(line);
                jbehaveBuilder.append(System.lineSeparator());
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        RegexStoryParser storyParser = new RegexStoryParser();
        return storyParser.parseStory(jbehaveBuilder.toString());
    }
}
