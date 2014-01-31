package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.PrettyFormatter;
import org.jbehave.core.model.Story;
import org.jbehave.core.parsers.RegexStoryParser;
import org.apache.commons.io.IOUtils;

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
        Translator translator = new Translator();
        FeatureWrapper gherkin = translator.translate(jbehave);
        writeGherkin(gherkinOut, gherkin);
    }

    private static void writeGherkin(OutputStreamWriter gherkinOut, FeatureWrapper gherkin) {
        Formatter formatter = new PrettyFormatter(gherkinOut, true, false);
        gherkin.replay(formatter);

        formatter.eof();
        formatter.close();
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
