# JBehave to Gherkin [![Build Status](https://drone.io/github.com/adaptive-logic/jbehave-to-gherkin/status.png)](https://drone.io/github.com/adaptive-logic/jbehave-to-gherkin/latest)

Converts JBehave grammar to [Gherkin]((https://github.com/cucumber/cucumber/wiki/Gherkin)) for use by [Cucumber](https://github.com/cucumber/cucumber), [SpecFlow](https://github.com/techtalk/SpecFlow), [Behat](https://github.com/Behat/Behat) or [behave](http://pythonhosted.org/behave/).

## Download

Download a [the release JAR with all dependencies](https://github.com/adaptive-logic/jbehave-to-gherkin/releases/download/jbehave-to-gherkin-1.0.0/jbehave-to-gherkin-1.0.1-jar-with-dependencies.jar) or [the standalone release JAR](https://github.com/adaptive-logic/jbehave-to-gherkin/releases/download/jbehave-to-gherkin-1.0.1/jbehave-to-gherkin-1.0.0.jar).

## Usage

JBehave to Gherkin is a Java application which reads a JBehave story from stdin, and writes a Gherkin feature to stdout, which means it works well with redirects and pipes.

```shell
$ java -jar jbehave-to-gherkin.jar < example.story > example.feature
```

## Example Conversion

The following example shows the expected output for a sample story. 

### Input

```jbehave
Gherkin feature description

Meta:
@meta

Narrative:
In order to use Cucumber
As a business analyst
I want to convert my JBehave to Gherkin

Lifecycle:
Before:
Given I am a before step

Scenario: Gherkin scenario description
Meta:
@scenario-meta
Given I have some JBehave stories
|name|rank|
|Larry|Stooge 3|
|Moe|Stooge 1|
|Curly|Stooge 2|
When I execute jbehave-to-gherkin
Then I should have Gherkin features

Scenario: Second scenario
Meta:
@author Joe Bloggs
Given a [precondition] has occurred
When an action is performed
Then a [postcondition] should occur

Examples:
|precondition|postcondition|
|foo|bar|
```

### Output

```gherkin
@meta
Feature: Gherkin feature description
  In order to use Cucumber
  As a business analyst
  I want to convert my JBehave to Gherkin

  Background: 
    Given I am a before step

  @scenario-meta
  Scenario: Gherkin scenario description
    Given I have some JBehave stories

      | name  | rank     |
      | Larry | Stooge 3 |
      | Moe   | Stooge 1 |
      | Curly | Stooge 2 |
    When I execute jbehave-to-gherkin
    Then I should have Gherkin features

  @author-joe-bloggs
  Scenario Outline: Second scenario
    Given a [precondition] has occurred
    When an action is performed
    Then a [postcondition] should occur

    Examples: 
      | precondition | postcondition |
      | foo          | bar           |
```

## Limitations

There is no support for After steps or GivenStories from JBehave, as there is no corresponding keyword in Gherkin.  Furthermore, Meta values are concatenated and converted to lowercase, so that "@name Foo Bar" in JBehave becomes "@name-foo-bar" in Gherkin.

There is also no support for the But step definition or DocStrings in Gherkin, as there is no corresponding structure in the JBehave grammar.
