package cucumberOptions;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/searchProduct.feature",
        glue = "stepDefinition",
        monochrome = true    //Cucumber output more readable
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
