package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends DriverBasePage {
    WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public RegisterPage(WebDriver driver) {

        super(driver);
    }

    // Register link on the homepage
    @FindBy(xpath = "//a[contains(@href,'register.htm') or contains(normalize-space(.),'Register')]")
    WebElement registerLink;


    // WebElements for the registration form fields
    @FindBy(xpath = "//input[@id = 'customer.firstName']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id = 'customer.lastName']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id = 'customer.address.street']")
    WebElement address;

    @FindBy(xpath = "//input[@id = 'customer.address.city']")
    WebElement city;

    @FindBy(xpath = "//input[@id = 'customer.address.state']")
    WebElement state;

    @FindBy(xpath = "//input[@id = 'customer.address.zipCode']")
    WebElement zipCode;

    @FindBy(xpath = "//input[@id = 'customer.phoneNumber']")
    WebElement phoneNumber;

    @FindBy(xpath = "//input[@id = 'customer.ssn']")
    WebElement ssn;

    @FindBy(xpath = "//input[@id = 'customer.username']")
    WebElement username;

    @FindBy(xpath = "//input[@id = 'customer.password']")
    WebElement password;

    @FindBy(xpath = "//input[@id = 'repeatedPassword']")
    WebElement repeatedPassword;

    @FindBy(how = How.XPATH, using = "//input[@value = 'Register']")
    WebElement registerButton;

    // Action Method to click the register link
    public void clickRegisterLink() {

        WebDriverWait  wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));

        registerLink.click();
    }

    // Action Method to fill out the registration form
    public void fillRegistrationForm(String firstNameValue, String lastNameValue, String addressValue, String cityValue, String stateValue, String zipCodeValue, String phoneNumberValue, String ssnValue, String usernameValue, String passwordValue, String repeatedPasswordValue) {
        firstName.sendKeys(firstNameValue);
        lastName.sendKeys(lastNameValue);
        address.sendKeys(addressValue);
        city.sendKeys(cityValue);
        state.sendKeys(stateValue);
        zipCode.sendKeys(zipCodeValue);
        phoneNumber.sendKeys(phoneNumberValue);
        ssn.sendKeys(ssnValue);
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue);
        repeatedPassword.sendKeys(repeatedPasswordValue);
    }


    // Action Method to click the Register button
    public void clickRegisterButton() {

        registerButton.click();
    }
}
