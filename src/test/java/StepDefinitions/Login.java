package StepDefinitions;

import PageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilitity.DataReader;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static Factory.BaseClass.getDriver;
import static Factory.BaseClass.getProperties;

public class Login {

    WebDriver driver;
    LoginPage loginPage;
    Properties p;

    String currentUsername;
    List<HashMap<String, String>> datamap;
    WebDriverWait wait ;
    @Given("the user navigates to the ParaBank application")
    public void the_user_navigates_to_the_para_bank_application() throws IOException {
        driver  = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        p = getProperties();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();

    }

    @When("the user logs in using the username and password from Excel row {int}")
    public void the_user_logs_in_using_the_username_and_password_from_excel_row(Integer Row_index) throws IOException {
        // reads data from thr DataReader class
        try {
            datamap = DataReader.readData(System.getProperty("user.dir") + "\\testdata\\Parabank_Testdata.xlsx", "LoginCreds");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int index = Row_index - 1;

        String username = datamap.get(index).get("username");
        String password = datamap.get(index).get("password");
        currentUsername = username.trim();

        loginPage.PassUsername(username);
        loginPage.PassPassword(password);
        loginPage.clickLoginButton();
    }

    @Then("the user is logged in successfully")
    public void the_user_is_logged_in_successfully() {

        By welcomeMsgBy = By.xpath("(//*[contains(normalize-space(.), 'Welcome " +  p.getProperty("firstname") + " " + p.getProperty("lastname") + "')])[last()]");
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMsgBy))
                .getText()
                .trim();

        // Accepts “Welcome <username>”, “Welcome <username>:”, or “Welcome <username>!”
        String expectedPrefix = "Welcome " + p.getProperty("firstname") + " " + p.getProperty("lastname");
        boolean matches = actual.startsWith(expectedPrefix);
        if (!matches && (actual.startsWith(expectedPrefix + ":") || actual.startsWith(expectedPrefix + "!"))) {
            matches = true;
        }

        Assert.assertTrue(
                actual + "Wlecome message is displayed",
                matches
        );

    }

}
