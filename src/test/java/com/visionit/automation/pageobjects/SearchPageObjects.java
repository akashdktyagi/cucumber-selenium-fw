package com.visionit.automation.pageobjects;

/***
 * Author: Akash Tyagi
 * Company: VisionIT
 * Date: 17-Sep-2020
 * Description: Test Automation FW development
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPageObjects {

    private static final Logger logger = LogManager.getLogger(SearchPageObjects.class);

    private WebDriver driver;

    private By search_refinement_categories_segment  = By.id("s-refinements");
    private By product_link_list = By.xpath("//a[@class='a-link-normal a-text-normal']");
    private By txtbx_minimum_price_filter = By.name("low-price");
    private By txtbx_maximum_price_filter = By.name("high-price");
    private By go_button_price_filter = By.xpath("//input[@aria-labelledby='a-autoid-1-announce']");
    private By product_price_list = By.xpath(("//span[@class='a-price-whole']"));

    //Section 3: Paratmerize the constuctor
    public SearchPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void ValidateProductSearchIsSuccessfull(){
        if (driver.findElement(search_refinement_categories_segment).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Search Page is displayed because refinement category is displayed");
        }else{
            logger.fatal("Search Page is not displayed because refinement category is not displayed");
            Assert.fail("Search Page is not displayed because refinement category is not displayed");
        }
    }

    public String ClickOnTheProductLink(int productIndex){
        //listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(product_link_list);
        logger.info("Number of products searched: " + listOfProducts.size());

        //Link on the  link with argument productIndex
        listOfProducts.get(productIndex).click();
        logger.info("Clicked on the Link in the List with index: " + productIndex +
                ". Link Text: " + listOfProducts.get(productIndex).getText());

        //return the text of the clicked link if further validation is required.
        return listOfProducts.get(productIndex).getText();

    }

    public void FilterSearchResultByPrice(String min,String max){
        driver.findElement(txtbx_minimum_price_filter).sendKeys(min);
        logger.info("Min price field set: " + min);

        driver.findElement(txtbx_maximum_price_filter).sendKeys(max);
        logger.info("Max price field set: " + max);

        driver.findElement(go_button_price_filter).click();
        logger.info("Search Price filter - Go Button clicked");

    }

    public void VerifyThatSearchedProductsAreInPriceRange(int min, int max){
        List<WebElement> product_prices = driver.findElements(product_price_list);
        logger.info("Get all the product prices");
        boolean bResult = false;
        int price_temp=0;

        for(int i=0;i<product_prices.size();i++){
            price_temp = Integer.parseInt(product_prices.get(i).getText().replace(",",""));
            if (price_temp>=min && price_temp<=max){
                bResult = true;
                logger.info("For index: " + i + " Product Price: " + price_temp + " and for Product: " + product_prices.get(i).getText());
            }else{
                bResult = false;
                logger.error("Product list is not with in Price range. Failed.");
                break;
            }
        }

        if (bResult){
            Assert.assertTrue("Search Result is with in the defined range i.e. Min: " + min + " Max: " + max,true);
            logger.info("All product is filtered with right price range. Min: " + min + " Max: " + max);
        }else{
            logger.error("All product is not filtered with right price range. Min: " + min + " Max: " + max);
            Assert.fail("Search Result is not with in the defined range i.e. Min: " + min + " Max: " + max );
        }

    }
}
