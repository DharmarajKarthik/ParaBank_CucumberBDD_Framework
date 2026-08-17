package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RequestLoanPage extends DriverBasePage {

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Request Loan']")
    WebElement requestLoanLink;

    @FindBy(id = "amount")
    WebElement loanAmountField;

    @FindBy(id = "downPayment")
    WebElement downPaymentField;

    @FindBy(id = "fromAccountId")
    WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Apply Now']")
    WebElement applyNowButton;

    @FindBy(xpath = "//h1[normalize-space()='Loan Request Processed']")
    WebElement loanProcessedHeader;

    @FindBy(xpath = "//td[normalize-space()='Approved']")
    WebElement approvedStatus;

    @FindBy(xpath = "//p[contains(text(),'Congratulations, your loan has been approved')]")
    WebElement approvedMessage;

    @FindBy(id = "newAccountId")
    WebElement newLoanAccountId;

    // Negative: loan denied elements
    @FindBy(xpath = "//td[normalize-space()='Denied']")
    WebElement deniedStatus;

    @FindBy(xpath = "//p[@class='error' or contains(@id,'error')]")
    WebElement errorMessage;

    public void clickRequestLoanLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(requestLoanLink));
        requestLoanLink.click();
    }

    public void enterLoanAmount(String amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(loanAmountField));
        loanAmountField.clear();
        loanAmountField.sendKeys(amount);
    }

    public void enterDownPayment(String downPayment) {
        downPaymentField.clear();
        downPaymentField.sendKeys(downPayment);
    }

    public void selectFromAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(fromAccountDropdown));
        new Select(fromAccountDropdown).selectByIndex(0);
    }

    public void clickApplyNow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(applyNowButton));
        applyNowButton.click();
    }

    public boolean isLoanRequestProcessedDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(loanProcessedHeader));
        return loanProcessedHeader.isDisplayed();
    }

    public boolean isLoanApproved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(approvedStatus));
        return approvedStatus.isDisplayed();
    }

    public boolean isLoanDenied() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(deniedStatus));
        return deniedStatus.isDisplayed();
    }

    public String getNewLoanAccountId() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(newLoanAccountId));
        return newLoanAccountId.getText();
    }
}
