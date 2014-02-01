Feature: Gherkin feature description
  In order to use Cucumber
  As a business analyst
  I want to convert my JBehave to Gherkin

  Background: 
    Given I am a before step

  Scenario: Gherkin scenario description
    Given I have some JBehave stories
    When I execute jbehave-to-gherkin
    Then I should have Gherkin features

  Scenario Outline: Second scenario
    Given a [precondition] has occurred
    When an action is performed
    Then a [postcondition] should occur

    Examples: 
      | precondition | postcondition |
      | foo          | bar           |
