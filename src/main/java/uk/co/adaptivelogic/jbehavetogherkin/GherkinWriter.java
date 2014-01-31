package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.PrettyFormatter;

import java.io.Writer;

public class GherkinWriter {
    private final Writer writer;

    public GherkinWriter(Writer writer) {
        this.writer = writer;
    }

    public void write(FeatureWrapper gherkin) {
        Formatter formatter = new PrettyFormatter(writer, true, false);
        gherkin.replay(formatter);

        formatter.eof();
        formatter.close();
    }
}