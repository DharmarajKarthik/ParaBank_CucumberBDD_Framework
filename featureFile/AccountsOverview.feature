Feature: Accounts Overview
  As a logged-in user
  I want to view my accounts overview
  So that I can see my account balances

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC1
  Scenario: Validate Accounts Overview table is displayed with correct columns
    When the user navigates to Accounts Overview
    Then the accounts table should be displayed
    And the table should contain columns "Account", "Balance", and "Available Amount"
    And at least one account row should be present

  @regression @NewTC1 @negative
  Scenario: Validate Accounts Overview is not accessible without login
    Given the user is logged out
    When the user navigates directly to the accounts overview URL
    Then the user should be redirected to the login page
