package uk.co.adaptivelogic.jbehavetogherkin;

import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        InputStreamReader jBehaveIn = new InputStreamReader(System.in, Charset.defaultCharset());
        OutputStreamWriter gherkinOut = new OutputStreamWriter(System.out, Charset.defaultCharset());

        translate(jBehaveIn, gherkinOut);
    }

    private static void translate(InputStreamReader jBehaveIn, OutputStreamWriter gherkinOut) {
        String jbehave = readJBehave(jBehaveIn);
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

    private static String translate(String jbehave) {
        String gherkin;
        if (jbehave.isEmpty()) {
            gherkin = "";
        } else {
            gherkin = "Feature: Example";
        }
        return gherkin;
    }

    private static String readJBehave(InputStreamReader jBehaveIn) {
        BufferedReader bufferedReader = new BufferedReader(jBehaveIn);
        StringBuilder jbehaveBuilder = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jbehaveBuilder.append(line);
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return jbehaveBuilder.toString();
    }
}
