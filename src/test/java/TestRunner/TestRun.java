package TestRunner;

import org.junit.runner.RunWith;
@RunWith(io.cucumber.junit.Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = "C:/Users/DharmarajG/IdeaProjects/ParaBank_CucumberBDD_Framework/featureFile",
        glue = "StepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRun {

}
