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
