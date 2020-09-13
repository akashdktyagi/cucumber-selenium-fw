package com.visionit.automation.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StepDefs {

    WebDriver driver;
    String base_url = "https://amazon.in";
    int implicit_wait_timeout_in_sec = 20;
    Scenario scn; // this is set in the @Before method

    // make sure to use this before import io.cucumber.java.Before;
    // Use @Before to execute steps to be executed before each scnerio
    // one example can be to invoke the browser
    //Scenario(see below method arg type) is a Interface, given by Cucumber;
    //This object is 'Injected' at run time and can be used for logging, screen shot attachement to reports
    //Other than that it also carries steps and scenario pass, fail status(more on this later)
    @Before
    public void setUp(Scenario scn){
        this.scn = scn; //Assign this to class variable, so that it can be used in all the step def methods
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
    }

    // make sure to use this after import io.cucumber.java.After;
    // Use @After to execute steps to be executed after each scnerio
    // one example can be to close the browser
    @After
    public void cleanUp(){
        driver.quit();
        scn.log("Browser Closed");
    }

    //Do not need to use this method
    @Given("User opened browser")
    @Deprecated
    public void user_opened_browser() {
        //We do need this step now, because steps to invoke the browser
        //has been moved to  @Before cucumber method
    }

    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {
        driver.get(base_url);
        scn.log("Browser navigated to URL: " + base_url);

        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual =driver.getTitle();
        Assert.assertEquals("Page Title validation",expected,actual);

        scn.log("Page title validation successfull. Actual title: " + actual );
    }

    @When("User Search for product {string}")
    public void user_search_for_product(String productName) {
        //Wait and Search for product
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

        elementSearchBox.sendKeys(productName);
        scn.log("Product Searched: " + productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        scn.log("Clicked on the GO butotn");
    }

    @Then("Search Result page is displayed")
    public void search_result_page_is_displayed() {
        //Wait for titile
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));

        //Assertion for Page Title
        Assert.assertEquals("Page Title validation","Amazon.in : Laptop", driver.getTitle());
        scn.log("Page title validation successfull: " + driver.getTitle());
    }

    @When("User click on any product")
    public void user_click_on_any_product() {
        //listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));

        scn.log("Number of products searched: " + listOfProducts.size());

        //But as this step asks click on any link, we can choose to click on Index 0 of the list
        listOfProducts.get(0).click();
        scn.log("Click on the first Link in the List. Link Text: " + listOfProducts.get(0).getText());
    }

    @Then("Product Description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab() {
        //As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        scn.log("List of windows found: "+handles.size());
        scn.log("Windows handles: " + handles.toString());
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String original = it.next();//gives the parent window id
        String prodDescp = it.next();//gives the child window id

        driver.switchTo().window(prodDescp); // switch to product Descp
        scn.log("Switched to the new window/tab");

        //Now driver can access new driver window, but can not access the orignal tab
        //Check product title is displayed
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        Assert.assertEquals("Product Title",true,productTitle.isDisplayed());
        scn.log("Product Title header is matched and displayed as: " + productTitle.getText() );

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        Assert.assertEquals("Add to Cart Button",true,addToCartButton.isDisplayed());
        scn.log("Add to cart Button is displayed");

        //Switch back to the Original Window, however no other operation to be done
        driver.switchTo().window(original);
        scn.log("Switched back to Original tab");

    }
}
