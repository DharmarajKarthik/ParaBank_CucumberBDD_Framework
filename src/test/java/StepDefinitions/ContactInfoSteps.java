package StepDefinitions;

import Factory.BaseClass;
import PageObjects.ContactInfoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ContactInfoSteps {

    WebDriver driver;
    ContactInfoPage contactInfoPage;
    Properties p;

    @When("the user navigates to Update Contact Info")
    public void the_user_navigates_to_update_contact_info() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        contactInfoPage = new ContactInfoPage(driver);
        contactInfoPage.clickUpdateContactInfoLink();
    }

    @And("updates contact details with first name {string}, last name {string}, address {string}, city {string}, state {string}, zip {string}, phone {string}")
    public void updates_contact_details(String firstName, String lastName, String address,
                                         String city, String state, String zip, String phone) {
        contactInfoPage.updateContactDetails(firstName, lastName, address, city, state, zip, phone);
    }

    @And("clicks Update Profile button")
    public void clicks_update_profile_button() {
        contactInfoPage.clickUpdateProfile();
    }

    @Then("the profile message {string} should be displayed")
    public void the_profile_updated_message_should_be_displayed(String expectedMessage) {
        if ("Profile Updated".equals(expectedMessage)) {
            Assert.assertTrue("Profile Updated message not displayed",
                    contactInfoPage.isProfileUpdatedDisplayed());
        }
    }

    @And("the success message {string} should be shown")
    public void the_success_message_should_be_shown(String expectedText) {
        Assert.assertTrue("Expected success message text not found: " + expectedText,
                contactInfoPage.isSuccessMessageDisplayed());
    }

    @And("clears all contact info fields")
    public void clears_all_contact_info_fields() {
        contactInfoPage.clearAllContactDetails();
    }

    @Then("the field validation errors should be displayed for contact info")
    public void the_field_validation_errors_should_be_displayed_for_contact_info() {
        List<WebElement> errors = driver.findElements(
                By.xpath("//span[@class='error'] | //span[contains(@id,'errors')]"));
        Assert.assertFalse("Expected field validation errors but none were found", errors.isEmpty());
    }
}
