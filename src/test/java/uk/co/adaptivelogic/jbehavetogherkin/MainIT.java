package uk.co.adaptivelogic.jbehavetogherkin;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class MainIT {
    @Test
    public void shouldOutputNothingForEmptyInput() {
        System.setIn(new ByteArrayInputStream(new byte[]{}));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Main.main(new String[]{});

        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, is(""));
    }

    @Test
    public void shouldOutputGivenForGivenStepInput() {
        String jbehave = "Given I am a step";
        System.setIn(new ByteArrayInputStream(jbehave.getBytes(Charset.defaultCharset())));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Main.main(new String[]{});

        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, equalTo("Given I am a step"));
    }

    @Test
    public void shouldOutputMultipleStepsForMultipleStepInput() {
        String jbehave = "Given I am a step" + System.lineSeparator() + "When I am another step";
        System.setIn(new ByteArrayInputStream(jbehave.getBytes(Charset.defaultCharset())));
        ByteArrayOutputStream gherkinByteStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(gherkinByteStream));

        Main.main(new String[]{});

        String gherkin = new String(gherkinByteStream.toByteArray(), Charset.defaultCharset());
        assertThat(gherkin, equalTo("Given I am a step" + System.lineSeparator() + "When I am another step"));
    }
}
