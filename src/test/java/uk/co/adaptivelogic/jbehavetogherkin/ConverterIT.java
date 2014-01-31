package uk.co.adaptivelogic.jbehavetogherkin;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.Charset;

public class ConverterIT {
    private static final String INDENT = "    ";

    @Test
    public void shouldOutputNothingForEmptyInput() {
        System.setIn(new ByteArrayInputStream(new byte[]{}));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Converter.main(new String[]{});

        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, is(""));
    }

    @Test
    public void shouldOutputGivenForGivenStepInput() {
        String jbehave = "Given I am a step";
        System.setIn(new ByteArrayInputStream(jbehave.getBytes(Charset.defaultCharset())));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Converter.main(new String[]{});

        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, equalTo("\n  Scenario: \n" +INDENT + "Given I am a step\n"));
    }

    @Test
    public void shouldOutputMultipleStepsForMultipleStepInput() {
        String jbehave = "Given I am a step" + System.lineSeparator() + "When I am another step";
        System.setIn(new ByteArrayInputStream(jbehave.getBytes(Charset.defaultCharset())));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Converter.main(new String[]{});

        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, equalTo("\n  Scenario: \n" + INDENT + "Given I am a step\n" + INDENT + "When I am another step\n"));
    }

    @Test
    public void shouldTranslateNarrativeToFeature() throws FileNotFoundException, IOException {
        System.setIn(getClass().getResourceAsStream("/narrative.story"));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Converter.main(new String[]{});
        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, equalTo(IOUtils.toString(getClass().getResourceAsStream("/narrative.feature"))));
    }
}
