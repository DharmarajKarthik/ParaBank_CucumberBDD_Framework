Feature: Logout
  As a logged-in user
  I want to logout from the application
  So that my session is terminated securely

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC8
  Scenario: Validate successful logout
    When the user clicks the Logout link
    Then the user should be redirected to the login page after logout
    And the login form should be visible

  @regression @NewTC8 @negative
  Scenario: Validate session is terminated after logout
    When the user clicks the Logout link
    Then the user should be redirected to the login page after logout
    And navigating to the accounts overview after logout should redirect to the login page
