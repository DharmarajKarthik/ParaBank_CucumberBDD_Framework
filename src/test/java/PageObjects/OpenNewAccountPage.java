package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenNewAccountPage extends DriverBasePage {

    public OpenNewAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Open New Account']")
    WebElement openNewAccountLink;

    @FindBy(id = "type")
    WebElement accountTypeDropdown;

    @FindBy(id = "fromAccountId")
    WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Open New Account']")
    WebElement openAccountButton;

    @FindBy(xpath = "//h1[normalize-space()='Account Opened!']")
    WebElement accountOpenedHeader;

    @FindBy(xpath = "//p[contains(text(),'Congratulations, your account is now open.')]")
    WebElement congratsMessage;

    @FindBy(id = "newAccountId")
    WebElement newAccountId;

    // --- Negative scenario elements ---
    @FindBy(xpath = "//p[contains(text(),'An error') or contains(text(),'could not')]")
    WebElement errorMessage;

    public void clickOpenNewAccountLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(openNewAccountLink));
        openNewAccountLink.click();
    }

    public void selectAccountType(String accountType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(accountTypeDropdown));
        new Select(accountTypeDropdown).selectByVisibleText(accountType);
    }

    public void selectFromAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(fromAccountDropdown));
        // Select the first available account
        new Select(fromAccountDropdown).selectByIndex(0);
    }

    public void clickOpenAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(openAccountButton));
        openAccountButton.click();
    }

    public boolean isAccountOpenedMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(accountOpenedHeader));
        return accountOpenedHeader.isDisplayed();
    }

    public boolean isCongratsMessageDisplayed() {
        return congratsMessage.isDisplayed();
    }

    public String getNewAccountId() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(newAccountId));
        return newAccountId.getText();
    }
}
