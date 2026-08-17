package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage extends DriverBasePage {

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Transfer Funds']")
    WebElement transferFundsLink;

    @FindBy(id = "amount")
    WebElement amountField;

    @FindBy(id = "fromAccountId")
    WebElement fromAccountDropdown;

    @FindBy(id = "toAccountId")
    WebElement toAccountDropdown;

    @FindBy(xpath = "//input[@value='Transfer']")
    WebElement transferButton;

    @FindBy(xpath = "//h1[normalize-space()='Transfer Complete!']")
    WebElement transferCompleteHeader;

    @FindBy(xpath = "//p[contains(.,'has been transferred from account')]")
    WebElement transferDetailsMessage;

    @FindBy(xpath = "//a[contains(text(),'See Account Activity')]")
    WebElement seeAccountActivityLink;

    // Negative: error for empty amount
    @FindBy(xpath = "//span[@id='amount.errors'] | //p[@class='error']")
    WebElement amountErrorMessage;

    public void clickTransferFundsLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink));
        transferFundsLink.click();
    }

    public void enterAmount(String amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public void selectFromAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(fromAccountDropdown));
        new Select(fromAccountDropdown).selectByIndex(0);
    }

    public void selectToAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(toAccountDropdown));
        new Select(toAccountDropdown).selectByIndex(0);
    }

    public void clickTransferButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(transferButton));
        transferButton.click();
    }

    public boolean isTransferCompleteDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(transferCompleteHeader));
        return transferCompleteHeader.isDisplayed();
    }

    public boolean isTransferDetailsMessageDisplayed() {
        return transferDetailsMessage.isDisplayed();
    }

    public boolean isSeeAccountActivityLinkDisplayed() {
        return seeAccountActivityLink.isDisplayed();
    }
}
