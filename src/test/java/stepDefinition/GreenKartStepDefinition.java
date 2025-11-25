package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class GreenKartStepDefinition {
    public WebDriver driver;
    public String offerPageProductName;
    public String landingPageProductName;

    @Given("User is on GreenCart Landing page")
    public void user_is_on_green_cart_landing_page() {
         driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }
    @When("user searched with Shortname {string} and extracted actual name of product")
    public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
        driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(shortName);
        String landingPageProductName = driver.findElement(By.xpath("//*[@class=\"product-name\"]"))
                .getText().split("-")[0].trim();

        //Thread.sleep(2000);
        System.out.println(landingPageProductName + " is Extracted from home page");
    }


    @Then("user searched for {string} shortname in offers page")
    public void user_searched_for_same_shortname_in_offers_page_to_check_if_product_exist(String shortName) throws InterruptedException {
        driver.findElement(By.linkText("Top Deals")).click();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 =s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        driver.switchTo().window(childWindow);

        driver.findElement(By.xpath("//*[@id=\"search-field\"]")).sendKeys(shortName);

        Thread.sleep(2000);
        String offerPageProductName = driver.findElement(By.cssSelector("tbody tr td:nth-child(1)")).getText();
        System.out.println(offerPageProductName + " is Extracted from offer page");
    }

    @Then("validate product name in offers page matches  with Landing page")
    public void validate_product_name_in_offers_page_matches_with_Landing_page(){

        Assert.assertEquals(offerPageProductName, landingPageProductName);
    }


}
