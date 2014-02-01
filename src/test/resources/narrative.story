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
