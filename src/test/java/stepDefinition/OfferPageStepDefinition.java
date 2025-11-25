package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import utils.TestContextSetup;

import java.util.Iterator;
import java.util.Set;

public class OfferPageStepDefinition {
    public WebDriver driver;
    public String offerPageProductName;
    public String landingPageProductName;
    TestContextSetup testContextSetup;

    public OfferPageStepDefinition(TestContextSetup testContextSetup){
        this.testContextSetup =  testContextSetup ;
    }

    @Then("user searched for {string} shortname in offers page")
    public void user_searched_for_same_shortname_in_offers_page_to_check_if_product_exist(String shortName) throws InterruptedException {
        testContextSetup.driver.findElement(By.linkText("Top Deals")).click();
        Set<String> s1 = testContextSetup.driver.getWindowHandles();
        Iterator<String> i1 =s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        testContextSetup.driver.switchTo().window(childWindow);

        testContextSetup.driver.findElement(By.xpath("//*[@id=\"search-field\"]")).sendKeys(shortName);

        Thread.sleep(2000);
        String offerPageProductName = testContextSetup.driver.findElement(By.cssSelector("tbody tr td:nth-child(1)")).getText();
        System.out.println(offerPageProductName + " is Extracted from offer page");
    }

    @Then("validate product name in offers page matches  with Landing page")
    public void validate_product_name_in_offers_page_matches_with_Landing_page(){

        Assert.assertEquals(offerPageProductName, landingPageProductName);
    }


}
