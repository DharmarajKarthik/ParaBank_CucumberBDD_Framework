package StepDefinitions;

import PageObjects.RegisterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import static Factory.BaseClass.getDriver;
import static Factory.BaseClass.getProperties;

public class Registration {

    static WebDriver driver;
    static RegisterPage registerPage;
    static Properties p;

    @Given("I navigate to the registration page")
    public void i_navigate_to_the_registration_page() throws IOException {

        driver = getDriver();

        p = getProperties();
        registerPage = new RegisterPage(driver);

        driver.get(p.getProperty("appURL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

       registerPage.clickRegisterLink();

    }
    @When("I enter valid registration details")
    public void i_enter_valid_registration_details() {
        registerPage.fillRegistrationForm(
                p.getProperty("firstname"),
                p.getProperty("lastname"),
                p.getProperty("address"),
                p.getProperty("city"),
                p.getProperty("state"),
                p.getProperty("zipCode"),
                p.getProperty("phoneNumber"),
                p.getProperty("ssn"),
                p.getProperty("username"),
                p.getProperty("password"),
                p.getProperty("confirmPassword")
        );

    }
    @When("I submit the registration form")
    public void i_submit_the_registration_form() {

        registerPage.clickRegisterButton();


    }
    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() {

        boolean successMessageDisplayed = driver.findElement(By.xpath("//p[contains(text(), 'Your account was created successfully. You are now logged in.')]")).isDisplayed();
        Assert.assertEquals(successMessageDisplayed, true);


    }
}
