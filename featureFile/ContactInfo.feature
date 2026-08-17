Feature: Update Contact Info
  As a logged-in user
  I want to update my contact information
  So that my profile details remain current

  Background:
    Given the user is logged in to ParaBank

  @regression @sanity @NewTC6
  Scenario: Validate successful profile update
    When the user navigates to Update Contact Info
    And updates contact details with first name "Sajaneesh", last name "Yashna", address "500 Elm Street", city "Chicago", state "IL", zip "60601", phone "312-555-0100"
    And clicks Update Profile button
    Then the profile message "Profile Updated" should be displayed
    And the success message "Your updated address and phone number have been added to the system" should be shown

  @regression @NewTC6 @negative
  Scenario: Validate profile update fails with empty required fields
    When the user navigates to Update Contact Info
    And clears all contact info fields
    And clicks Update Profile button
    Then the field validation errors should be displayed for contact info
