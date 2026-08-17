Feature: Request Loan
  As a logged-in user
  I want to apply for a loan
  So that I can get financial assistance

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC7
  Scenario: Validate successful loan request with reasonable amount
    When the user navigates to Request Loan
    And enters loan amount "1000"
    And enters down payment "100"
    And selects a source account for the loan
    And clicks Apply Now button
    Then the loan message "Loan Request Processed" should be displayed
    And the loan status should be "Approved"
    And a new loan account number should be generated

  @regression @NewTC7 @negative
  Scenario: Validate loan request is denied with excessive amount
    When the user navigates to Request Loan
    And enters loan amount "100000"
    And enters down payment "100"
    And selects a source account for the loan
    And clicks Apply Now button
    Then the loan message "Loan Request Processed" should be displayed
    And the loan status should be "Denied"

  @regression @NewTC7 @negative
  Scenario: Validate loan request fails with zero loan amount
    When the user navigates to Request Loan
    And enters loan amount "0"
    And enters down payment "0"
    And selects a source account for the loan
    And clicks Apply Now button
    Then a validation error should be displayed for invalid loan amount
