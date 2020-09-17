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

public class SignInPageObjects {

    private static final Logger logger = LogManager.getLogger(SignInPageObjects.class);

    private WebDriver driver;

    private By input_textbox_email  = By.id("ap_email");

    //Section 3: Paratmerize the constuctor
    public SignInPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public void validateEmailInputTextBoxIsDisplayed(){
        if (driver.findElement(input_textbox_email).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Email input box is displayed");
        }else{
            logger.fatal("Email input box not displayed");
            Assert.fail("Email text box does not appear for login after clicking on Sign in button");
        }
    }

    public void enterTextInEmailTextBox(String text){
        logger.info("Text entered in email id: " + text);
        driver.findElement(input_textbox_email).sendKeys(text);
    }

}
