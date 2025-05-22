package selenium_project.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/selenium_project/cucumber",
        glue = "selenium_project.stepDefinition",
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"},
        monochrome = true, 
        tags = "@ErrorValidation"
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    // This class will be empty. It will be used only as a holder for the above annotations.
    // The CucumberOptions annotation is used to specify the location of the feature files and step definitions.
    // The TestNGTestRunner class extends AbstractTestNGCucumberTests to integrate Cucumber with TestNG.
    
}