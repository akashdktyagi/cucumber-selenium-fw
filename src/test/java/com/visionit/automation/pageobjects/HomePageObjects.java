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

public class HomePageObjects {

    private static final Logger logger = LogManager.getLogger(HomePageObjects.class);

    //Section1:  Declare a driver object
    private WebDriver driver;

    //Section 2 : Define the locators
    private String menu_item_text_element = "//button[@role='menuitem' and text()='%s']";

    //Section 3: Paratmerize the constuctor
    public HomePageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void validateMenuItemIsPresent(String menuItemName){
        logger.info("Validating the menu item is present. Menu Item name: " + menuItemName);
        Assert.assertEquals(driver.findElement(By.xpath(String.format(menu_item_text_element,menuItemName))).isDisplayed(),true);
    }

}
