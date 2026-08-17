package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountsOverviewPage extends DriverBasePage {

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Accounts Overview']")
    WebElement accountsOverviewLink;

    @FindBy(id = "accountTable")
    WebElement accountTable;

    @FindBy(xpath = "//table[@id='accountTable']//th")
    List<WebElement> tableHeaders;

    @FindBy(xpath = "//table[@id='accountTable']//tbody//tr[not(contains(@class,'footer'))]")
    List<WebElement> accountRows;

    public void clickAccountsOverviewLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(accountsOverviewLink));
        accountsOverviewLink.click();
    }

    public boolean isAccountTableDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(accountTable));
        return accountTable.isDisplayed();
    }

    public List<String> getTableHeaders() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
        return tableHeaders.stream().map(WebElement::getText).toList();
    }

    public int getAccountRowCount() {
        return accountRows.size();
    }

    public boolean hasAccountRows() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(accountTable));
        return !accountRows.isEmpty();
    }

    public String getFirstAccountBalance() {
        return driver.findElement(By.xpath("//table[@id='accountTable']//tbody//tr[1]/td[2]")).getText();
    }
}
