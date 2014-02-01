package uk.co.adaptivelogic.jbehavetogherkin;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.*;

import java.util.ArrayList;
import java.util.List;

public class FeatureWrapper {
    private List<ScenarioWrapper> scenarios = new ArrayList<ScenarioWrapper>();
    private Feature feature = new Feature(null, null, null, null, null, null, null) {
        public void replay(Formatter formatter) {
            // Do nothing
        }
    };
    private BackgroundWrapper backgroundWrapper = new BackgroundWrapper();

    public void setScenarios(List<ScenarioWrapper> scenarios) {
        this.scenarios = scenarios;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public void setBackground(BackgroundWrapper backgroundWrapper) {
        this.backgroundWrapper = backgroundWrapper;
    }

    public void replay(Formatter formatter) {
        feature.replay(formatter);
        backgroundWrapper.replay(formatter);
        for (ScenarioWrapper scenario : scenarios) {
            scenario.replay(formatter);
        }
    }
}
