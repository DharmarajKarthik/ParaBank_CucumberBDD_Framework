package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactInfoPage extends DriverBasePage {

    public ContactInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Update Contact Info']")
    WebElement updateContactInfoLink;

    @FindBy(id = "customer.firstName")
    WebElement firstName;

    @FindBy(id = "customer.lastName")
    WebElement lastName;

    @FindBy(id = "customer.address.street")
    WebElement address;

    @FindBy(id = "customer.address.city")
    WebElement city;

    @FindBy(id = "customer.address.state")
    WebElement state;

    @FindBy(id = "customer.address.zipCode")
    WebElement zipCode;

    @FindBy(id = "customer.phoneNumber")
    WebElement phoneNumber;

    @FindBy(xpath = "//input[@value='Update Profile']")
    WebElement updateProfileButton;

    @FindBy(xpath = "//h1[normalize-space()='Profile Updated']")
    WebElement profileUpdatedHeader;

    @FindBy(xpath = "//p[contains(text(),'Your updated address and phone number have been added to the system')]")
    WebElement successMessage;

    // Negative: required field errors
    @FindBy(xpath = "//span[@class='error'] | //span[contains(@id,'errors')]")
    WebElement fieldError;

    public void clickUpdateContactInfoLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(updateContactInfoLink));
        updateContactInfoLink.click();
    }

    public void updateContactDetails(String firstNameVal, String lastNameVal, String addressVal,
                                      String cityVal, String stateVal, String zipVal, String phoneVal) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(firstName));
        clearAndType(firstName, firstNameVal);
        clearAndType(lastName, lastNameVal);
        clearAndType(address, addressVal);
        clearAndType(city, cityVal);
        clearAndType(state, stateVal);
        clearAndType(zipCode, zipVal);
        clearAndType(phoneNumber, phoneVal);
    }

    public void clearAllContactDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.clear();
        lastName.clear();
        address.clear();
        city.clear();
        state.clear();
        zipCode.clear();
        phoneNumber.clear();
    }

    public void clickUpdateProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(updateProfileButton));
        updateProfileButton.click();
    }

    public boolean isProfileUpdatedDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(profileUpdatedHeader));
        return profileUpdatedHeader.isDisplayed();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    private void clearAndType(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }
}
