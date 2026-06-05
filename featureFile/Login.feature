Feature: User Login
  @regression @registerationPositive @sanity
  Scenario Outline: User logs in successfully
    Given the user navigates to the ParaBank application
    When the user logs in using the username and password from Excel row <Row_index>
    Then the user is logged in successfully
    Examples:
      | Row_index |
      |         1 |






