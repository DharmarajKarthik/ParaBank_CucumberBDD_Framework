package StepDefinitions;

import Factory.BaseClass;
import PageObjects.OpenNewAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class OpenNewAccountSteps {

    WebDriver driver;
    OpenNewAccountPage openNewAccountPage;
    Properties p;

    @When("the user navigates to Open New Account")
    public void the_user_navigates_to_open_new_account() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        openNewAccountPage = new OpenNewAccountPage(driver);
        openNewAccountPage.clickOpenNewAccountLink();
    }

    @And("selects account type {string}")
    public void selects_account_type(String accountType) {
        openNewAccountPage.selectAccountType(accountType);
    }

    @And("selects an existing account as the funding source")
    public void selects_an_existing_account_as_the_funding_source() {
        openNewAccountPage.selectFromAccount();
    }

    @And("clicks Open New Account button")
    public void clicks_open_new_account_button() {
        openNewAccountPage.clickOpenAccountButton();
    }

    @Then("the account opened message {string} should be displayed")
    public void the_account_opened_message_should_be_displayed(String expectedMessage) {
        if ("Account Opened!".equals(expectedMessage)) {
            Assert.assertTrue("Account Opened! message not displayed",
                    openNewAccountPage.isAccountOpenedMessageDisplayed());
        }
    }

    @And("the congratulations message should be displayed")
    public void the_congratulations_message_should_be_displayed() {
        Assert.assertTrue("Congratulations message not displayed",
                openNewAccountPage.isCongratsMessageDisplayed());
    }

    @And("a new account number should be generated")
    public void a_new_account_number_should_be_generated() {
        String newAccountId = openNewAccountPage.getNewAccountId();
        Assert.assertNotNull("New account ID is null", newAccountId);
        Assert.assertFalse("New account ID is empty", newAccountId.trim().isEmpty());
    }
}
