JBehave to Gherkin [![Build Status](https://travis-ci.org/adaptive-logic/jbehave-to-gherkin.png?branch=master)](https://travis-ci.org/adaptive-logic/jbehave-to-gherkin)
==================

Converts JBehave grammar to [Gherkin]((https://github.com/cucumber/cucumber/wiki/Gherkin)) for use by [Cucumber](https://github.com/cucumber/cucumber), [SpecFlow](https://github.com/techtalk/SpecFlow) or [Behat](https://github.com/Behat/Behat).

Usage
-----

```shell
$ java -jar jbehave-to-gherkin.jar < example.story > example.feature
$ cat example.story
```

```
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

```shell
$ cat example.feature
```

```
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

More Information
----------------

* [JBehave Grammar](http://jbehave.org/reference/stable/grammar.html)
* [Behat Gherkin Documentation](http://docs.behat.org/guides/1.gherkin.html)
* [Gherkin Formatter](https://github.com/cucumber/gherkin)
* [JBehave Examples](http://git.codehaus.org/gitweb.cgi?p=jbehave-core.git;a=tree;f=examples/core/src/main/java/org/jbehave/examples/core/stories)

Backlog
-------

[![Stories in Ready](https://badge.waffle.io/adaptive-logic/jbehave-to-gherkin.png?label=ready)](https://waffle.io/adaptive-logic/jbehave-to-gherkin)
