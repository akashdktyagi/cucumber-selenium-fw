package com.visionit.automation.stepdefs;

/***
 * Author: Akash Tyagi
 * Company: VisionIT
 * Date: 17-Sep-2020
 * Description: Test Automation FW development
 */

import com.visionit.automation.core.WebDriverFactory;
import com.visionit.automation.pageobjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StepDefs {

    //***********************************************************************
    //***********************Logger Init*************************************
    //***********************************************************************
    private static final Logger logger = LogManager.getLogger(StepDefs.class);

    //***********************************************************************
    //***********************Declaration*************************************
    //***********************************************************************

    WebDriver driver;
    String base_url = "https://amazon.in";
    int implicit_wait_timeout_in_sec = 20;
    Scenario scn; // this is set in the @Before method

    //***********************************************************************
    //***********************Page Object Model Declaration*******************
    //***********************************************************************
    // Declare Page Object Model (note that we are not initilizing them here)
    // Before we could init them we need to have driver initialized
    // We Will init these objects in @Before Set Up method only after Driver object in set
    CmnPageObjects cmnPageObjects;
    HomePageObjects homePageObjects;
    SignInPageObjects signInPageObjects;
    SearchPageObjects searchPageObjects;
    ProductDescriptionPageObjects productDescriptionPageObjects;

    //***********************************************************************
    //***********************HOOKS*******************************************
    //***********************************************************************

    // make sure to use this before import io.cucumber.java.Before;
    // Use @Before to execute steps to be executed before each scnerio
    // one example can be to invoke the browser
    //Scenario(see below method arg type) is a Interface, given by Cucumber;
    //This object is 'Injected' at run time and can be used for logging, screen shot attachement to reports
    //Other than that it also carries steps and scenario pass, fail status(more on this later)
    @Before
    public void setUp(Scenario scn) throws Exception {
        this.scn = scn; //Assign this to class variable, so that it can be used in all the step def methods

        //Get the browser name by default it is chrome
        String browserName = WebDriverFactory.getBrowserName();
        driver = WebDriverFactory.getWebDriverForBrowser(browserName);
        logger.info("Browser invoked.");

        //Init Page Object Model Objects
        cmnPageObjects = new CmnPageObjects(driver);
        homePageObjects = new HomePageObjects(driver);
        signInPageObjects = new SignInPageObjects(driver);
        searchPageObjects = new SearchPageObjects(driver);
        productDescriptionPageObjects = new ProductDescriptionPageObjects(driver);
    }

    // make sure to use this after import io.cucumber.java.After;
    // Use @After to execute steps to be executed after each scnerio
    // one example can be to close the browser
    // Giving this method order as 2, so that quit happens after screen shot capture.
    @After(order=1)
    public void cleanUp(){
        WebDriverFactory.quitDriver();
        scn.log("Browser Closed");
    }

    //1. Screen shot capturing is a important part of test cases failure investigation.
    //2. When test cases fails, we need to give the evidence to the person who is investigating the report of the test execution.
    //3. There can be many screen shot capturing strategies. like:
    //     -Take one screen shot when test case end (pass or fail)
    //     -Take screen shot after each line of the scenario.
    //     -Take screen shot as soon as a step fails.
    //4. Usually taking screen shot when scenario/step fails is more resonable and commonly used strategy.
    //5. To implement it we will need to know at run time if the test is pass or fail.
    //6. If the test is pass we will choose not to take a screen shot.
    //7. If the test fails we will take the screenshot and attach it with the native report.
    //8. Then can be achieved in cucumber using 'Scenario' Object injected in @After method.
    //9. So we have added another After method (you can have many after methods)
    //10.However we need to make sure, that this after method gets executed before above After method, otherwise browser will be closed by above after method and screen shot will not be captured.
    //11.To run this @After method first, we need to add the argument 'order' to this method's annotation.
    //12. Giving this method order as 2, means it will always execute first, and then giving order as 1 to the above after method.
    //    Order number is the order in which this hook should run. Higher numbers are run first. The default order is 10000.
    //13. Now since we need to capture the screen shot only after a test is failed, we will put a if condition as check the failure using method 'isFailed'.
    //14. If test is failed it will take the screen shot and attach the screen shot with the report. For this s.attach method is used. (in old version, method name was embed)
    @After(order=2)
    public void takeScreenShot(Scenario s) {
        if (s.isFailed()) {
            TakesScreenshot scrnShot = (TakesScreenshot)driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scn.attach(data, "image/png","Failed Step Name: " + s.getName());
        }else{
            scn.log("Test case is passed, no screen shot captured");
        }
    }

    //***********************************************************************
    //***********************Step Defs***************************************
    //***********************************************************************

    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {
        WebDriverFactory.navigateToTheUrl(base_url);
        scn.log("Browser navigated to URL: " + base_url);

        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        cmnPageObjects.validatePageTitleMatch(expected);
    }

    @When("User Search for product {string}")
    public void user_search_for_product(String productName) {
        cmnPageObjects.SetSearchTextBox(productName);
        cmnPageObjects.ClickOnSearchButton();
        scn.log("Product Searched: " + productName);
    }

    @Then("Search Result page is displayed")
    public void search_result_page_is_displayed() {
        searchPageObjects.ValidateProductSearchIsSuccessfull();
    }

    @When("User click on any product")
    public void user_click_on_any_product() {
        searchPageObjects.ClickOnTheProductLink(0);//0 here means click on the first link
     }

    @Then("Product Description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab() {
        WebDriverFactory.switchBrowserToTab();
        scn.log("Switched to the new window/tab");

        productDescriptionPageObjects.ValidateProductTileIsCorrectlyDisplayed();
        productDescriptionPageObjects.ValidateAddToCartButtonIsCorrectlyDisplayed();
    }

    @When("User add the products with defined price range and quantity listed below")
    public void user_add_the_products_with_defined_price_range_and_quantity_listed_below(List<Map<String,String>> data) {
        String first_product_name = data.get(0).get("ITEM");
        int first_product_price_limit = Integer.parseInt(data.get(0).get("PRICE_LESS_THAN"));
        int first_product_amount = Integer.parseInt(data.get(0).get("QUANTITY"));

//        Reusing Existing methods.
//        You can use existing Step Defs methods.
//        No issues there.
        user_search_for_product(first_product_name);

        // Get the List of Products.
        //This XPATH will get all the product links.
        //div[@class='sg-row']//a[@class='a-link-normal a-text-normal']
        List<WebElement> list_product_links = driver.findElements(By.xpath("//div[@class='sg-row']//a[@class='a-link-normal a-text-normal']"));

        //This will give all the prices corresponding to the above products.
        //We are assuming, that we have the indexes of above product matches the price in below list.
        //Most of the time this assumption is right. In this case it is certainly is.
        List<WebElement> list_product_prices = driver.findElements(By.xpath("//div[@class='sg-row']//span[@class='a-price-whole']"));

        int product_link_index=-1;
        //Loop through the List
        for (int i=0;i< list_product_prices.size();i++){
            //Value is to be captured, then , (comma) is to be removed and then it is to be converted to a integer.
            //Below all done in a single step and value stored in temp variable
            int temp = Integer.parseInt(list_product_prices.get(i).getText().replace(",",""));
            if (temp<first_product_price_limit){// if product is less then the price mentioned
                product_link_index = i;
                break;
            }
        }

        //if a product with required price is found then click on the link.
        //product_link_index variable will have index
        list_product_links.get(product_link_index).click();

        //Product description page will be opened
        product_description_is_displayed_in_new_tab();

    }

    @Then("User cart is updated with the products and quantity")
    public void user_cart_is_updated_with_the_products_and_quantity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
