package StepDefinitions;

import Factory.BaseClass;
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

public class LogoutSteps {

    WebDriver driver;
    Properties p;

    @When("the user clicks the Logout link")
    public void the_user_clicks_the_logout_link() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // ParaBank renders logout as "Log Out" (with space) — use contains to be safe
        By logoutBy = By.xpath("//a[contains(normalize-space(.),'Log Out') or contains(@href,'logout')]");
        wait.until(ExpectedConditions.elementToBeClickable(logoutBy));
        driver.findElement(logoutBy).click();
    }

    @Then("the user should be redirected to the login page after logout")
    public void the_user_should_be_redirected_to_the_login_page_after_logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // After logout ParaBank redirects to index.htm with the login panel
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")),
                ExpectedConditions.urlContains("index.htm"),
                ExpectedConditions.urlContains("login.htm")
        ));
        Assert.assertTrue("Login form (username field) should be visible after logout",
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")))
                        .isDisplayed());
    }

    @And("the login form should be visible")
    public void the_login_form_should_be_visible() {
        Assert.assertTrue("Password field should be visible on login page",
                driver.findElement(By.xpath("//input[@name='password']")).isDisplayed());
        Assert.assertTrue("Log In button should be visible on login page",
                driver.findElement(By.xpath("//input[@value='Log In']")).isDisplayed());
    }

    @And("navigating to the accounts overview after logout should redirect to the login page")
    public void navigating_to_accounts_overview_after_logout_should_redirect_to_login() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        String overviewUrl = p.getProperty("appURL").replace("index.htm", "overview.htm");
        driver.get(overviewUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        Assert.assertTrue("Accessing protected page after logout should redirect to login form",
                driver.findElement(By.xpath("//input[@name='username']")).isDisplayed());
    }
}
