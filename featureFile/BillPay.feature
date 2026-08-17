Feature: Bill Pay
  As a logged-in user
  I want to pay bills online
  So that I can manage my bill payments

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC4
  Scenario: Validate successful bill payment
    When the user navigates to Bill Pay
    And fills in payee details with name "TestPayee", address "123 Main St", city "Springfield", state "IL", zip "62701", phone "217-555-0199", and account "54321"
    And enters bill payment amount "50"
    And selects a source account for bill payment
    And clicks Send Payment button
    Then the bill payment message "Bill Payment Complete!" should be displayed
    And the bill payment success details should be displayed
    And the See Account Activity link should be visible after bill payment

  @regression @NewTC4 @negative
  Scenario: Validate bill payment fails with missing payee name
    When the user navigates to Bill Pay
    And fills in payee details with name "", address "123 Main St", city "Springfield", state "IL", zip "62701", phone "217-555-0199", and account "54321"
    And enters bill payment amount "50"
    And selects a source account for bill payment
    And clicks Send Payment button
    Then the validation error should be shown for missing payee information

  @regression @NewTC4 @negative
  Scenario: Validate bill payment fails with mismatched account numbers
    When the user navigates to Bill Pay
    And fills in payee details with mismatched account numbers
    And enters bill payment amount "50"
    And selects a source account for bill payment
    And clicks Send Payment button
    Then the validation error should be shown for account number mismatch
