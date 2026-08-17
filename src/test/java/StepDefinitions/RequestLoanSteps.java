package StepDefinitions;

import Factory.BaseClass;
import PageObjects.RequestLoanPage;
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

public class RequestLoanSteps {

    WebDriver driver;
    RequestLoanPage requestLoanPage;
    Properties p;

    @When("the user navigates to Request Loan")
    public void the_user_navigates_to_request_loan() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        requestLoanPage = new RequestLoanPage(driver);
        requestLoanPage.clickRequestLoanLink();
    }

    @And("enters loan amount {string}")
    public void enters_loan_amount(String amount) {
        requestLoanPage.enterLoanAmount(amount);
    }

    @And("enters down payment {string}")
    public void enters_down_payment(String downPayment) {
        requestLoanPage.enterDownPayment(downPayment);
    }

    @And("selects a source account for the loan")
    public void selects_a_source_account_for_the_loan() {
        requestLoanPage.selectFromAccount();
    }

    @And("clicks Apply Now button")
    public void clicks_apply_now_button() {
        requestLoanPage.clickApplyNow();
    }

    @Then("the loan message {string} should be displayed")
    public void the_loan_message_should_be_displayed(String expectedMessage) {
        if ("Loan Request Processed".equals(expectedMessage)) {
            Assert.assertTrue("Loan Request Processed message not displayed",
                    requestLoanPage.isLoanRequestProcessedDisplayed());
        }
    }

    @And("the loan status should be {string}")
    public void the_loan_status_should_be(String expectedStatus) {
        if ("Approved".equalsIgnoreCase(expectedStatus)) {
            Assert.assertTrue("Loan was expected to be Approved but it was not",
                    requestLoanPage.isLoanApproved());
        } else if ("Denied".equalsIgnoreCase(expectedStatus)) {
            Assert.assertTrue("Loan was expected to be Denied but it was not",
                    requestLoanPage.isLoanDenied());
        }
    }

    @And("a new loan account number should be generated")
    public void a_new_loan_account_number_should_be_generated() {
        String loanAccountId = requestLoanPage.getNewLoanAccountId();
        Assert.assertNotNull("New loan account ID is null", loanAccountId);
        Assert.assertFalse("New loan account ID is empty", loanAccountId.trim().isEmpty());
    }

    @Then("a validation error should be displayed for invalid loan amount")
    public void a_validation_error_should_be_displayed_for_invalid_loan_amount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean errorPresent;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[@class='error'] | //span[contains(@class,'error')]")));
            errorPresent = true;
        } catch (Exception ignored) {
            // If no dedicated error shown, check the Loan Request Processed header is NOT present
            List<WebElement> processedHeader = driver.findElements(
                    By.xpath("//h1[normalize-space()='Loan Request Processed']"));
            errorPresent = processedHeader.isEmpty();
        }
        Assert.assertTrue("Expected a validation error for invalid loan amount", errorPresent);
    }
}
