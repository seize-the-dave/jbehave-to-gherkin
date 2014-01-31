package uk.co.adaptivelogic.jbehavetogherkin;

import org.apache.commons.io.IOUtils;
import org.jbehave.core.model.Story;
import org.jbehave.core.parsers.RegexStoryParser;

import java.io.IOException;
import java.io.Reader;

public class JBehaveReader {
    private final Reader reader;

    public JBehaveReader(Reader reader) {
        this.reader = reader;
    }

    public Story read() throws IOException {
        RegexStoryParser storyParser = new RegexStoryParser();
        return storyParser.parseStory(IOUtils.toString(reader));
    }
}