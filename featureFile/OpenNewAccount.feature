Feature: Open New Account
  As a logged-in user
  I want to open a new bank account
  So that I can manage multiple accounts

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC2
  Scenario: Validate opening a new CHECKING account
    When the user navigates to Open New Account
    And selects account type "CHECKING"
    And selects an existing account as the funding source
    And clicks Open New Account button
    Then the account opened message "Account Opened!" should be displayed
    And the congratulations message should be displayed
    And a new account number should be generated

  @regression @NewTC2
  Scenario: Validate opening a new SAVINGS account
    When the user navigates to Open New Account
    And selects account type "SAVINGS"
    And selects an existing account as the funding source
    And clicks Open New Account button
    Then the account opened message "Account Opened!" should be displayed
    And the congratulations message should be displayed
    And a new account number should be generated
