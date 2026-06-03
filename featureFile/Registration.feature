Feature: User Registration

  @regression @registerationPositive @sanity
  Scenario: Successful registration with valid details
    Given I navigate to the registration page
    When I enter valid registration details
    And I submit the registration form
    Then I should see a confirmation message

 @regression @registerationNegative
Scenario: Registration with missing required fields
  Given I am on the registration page
  When I leave required fields empty
  And I submit the registration form
  Then I should see error messages for the missing fields

