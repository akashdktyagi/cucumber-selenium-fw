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

        WebDriverFactory.navigateToTheUrl(base_url);
        scn.log("Browser navigated to URL: " + base_url);

        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        cmnPageObjects.validatePageTitleMatch(expected);

        String sdfsf = "{\n" +
                "  \"content\": [\n" +
                "    {\n" +
                "      \"fullName\": \"string\",\n" +
                "      \"userName\": \"string\"\n" +
                "    }\n" +
                "  ],";
    }

    // make sure to use this after import io.cucumber.java.After;
    // Use @After to execute steps to be executed after each scnerio
    // one example can be to close the browser
    // Giving this method order as 2, so that quit happens after screen shot capture.
    @After(order=1)
    public void cleanUp(){
        //WebDriverFactory.quitDriver();
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

    @Then("User cart is updated with the products and quantity")
    public void user_cart_is_updated_with_the_products_and_quantity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
        //This part is pending
        //Click on the cart button on top right
        //Check all the products with the right products and prices and quantity are added
        //Total is correct
    }

    //NOTE: In order to simplify explanation, I am not using Page Object Model in this method.
    //I am directly using xpaths in the Step Defs. This is not right and these xpaths have to
    //to be moved to respective page object model file.
    @When("User add the products with defined price range and quantity listed below")
    public void user_add_the_products_with_defined_price_range_and_quantity_listed_below(List<Map<String,String>> data) {
        for (int i=0; i<=data.size()-1;i++){
            searchAndAddProducts(data,i);
            scn.log("First Product added and searched. " + data.get(i).toString());
        }
    }

    // Common Method to Iterated
    public void searchAndAddProducts(List<Map<String,String>> data, int index){
        String product_name = data.get(index).get("ITEM");
        int product_price_limit = Integer.parseInt(data.get(index).get("PRICE_LESS_THAN"));
        String product_quantity = data.get(index).get("QUANTITY");

        //Reusing Existing methods.
        //You can use existing Step Defs methods.
        //No issues there.
        user_search_for_product(product_name);

        //Get the List of Products.
        //This XPATH will get all the product links.
        //div[@class='sg-row']//a[@class='a-link-normal a-text-normal']
        List<WebElement> list_product_links = driver.findElements(By.xpath("//div[@class='sg-row']//a[@class='a-link-normal a-text-normal']"));

        //This will give all the prices corresponding to the above products.
        //We are assuming, that indexes of above product links matches the price list indexes in below list.
        //Most of the time this assumption is right. We need to inspect the element to check the assumption is right.
        //In this case this assumption certainly is right.
        List<WebElement> list_product_prices = driver.findElements(By.xpath("//div[@class='sg-row']//span[@class='a-price-whole']"));

        int product_link_index = -1;// this value is kept negative, to check later
        //Loop through the List
        for (int i=0;i< list_product_prices.size();i++){
            //Value is to be captured, then , (comma) is to be removed and then it is to be converted to a integer.
            //Below all done in a single step and value stored in temp variable
            int temp = Integer.parseInt(list_product_prices.get(i).getText().replace(",",""));
            if (temp<product_price_limit){// if product is less then the price mentioned
                product_link_index = i;
                scn.log("Product found with in the price range. ");
                break;
            }
        }

        //If no product is found in the above loop.
        if (product_link_index==-1){
            scn.log("No product found with in the price range");
            Assert.fail("No product found on page 1 which has price less then mentioned amount");
        }

        //if a product with required price is found then click on the link.
        //Save the name of the Product
        String product_text = list_product_links.get(product_link_index).getText();
        scn.log("Product found with in the price range: " + product_text);
        list_product_links.get(product_link_index).click();

        //Product description page will be opened
        product_description_is_displayed_in_new_tab();
        scn.log("Product Description is displayed in new tab.");

        //On Product Description Page Select Quantity as mentioned in the feature file
        productDescriptionPageObjects.selectQuantity(product_quantity);
        scn.log("Quantity Selected. " + product_quantity);

        //Click on add to cart Button on product Description Page
        productDescriptionPageObjects.clickOnAddToCartButton();
        scn.log("Add to cart to button clicked.");

        /*
        Note: Checking Added to Cart Text is displayed is not enough to check the feature.
                You need to add more validation steps like:
                a. Checking that the right product is added by matching the text of the product
                b. Check the right price is displayed.
                c. If quantity is more than 1, then price is to be multiplied by the quantity and validated.

                I am skipping this validation steps and leaving up to the student to implement it.
                Some more complex logic has to be written to implement these validations.
                I recommend students is to try writing this logic as well, at least the price logic should be there.
         */
        //Checking the Added to Cart Text is displayed
        productDescriptionPageObjects.checkAddedToCartMessageIsDisplayed();
        scn.log("Add to cart message is displayed");

        //Close the open Product Descp page.
        //Notice we are using driver.close and not driver.quit.
        //Driver.close will only close product description tab
        driver.close();
        scn.log("Product description tab is closed.");

        //Clean up for this method is to switch to the original window
        //Because we need to search new product there again
        //However you can continue to use the same window to search for new products
        WebDriverFactory.switchToOriginalTab();
        scn.log("Driver switched to original tab/window");
    }


    @When("User enters minimum price as {string} and maximum price as {string} mentioned in below table")
    public void user_enters_minimum_price_as_and_maximum_price_as_mentioned_in_below_table(String min, String max) {
        searchPageObjects.FilterSearchResultByPrice(min,max);
    }

    @Then("Verify that Search results gets filtered with price range between {int} and {int}")
    public void search_results_gets_filtered_with_price_range_between_and(int min, int max) {
        searchPageObjects.VerifyThatSearchedProductsAreInPriceRange(min,max);
    }

}
