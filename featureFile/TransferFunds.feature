Feature: Transfer Funds
  As a logged-in user
  I want to transfer funds between accounts
  So that I can manage my account balances

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC3
  Scenario: Validate successful fund transfer between accounts
    When the user navigates to Transfer Funds
    And enters transfer amount "20"
    And selects a source account
    And selects a destination account
    And clicks the Transfer button
    Then the transfer message "Transfer Complete!" should be displayed
    And the transfer details message should be displayed
    And the See Account Activity link should be displayed

  @regression @NewTC3 @negative
  Scenario: Validate fund transfer fails with empty amount
    When the user navigates to Transfer Funds
    And enters transfer amount ""
    And selects a source account
    And selects a destination account
    And clicks the Transfer button
    Then an error message should be displayed for the invalid transfer amount

  @regression @NewTC3 @negative
  Scenario: Validate fund transfer fails with zero amount
    When the user navigates to Transfer Funds
    And enters transfer amount "0"
    And selects a source account
    And selects a destination account
    And clicks the Transfer button
    Then an error message should be displayed for the invalid transfer amount
