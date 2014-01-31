JBehave to Gherkin [![Build Status](https://travis-ci.org/adaptive-logic/jbehave-to-gherkin.png?branch=master)](https://travis-ci.org/adaptive-logic/jbehave-to-gherkin)
==================

Converts JBehave grammar to [Gherkin]((https://github.com/cucumber/cucumber/wiki/Gherkin)) for use by [Cucumber](https://github.com/cucumber/cucumber), [SpecFlow](https://github.com/techtalk/SpecFlow) or [Behat](https://github.com/Behat/Behat).

Usage
-----

```shell
$ java -jar jbehave-to-gherkin.jar < example.story > example.feature
$ cat example.story
Gherkin feature description

Narrative:
In order to use Cucumber
As a business analyst
I want to convert my JBehave to Gherkin

Scenario: Gherkin scenario description
Given I have some JBehave stories
When I execute jbehave-to-gherkin
Then I should have Gherkin features

$ cat example.feature
Feature: Gherkin feature description
  In order to use Cucumber
  As a business analyst
  I want to convert my JBehave to Gherkin

  Scenario: Gherkin scenario description
    Given I have some JBehave stories
    When I execute jbehave-to-gherkin
    Then I should have Gherkin features

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
