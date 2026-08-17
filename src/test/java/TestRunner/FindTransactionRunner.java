package TestRunner;

import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = "featureFile/FindTransaction.feature",
        glue = "StepDefinitions",
        plugin = {"pretty",
                "html:target/cucumber-reports/find-transaction.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        publish = true,
        monochrome = true
)
public class FindTransactionRunner {
}
