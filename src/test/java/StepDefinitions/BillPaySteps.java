package StepDefinitions;

import Factory.BaseClass;
import PageObjects.BillPayPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BillPaySteps {

    WebDriver driver;
    BillPayPage billPayPage;
    Properties p;

    @When("the user navigates to Bill Pay")
    public void the_user_navigates_to_bill_pay() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        billPayPage = new BillPayPage(driver);
        billPayPage.clickBillPayLink();
    }

    @And("fills in payee details with name {string}, address {string}, city {string}, state {string}, zip {string}, phone {string}, and account {string}")
    public void fills_in_payee_details(String name, String address, String city, String state,
                                        String zip, String phone, String accountNum) {
        billPayPage.fillPayeeDetails(name, address, city, state, zip, phone, accountNum);
    }

    @And("fills in payee details with mismatched account numbers")
    public void fills_in_payee_details_with_mismatched_account_numbers() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("payee.name")));
        driver.findElement(By.name("payee.name")).sendKeys("MismatchPayee");
        driver.findElement(By.name("payee.address.street")).sendKeys("123 Test St");
        driver.findElement(By.name("payee.address.city")).sendKeys("TestCity");
        driver.findElement(By.name("payee.address.state")).sendKeys("IL");
        driver.findElement(By.name("payee.address.zipCode")).sendKeys("60601");
        driver.findElement(By.name("payee.phoneNumber")).sendKeys("312-555-0001");
        driver.findElement(By.name("payee.accountNumber")).sendKeys("11111");
        driver.findElement(By.name("verifyAccount")).sendKeys("99999");  // mismatched
    }

    @And("enters bill payment amount {string}")
    public void enters_bill_payment_amount(String amount) {
        billPayPage.enterAmount(amount);
    }

    @And("selects a source account for bill payment")
    public void selects_a_source_account_for_bill_payment() {
        billPayPage.selectFromAccount();
    }

    @And("clicks Send Payment button")
    public void clicks_send_payment_button() {
        billPayPage.clickSendPayment();
    }

    @Then("the bill payment message {string} should be displayed")
    public void the_bill_payment_message_should_be_displayed(String expectedMessage) {
        if ("Bill Payment Complete!".equals(expectedMessage)) {
            Assert.assertTrue("Bill Payment Complete! message not displayed",
                    billPayPage.isBillPaymentCompleteDisplayed());
        }
    }

    @And("the bill payment success details should be displayed")
    public void the_bill_payment_success_details_should_be_displayed() {
        String successText = billPayPage.getSuccessMessageText();
        Assert.assertTrue("Bill payment success message not as expected",
                successText.contains("was successful"));
    }

    @And("the See Account Activity link should be visible after bill payment")
    public void the_see_account_activity_link_should_be_visible_after_bill_payment() {
        Assert.assertTrue("See Account Activity link not visible after bill payment",
                billPayPage.isSeeAccountActivityLinkDisplayed());
    }

    @Then("the validation error should be shown for missing payee information")
    public void the_validation_error_should_be_shown_for_missing_payee_information() {
        List<WebElement> errors = driver.findElements(
                By.xpath("//span[contains(@class,'error') and string-length(normalize-space(.)) > 0]"));
        Assert.assertFalse("Expected validation error for missing payee name, but none found", errors.isEmpty());
    }

    @Then("the validation error should be shown for account number mismatch")
    public void the_validation_error_should_be_shown_for_account_number_mismatch() {
        List<WebElement> errors = driver.findElements(
                By.xpath("//span[contains(@class,'error') and string-length(normalize-space(.)) > 0]"));
        Assert.assertFalse("Expected validation error for account number mismatch, but none found", errors.isEmpty());
    }
}
