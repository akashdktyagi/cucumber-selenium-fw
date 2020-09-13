# cucumber-selenium-fw

Steps to Do
---
### 1. Project Set Up
Reference Branch: ```1-add-steps-to-do-in-read-me-file```
1. Create Maven project
2. Add 5 dependencies in pom as mentioned below:
    * Cucumber-jvm
    * Cucumber-junit
    * Cucumber-pico-container(for dependency injection)
    * Junit 4 Library
    * Selenium Library
    


```
   <dependencies>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-picocontainer</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>
```
---
### 2. Add Feature File
Reference Branch: ```2-add-feature-file```
1. Create a new feature file under test->resources-features package.
2. Add new Healthcheck feature as below:
```
@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    Given User opened browser
    And User navigated to the home application url
    When User Search for product "Laptop"
    Then Search Result page is displayed
```
---

### 3. Add Runner File
Reference Branch: ```3-add-runner-file```
1. Under ```test.java.com.visionit.automation``` package create package ```runners```.
2. Add a Class and add Junit annotation ```@RunWith(Cucumbers.class```
3. This annotation will instruct Junit to trigger the test cases using Cucumber class.
4. Add ```@CucumberOptions``` annotation on top of Junit Class file. Under this cucumber options we have to list the cucumber configurations.

Check Below file:

```$xslt
TestRunner.java File
package com.visionit.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:features",//to tell cucumber where is ur feature file
        glue="stepdefs", // to tell cucumber where is ur step def code
        tags="", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
            "html:target/html/",
            "json:target/json/file.json",
            },
        publish=true,
        dryRun=true // to tell whether to test run(true) or actual run(false)
        )
public class TestRunner {
    //Class will be Emptity.
    //Nothing goes here
    //So do not get confused
}
```
---
### 4. Generate Step Defs and Add in stepdefs package
Reference Branch: ```4-generate-and-add-step-defs-file```
1. Right click on the TestRunner.java file and click on Run
2. The run will fail with the below message and it will generate the method definition for you.
3. Copy this method definition from the console and paste it in the newly created java file.
4. Name this new package 'stepdefs' under: ```test.java.com.visionit.automation```
5. Create New java file as StepDefs.java under package: ```test.java.com.visionit.automation.stepdefs```

```The step "User opened browser" is undefined. You can implement it using the snippet(s) below:```

New Java Step Definition file will look like:
```$xslt
package com.visionit.automation.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
    @Given("User opened browser")
    public void user_opened_browser() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("User Search for product {string}")
    public void user_search_for_product(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("Search Result page is displayed")
    public void search_result_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}

```
---
### 5 .Add Selenium Steps in Step Defs Java File
Reference Branch: ```5-write-selenium-steps-in-step-def-file```

1. GO to Step Def file
2. Declare WebDriver at the top
3. Init WebDriver=chrome Driver under method ```user_opened_browser``` and similarly other selenium steps will go under respective methods.
4. Go to Test Runner file and change the dryRun=false
5. Run the code. Simple Right click and run.
6. Test will run and there will be a report link, open it. This will only work if you have publish=true in cucumber options under TestRunner.java file.
7. Go to /target/html/htmlreport.html; this also is a report cucumber generated.

Check in the reference branch as well as below. For exact working code select the right branch in git hub and navigate to the file.

Runner file should like below:
```$xslt
@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:features",//to tell cucumber where is ur feature file
        glue="com.visionit.automation.stepdefs", // to tell cucumber where is ur step def code
        tags="", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
            "html:target/html/htmlreport.html",
            "json:target/json/file.json",
            },
        publish=true,
        dryRun=false // to tell whether to test run(true) or actual run(false)
        )
public class TestRunner {
    //Class will be Emptity.
    //Nothing goes here
    //So do not get confused
}
```

Step Defs File will look like Below:
```$xslt
package com.visionit.automation.stepdefs;

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

import java.util.concurrent.TimeUnit;

public class StepDefs {

    WebDriver driver;
    String base_url = "https://amazon.in";
    int implicit_wait_timeout_in_sec = 20;


    @Given("User opened browser")
    public void user_opened_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
    }

    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {
        driver.get(base_url);
        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual =driver.getTitle();
        Assert.assertEquals("Page Title validation",expected,actual);
    }

    @When("User Search for product {string}")
    public void user_search_for_product(String productName) {
        //Wait and Search for product
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

        elementSearchBox.sendKeys(productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
    }

    @Then("Search Result page is displayed")
    public void search_result_page_is_displayed() {
        //Wait for titile
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));

        //Assertion for Page Title
        Assert.assertEquals("Page Title validation","Amazon.in : Laptop", driver.getTitle());
    }
}
```
---
### 6. Add new Test Case for Product Description
Reference branch: ```6-add-new-scn-clik-on-product```

1. Add new Scenario in the Feature File of product Description.
Notice that I am reusing the steps created in the previous scenerio
2. Run the runner file with dryRun flag as ```true```
3. This will generate the steps for the new steps.
4. Copy these new steps in the StepDefs file and write corresponding selenium code.

```$xslt
  Scenario: User is click on the Product and check the Product Details
    Given User opened browser
    And User navigated to the home application url
    And User Search for product "Laptop"
    When User click on any product
    Then Product Description is displayed in new tab

New Steps Created, when you run the runner file:

@When("User click on any product")
public void user_click_on_any_product() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}


Some other steps were also undefined:

@Then("Product Description is displayed in new tab")
public void product_description_is_displayed_in_new_tab() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```
Write step def for Click on any product:
```$xslt
    @When("User click on any product")
    public void user_click_on_any_product() {
        //listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));

        //But as this step asks click on any link, we can choose to click on Index 0 of the list
        listOfProducts.get(0).click();
    }

   @Then("Product Description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab() {
        //As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String original = it.next();//gives the parent window id
        String prodDescp = it.next();//gives the child window id

        driver.switchTo().window(prodDescp); // switch to product Descp

        //Now driver can access new driver window, but can not access the orignal tab
        //Check product title is displayed
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        Assert.assertEquals("Product Title",true,productTitle.isDisplayed());

        WebElement addToCartButton = driver.findElement(By.xpath("//button[@title='Add to Shopping Cart']"));
        Assert.assertEquals("Product Title",true,addToCartButton.isDisplayed());

        //Switch back to the Original Window, however no other operation to be done
        driver.switchTo().window(original);
        
    }
```
-----
### Cucumber Hooks @Before and @After Hooks for Browser Invoke and Browser Close Operation
Reference Branch: ```7-cucumber-hooks-before-after```
1. Browser Open and Close Operation can be taken care by @Before and @After hooks. 
2. These are similar to Junit After and Before hooks, i.e. they get eexecuted automatically 'before' and'after' each scenario.
3. Therefore as a part of this modification, I am not using step to invoke the browser, check the feature file in the reference branch folder structure.
4. Putting in files here as well.
```$xslt
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    Given User navigated to the home application url
    When User Search for product "Laptop"
    Then Search Result page is displayed

  Scenario: User is click on the Product and check the Product Details
    Given User navigated to the home application url
    And User Search for product "earphone"
    When User click on any product
    Then Product Description is displayed in new tab

Step Defs Code Snippet:
    // make sure to use this before import io.cucumber.java.Before;
    // Use @Before to execute steps to be executed before each scnerio
    // one example can be to invoke the browser
    @Before
    public void setUp(){
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
    }

    //Do not need to use this method
    @Given("User opened browser")
    @Deprecated
    public void user_opened_browser() {
        //We do need this step now, because steps to invoke the browser
        //has been moved to  @Before cucumber method
    }

```
---