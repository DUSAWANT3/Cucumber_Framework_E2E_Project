package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.LandingPage;
import utils.TestContextSetup;

import java.util.Iterator;
import java.util.Set;

public class LandingPageStepDefinition {
    public WebDriver driver;
    public String offerPageProductName;
    public String landingPageProductName;
    TestContextSetup testContextSetup;

 //Spring Framework, EJB
    //Constructer
 public    LandingPageStepDefinition(TestContextSetup testContextSetup){
     this.testContextSetup =  testContextSetup ;
 }

    @Given("User is on GreenCart Landing page")
    public void user_is_on_green_cart_landing_page() {
        testContextSetup.driver = new ChromeDriver();
        testContextSetup.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }
    @When("user searched with Shortname {string} and extracted actual name of product")
    public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {

        LandingPage landingPage = new LandingPage(testContextSetup.driver);
        landingPage.searchItem(shortName);

        testContextSetup.driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(shortName);

        Thread.sleep(2000);
        testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();

        System.out.println(landingPageProductName + " is Extracted from home page");
    }


}
