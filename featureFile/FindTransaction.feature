Feature: Find Transaction
  As a logged-in user
  I want to find transactions by various criteria
  So that I can review my account activity

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC5
  Scenario: Validate find transactions by amount
    When the user navigates to Find Transactions
    And selects an account to search transactions
    And searches for transactions by amount "20"
    Then the transaction results table should be displayed
    And the transaction rows should be visible

  @regression @NewTC5
  Scenario: Validate find transactions by date
    When the user navigates to Find Transactions
    And selects an account to search transactions
    And searches for transactions by date "01-01-2024"
    Then the transaction results table should be displayed

  @regression @NewTC5
  Scenario: Validate find transactions by date range
    When the user navigates to Find Transactions
    And selects an account to search transactions
    And searches for transactions by date range from "01-01-2024" to "12-31-2025"
    Then the transaction results table should be displayed

  @regression @NewTC5 @negative
  Scenario: Validate find transactions with invalid transaction ID shows no results
    When the user navigates to Find Transactions
    And selects an account to search transactions
    And searches for transactions by amount "0.01"
    Then no transactions should be found or an error message is shown
