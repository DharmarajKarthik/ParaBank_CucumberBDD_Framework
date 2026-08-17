package StepDefinitions;

import Factory.BaseClass;
import PageObjects.AccountsOverviewPage;
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
import java.util.List;
import java.util.Properties;

public class AccountsOverviewSteps {

    WebDriver driver;
    AccountsOverviewPage accountsOverviewPage;
    Properties p;

    @When("the user navigates to Accounts Overview")
    public void the_user_navigates_to_accounts_overview() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        accountsOverviewPage = new AccountsOverviewPage(driver);
        accountsOverviewPage.clickAccountsOverviewLink();
    }

    @Then("the accounts table should be displayed")
    public void the_accounts_table_should_be_displayed() {
        Assert.assertTrue("Accounts table is not displayed", accountsOverviewPage.isAccountTableDisplayed());
    }

    @And("the table should contain columns {string}, {string}, and {string}")
    public void the_table_should_contain_columns(String col1, String col2, String col3) {
        List<String> headers = accountsOverviewPage.getTableHeaders();
        String headersJoined = String.join("|", headers).toLowerCase();
        Assert.assertTrue("Missing '" + col1 + "' column", headersJoined.contains(col1.toLowerCase()));
        Assert.assertTrue("Missing '" + col2 + "' column", headersJoined.contains(col2.toLowerCase()));
        Assert.assertTrue("Missing '" + col3 + "' column", headersJoined.contains(col3.toLowerCase()));
    }

    @And("at least one account row should be present")
    public void at_least_one_account_row_should_be_present() {
        Assert.assertTrue("No account rows found in table", accountsOverviewPage.hasAccountRows());
    }

    @When("the user navigates directly to the accounts overview URL")
    public void the_user_navigates_directly_to_accounts_overview_url() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        String overviewUrl = p.getProperty("appURL").replace("index.htm", "overview.htm");
        driver.get(overviewUrl);
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // ParaBank redirects unauthenticated users to the login page (index.htm)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
        Assert.assertTrue("Expected login form to be visible",
                driver.findElement(By.xpath("//input[@name='username']")).isDisplayed());
    }
}
