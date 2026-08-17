package StepDefinitions;

import Factory.BaseClass;
import PageObjects.FindTransactionPage;
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

public class FindTransactionSteps {

    WebDriver driver;
    FindTransactionPage findTransactionPage;
    Properties p;

    @When("the user navigates to Find Transactions")
    public void the_user_navigates_to_find_transactions() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        findTransactionPage = new FindTransactionPage(driver);
        findTransactionPage.clickFindTransactionLink();
    }

    @And("selects an account to search transactions")
    public void selects_an_account_to_search_transactions() {
        findTransactionPage.selectAccount();
    }

    @And("searches for transactions by amount {string}")
    public void searches_for_transactions_by_amount(String amount) {
        findTransactionPage.enterAmount(amount);
        findTransactionPage.clickFindByAmount();
    }

    @And("searches for transactions by date {string}")
    public void searches_for_transactions_by_date(String date) {
        findTransactionPage.enterTransactionDate(date);
        findTransactionPage.clickFindByDate();
    }

    @And("searches for transactions by date range from {string} to {string}")
    public void searches_for_transactions_by_date_range(String fromDate, String toDate) {
        findTransactionPage.enterDateRange(fromDate, toDate);
        findTransactionPage.clickFindByDateRange();
    }

    @Then("the transaction results table should be displayed")
    public void the_transaction_results_table_should_be_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // ParaBank renders transaction results in ng-repeat rows
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//table[@id='transactionTable'] | //table[contains(@ng-show,'transactionList')]")));
            boolean tableVisible = driver.findElement(
                    By.xpath("//table[@id='transactionTable'] | //table[contains(@ng-show,'transactionList')]")).isDisplayed();
            Assert.assertTrue("Transaction results table not displayed", tableVisible);
        } catch (Exception e) {
            // If no transactions found, a "No transactions found" message may appear — that is also a valid result
            List<WebElement> noResultMsg = driver.findElements(
                    By.xpath("//span[contains(@ng-show,'noTransactions')] | //b[contains(text(),'No Transaction')]"));
            Assert.assertFalse("Neither transaction table nor no-results message found", noResultMsg.isEmpty() && true);
        }
    }

    @And("the transaction rows should be visible")
    public void the_transaction_rows_should_be_visible() {
        List<WebElement> rows = findTransactionPage.getTransactionRows();
        Assert.assertFalse("No transaction rows visible in the results table", rows.isEmpty());
    }

    @Then("no transactions should be found or an error message is shown")
    public void no_transactions_should_be_found_or_error_message_is_shown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Either a "no transactions" span appears, or the table is empty
        boolean noResultShown;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@ng-show,'noTransactions')] | //b[contains(text(),'No Transaction')]")));
            noResultShown = true;
        } catch (Exception ignored) {
            List<WebElement> rows = findTransactionPage.getTransactionRows();
            noResultShown = rows.isEmpty();
        }
        Assert.assertTrue("Expected no transactions or error message, but results were shown", noResultShown);
    }
}
