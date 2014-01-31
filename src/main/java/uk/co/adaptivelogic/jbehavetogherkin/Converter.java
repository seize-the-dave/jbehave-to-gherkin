package uk.co.adaptivelogic.jbehavetogherkin;

import java.io.*;
import java.nio.charset.Charset;

public class Converter {
    private final JBehaveReader jbehave;
    private final GherkinWriter gherkin;
    private final Translator translator;

    public Converter(JBehaveReader jbebave, GherkinWriter gherkin, Translator translator) {
        this.jbehave = jbebave;
        this.gherkin = gherkin;
        this.translator = translator;
    }

    public void convert() throws IOException {
        gherkin.write(translator.translate(jbehave.read()));
    }

    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in, Charset.defaultCharset());
        OutputStreamWriter out = new OutputStreamWriter(System.out, Charset.defaultCharset());

        Converter converter = new Converter(new JBehaveReader(in), new GherkinWriter(out), new Translator());
        try {
            converter.convert();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

}
