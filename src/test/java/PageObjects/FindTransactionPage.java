package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FindTransactionPage extends DriverBasePage {

    public FindTransactionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Find Transactions']")
    WebElement findTransactionLink;

    @FindBy(id = "accountId")
    WebElement accountDropdown;

    @FindBy(id = "transactionId")
    WebElement transactionIdField;

    @FindBy(id = "transactionDate")
    WebElement transactionDateField;

    @FindBy(id = "fromDate")
    WebElement fromDateField;

    @FindBy(id = "toDate")
    WebElement toDateField;

    @FindBy(id = "amount")
    WebElement amountField;

    @FindBy(xpath = "//*[@ng-click='findById()']")
    WebElement findByIdButton;

    @FindBy(xpath = "//*[@ng-click='findByDate()']")
    WebElement findByDateButton;

    @FindBy(xpath = "//*[@ng-click='findByDateRange()']")
    WebElement findByDateRangeButton;

    @FindBy(xpath = "//*[@ng-click='findByAmount()']")
    WebElement findByAmountButton;

    @FindBy(xpath = "//table[@id='transactionTable'] | //table[contains(@class,'table')]")
    WebElement transactionTable;

    @FindBy(xpath = "//span[@ng-show='noTransactions']")
    WebElement noTransactionsMessage;

    public void clickFindTransactionLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findTransactionLink));
        findTransactionLink.click();
    }

    public void selectAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(accountDropdown));
        new Select(accountDropdown).selectByIndex(0);
    }

    public void enterTransactionId(String txnId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(transactionIdField));
        transactionIdField.clear();
        transactionIdField.sendKeys(txnId);
    }

    public void enterTransactionDate(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(transactionDateField));
        transactionDateField.clear();
        transactionDateField.sendKeys(date);
    }

    public void enterDateRange(String fromDate, String toDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(fromDateField));
        fromDateField.clear();
        fromDateField.sendKeys(fromDate);
        toDateField.clear();
        toDateField.sendKeys(toDate);
    }

    public void enterAmount(String amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public void clickFindByAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findByAmountButton));
        findByAmountButton.click();
    }

    public void clickFindByDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findByDateButton));
        findByDateButton.click();
    }

    public void clickFindByDateRange() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findByDateRangeButton));
        findByDateRangeButton.click();
    }

    public boolean isTransactionTableDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(transactionTable));
        return transactionTable.isDisplayed();
    }

    public List<WebElement> getTransactionRows() {
        return driver.findElements(By.xpath("//table[@id='transactionTable']//tbody//tr | //table[contains(@ng-show,'transactionList')]//tbody//tr"));
    }

    public boolean isNoTransactionMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(noTransactionsMessage));
        return noTransactionsMessage.isDisplayed();
    }
}
