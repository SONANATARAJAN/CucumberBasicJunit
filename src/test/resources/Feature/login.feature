#cucumber

@SmokeTest
Feature: Feature to test login functionality
  @Smoke
  @SmokeScenario
  Scenario Outline: Check login is succesfully with valid credentials

    Given user is on login page
    When user enters <username> and <password>
    And click on logic button
    Then user is navigated to home page
    Examples:
      | username | password |
      |student|Password123|
 




