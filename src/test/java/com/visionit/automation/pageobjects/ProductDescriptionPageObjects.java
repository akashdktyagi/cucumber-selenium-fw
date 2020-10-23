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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductDescriptionPageObjects {

    private static final Logger logger = LogManager.getLogger(ProductDescriptionPageObjects.class);

    private WebDriver driver;

    private By product_title = By.id("productTitle");
    private By add_to_cart_button = By.id("add-to-cart-button");
    private By drop_down_select_quantity = By.xpath("//select[@id='quantity']"); //By.id("quantity") is giving wrong result. let me know why?
    private By text_added_to_cart_message = By.xpath("//div[@class='a-box a-alert a-alert-success added-to-cart-message-box']//h4[text()='Added to Cart']");
    private By text_added_to_cart_message_other_version = By.xpath("//h1[@class='a-size-medium a-text-bold' and contains(text(),'Added to Cart')]");


    //Section 3: Paratmerize the constuctor
    public ProductDescriptionPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnAddToCartButton(){
        driver.findElement(add_to_cart_button).click();
        logger.info("Add to cart button on product description page is clicked.");
    }

    public void selectQuantity(String quantity){
        Select select = new Select(driver.findElement(drop_down_select_quantity));
        select.selectByValue(quantity);
        logger.info("Quantity is selected as : " + quantity);
    }

    public void checkAddedToCartMessageIsDisplayed(){
        //Check Added to cart message is displayed
        try{ // try to check for first version of add to cart
            WebDriverWait wait = new WebDriverWait(driver,20);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(text_added_to_cart_message));
            Assert.assertEquals("Added to Cart Message",true,element.isDisplayed());
        }catch(Exception e){ // if first version of add to cart is not coming then check for other version
            WebDriverWait wait = new WebDriverWait(driver,20);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(text_added_to_cart_message_other_version));
            Assert.assertEquals("Added to Cart Message",true,element.isDisplayed());
        } // If both the version is not coming then test case will fail
    }

    public void ValidateProductTileIsCorrectlyDisplayed(){
        if (driver.findElement(product_title).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Product Title is displayed");
        }else{
            logger.fatal("Product Title is not displayed");
            Assert.fail("Product Title is not displayed");
        }
    }

    public void ValidateAddToCartButtonIsCorrectlyDisplayed(){
        if (driver.findElement(add_to_cart_button).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Add to Cart Button is displayed");
        }else{
            logger.fatal("Add to Cart Button is not displayed");
            Assert.fail("Add to Cart Button is not displayed");
        }
    }


}
