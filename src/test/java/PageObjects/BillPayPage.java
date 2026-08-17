package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillPayPage extends DriverBasePage {

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Bill Pay']")
    WebElement billPayLink;

    @FindBy(name = "payee.name")
    WebElement payeeName;

    @FindBy(name = "payee.address.street")
    WebElement payeeAddress;

    @FindBy(name = "payee.address.city")
    WebElement payeeCity;

    @FindBy(name = "payee.address.state")
    WebElement payeeState;

    @FindBy(name = "payee.address.zipCode")
    WebElement payeeZipCode;

    @FindBy(name = "payee.phoneNumber")
    WebElement payeePhone;

    @FindBy(name = "payee.accountNumber")
    WebElement payeeAccountNumber;

    @FindBy(name = "verifyAccount")
    WebElement verifyAccountNumber;

    @FindBy(name = "amount")
    WebElement amount;

    @FindBy(name = "fromAccountId")
    WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Send Payment']")
    WebElement sendPaymentButton;

    @FindBy(xpath = "//h1[contains(normalize-space(.),'Bill Payment Complete')] | //h1[contains(.,'Bill Payment Complete')]")
    WebElement billPaymentCompleteHeader;

    @FindBy(xpath = "//*[contains(.,'was successful') and not(self::script)]")
    WebElement successMessage;

    @FindBy(xpath = "//a[contains(text(),'See Account Activity')]")
    WebElement seeAccountActivityLink;

    // Negative: validation error elements
    @FindBy(xpath = "//span[contains(@class,'error') and not(contains(text(),''))]")
    WebElement validationError;

    public void clickBillPayLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(billPayLink));
        billPayLink.click();
    }

    public void fillPayeeDetails(String name, String address, String city, String state,
                                  String zip, String phone, String accountNum) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(payeeName));
        payeeName.clear();
        payeeName.sendKeys(name);
        payeeAddress.clear();
        payeeAddress.sendKeys(address);
        payeeCity.clear();
        payeeCity.sendKeys(city);
        payeeState.clear();
        payeeState.sendKeys(state);
        payeeZipCode.clear();
        payeeZipCode.sendKeys(zip);
        payeePhone.clear();
        payeePhone.sendKeys(phone);
        payeeAccountNumber.clear();
        payeeAccountNumber.sendKeys(accountNum);
        verifyAccountNumber.clear();
        verifyAccountNumber.sendKeys(accountNum);
    }

    public void enterAmount(String payAmount) {
        amount.clear();
        amount.sendKeys(payAmount);
    }

    public void selectFromAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(fromAccountDropdown));
        new Select(fromAccountDropdown).selectByIndex(0);
    }

    public void clickSendPayment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(sendPaymentButton));
        sendPaymentButton.click();
    }

    public boolean isBillPaymentCompleteDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(billPaymentCompleteHeader));
        return billPaymentCompleteHeader.isDisplayed();
    }

    public String getSuccessMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText();
    }

    public boolean isSeeAccountActivityLinkDisplayed() {
        return seeAccountActivityLink.isDisplayed();
    }
}
