Feature: feature to test google functionality
  Scenario: Validate Google Search Field
    Given browser is open
    When user Click search Feild
    And hit enter
    Then user is navigate to search result