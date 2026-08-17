package StepDefinitions;

import Factory.BaseClass;
import PageObjects.LoginPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import utilitity.DataReader;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Shared step definitions for login background steps used across all post-login features.
 * The @Given("the user is logged in to ParaBank") step is defined here and reused
 * by Background sections in all new feature files.
 */
public class CommonSteps {

    WebDriver driver;
    Properties p;
    LoginPage loginPage;

    @Given("the user is logged in to ParaBank")
    public void the_user_is_logged_in_to_para_bank() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        loginPage = new LoginPage(driver);

        driver.get(p.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Read credentials from Excel row 1
        List<HashMap<String, String>> datamap;
        try {
            datamap = DataReader.readData(
                    System.getProperty("user.dir") + "\\testdata\\Parabank_Testdata.xlsx", "LoginCreds");
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data from Excel: " + e.getMessage(), e);
        }

        String username = datamap.get(0).get("username");
        String password = datamap.get(0).get("password");

        loginPage.PassUsername(username);
        loginPage.PassPassword(password);
        loginPage.clickLoginButton();
    }

    @Given("the user is logged out")
    public void the_user_is_logged_out() throws IOException {
        driver = BaseClass.getDriver();
        p = BaseClass.getProperties();
        // Delete all cookies to invalidate the session, then navigate to home
        driver.manage().deleteAllCookies();
        driver.get(p.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
