package StepDefinitions;

import Factory.BaseClass;
import PageObjects.TransferFundsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TransferFundsSteps {

    WebDriver driver;
    TransferFundsPage transferFundsPage;
    Properties p;

    @When("the user navigates to Transfer Funds")
    public void the_user_navigates_to_transfer_funds() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.clickTransferFundsLink();
    }

    @And("enters transfer amount {string}")
    public void enters_transfer_amount(String amount) {
        transferFundsPage.enterAmount(amount);
    }

    @And("selects a source account")
    public void selects_a_source_account() {
        transferFundsPage.selectFromAccount();
    }

    @And("selects a destination account")
    public void selects_a_destination_account() {
        transferFundsPage.selectToAccount();
    }

    @And("clicks the Transfer button")
    public void clicks_the_transfer_button() {
        transferFundsPage.clickTransferButton();
    }

    @Then("the transfer message {string} should be displayed")
    public void the_transfer_message_should_be_displayed(String expectedMessage) {
        if ("Transfer Complete!".equals(expectedMessage)) {
            Assert.assertTrue("Transfer Complete! message not displayed",
                    transferFundsPage.isTransferCompleteDisplayed());
        }
    }

    @And("the transfer details message should be displayed")
    public void the_transfer_details_message_should_be_displayed() {
        Assert.assertTrue("Transfer details message not displayed",
                transferFundsPage.isTransferDetailsMessageDisplayed());
    }

    @And("the See Account Activity link should be displayed")
    public void the_see_account_activity_link_should_be_displayed() {
        Assert.assertTrue("See Account Activity link not displayed",
                transferFundsPage.isSeeAccountActivityLinkDisplayed());
    }

    @Then("an error message should be displayed for the invalid transfer amount")
    public void an_error_message_should_be_displayed_for_the_invalid_transfer_amount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // ParaBank shows an error span or stays on the same page without Transfer Complete
        boolean errorPresent = false;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[@id='amount.errors'] | //p[@class='error'] | //b[contains(text(),'Please enter')]")));
            errorPresent = true;
        } catch (Exception ignored) {
            // Also verify the Transfer Complete header is NOT shown
            errorPresent = driver.findElements(By.xpath("//h1[normalize-space()='Transfer Complete!']")).isEmpty();
        }
        Assert.assertTrue("Expected an error for invalid transfer amount", errorPresent);
    }
}
