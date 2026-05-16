package StepDefinitions;

import Factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;

public class Hooks {
    WebDriver driver;
    Properties p;

    @Before
    public void setUp() throws IOException {
        driver = BaseClass.initializeDriver();
        driver.manage().window().maximize();
        p = BaseClass.getProperties();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @AfterStep
    public void FailureScreenshots(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            // Code to capture screenshot on failure
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
