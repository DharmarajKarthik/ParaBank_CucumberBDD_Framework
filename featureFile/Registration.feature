Feature: User Registration

  @regression @registerationPositive @sanity
  Scenario: Successful registration with valid details
    Given I navigate to the registration page
    When I enter valid registration details
    And I submit the registration form
    Then I should see a confirmation message

 @regression @registerationNegative
Scenario: Registration with missing required fields
   Given I navigate to the registration page
   When I leave registration details empty
   And I submit the registration form
   Then I should see a the error message for the missing fields
