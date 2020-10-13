# cucumber-selenium-fw

Steps to Do
---

This Framework has been created by Akash Tyagi and Sarang Holey. 
It has been created for training purposes. However, is still industry standard and can be used in any actual live project.
You can reach out to us at: akashdktyagi@gmail.com/sarangholey@gmail.com

### 1. Project Set Up
Reference Branch: ```1-add-steps-to-do-in-read-me-file```
1. Create Maven project
2. Add 5 dependencies in pom as mentioned below:
    * Cucumber-jvm
    * Cucumber-junit
    * Cucumber-pico-container(for dependency injection)
    * Junit 4 Library
    * Selenium Library

<details>
  <summary> Click to see code.</summary>
  
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

</details>

---
### 2. Add Feature File
Reference Branch: ```2-add-feature-file```
1. Create a new feature file under test->resources-features package.
2. Add new Healthcheck feature as below:

<details>
  <summary> Click to see code.</summary>
  
  
```
@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    Given User opened browser
    And User navigated to the home application url
    When User Search for product "Laptop"
    Then Search Result page is displayed
```

</details>

---

### 3. Add Runner File
Reference Branch: ```3-add-runner-file```
1. Under ```test.java.com.visionit.automation``` package create package ```runners```.
2. Add a Class and add Junit annotation ```@RunWith(Cucumbers.class```
3. This annotation will instruct Junit to trigger the test cases using Cucumber class.
4. Add ```@CucumberOptions``` annotation on top of Junit Class file. Under this cucumber options we have to list the cucumber configurations.

Check Below file:

<details>
  <summary> Click to see code.</summary>
  
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

</details>

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

<details>
  <summary> Click to see code.</summary>
  
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

</details>

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

<details>
  <summary> Click to see code.</summary>
  
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

</details>

---
### 6. Add new Test Case for Product Description
Reference branch: ```6-add-new-scn-clik-on-product```

1. Add new Scenario in the Feature File of product Description.
Notice that I am reusing the steps created in the previous scenerio
2. Run the runner file with dryRun flag as ```true```
3. This will generate the steps for the new steps.
4. Copy these new steps in the StepDefs file and write corresponding selenium code.

<details>
  <summary> Click to see code.</summary>
  
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

</details>

-----
### 7. Cucumber Hooks @Before and @After Hooks for Browser Invoke and Browser Close Operation
Reference Branch: ```7-cucumber-hooks-before-after```
1. Browser Open and Close Operation can be taken care by @Before and @After hooks. 
2. These are similar to Junit After and Before hooks, i.e. they get eexecuted automatically 'before' and'after' each scenario.
3. Therefore as a part of this modification, I am not using step to invoke the browser, check the feature file in the reference branch folder structure.
4. Putting in files here as well.

<details>
  <summary> Click to see code.</summary>
  
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

</details>

---
### 8. Cucumber Scnerio Interface/Object Injections
Reference Branch: 8-scenario-interface-injection

1. Scenario(see below method arg type) is a Interface, given by Cucumber. This is native 'dependency injection' given by cucumber. Explicit dependency injection fw, which we will use later is 'Pico Container'.
2. This object is 'Injected' at run time and can be used for logging, screen shot attachement to reports etc.
3. Other than that it also carries steps and scenario pass, fail status(more on this later).
4. After this is injected in the before method, it has to be assigned to a class variable and it can be used in all the step def methods with in the class.
5. Check this link for more details on this:
https://cucumber.io/docs/cucumber/api/#hooks
6. Check the reports and witness how these logs are displayed in the report.

<details>
  <summary> Click to see code.</summary>
  
```$xslt

   WebDriver driver;
    String base_url = "https://amazon.in";
    int implicit_wait_timeout_in_sec = 20;
    Scenario scn; // this is set in the @Before method

    //Scenario(see below method arg type) is a Interface, given by Cucumber;
    //This object is 'Injected' at run time and can be used for logging, screen shot attachement to reports
    //Other than that it also carries steps and scenario pass, fail status(more on this later)
    @Before
    public void setUp(Scenario scn){
        this.scn = scn; // Assign this to class variable, so that it can be used in all the step def methods
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
    }

    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {
        driver.get(base_url);
        //Can be used in all the methods like this to log additional info about the step
        scn.log("Browser navigated to URL: " + base_url);

        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual =driver.getTitle();
        Assert.assertEquals("Page Title validation",expected,actual);

        scn.log("Page title validation successfull. Actual title: " + actual );
    }
```

</details>

 ### 9. Log 4j Impl for Logging
 Reference Branch: ```9-log4j-impl-for-logging```
 
 1. Add Log4 J 2 Depdendency in POM.
 2. Add Log4j2.xml under ```test.resources``` package.
 3. On top of each class create the statement with its class name.
 4. Based on the info mentioned in the log4j2.xml configuration file, logs will be printed on the console as well as a new file will be created every time you run the framework.
    ```    private static final Logger logger = LogManager.getLogger(TestCases_1.class);```
5. . Make sure to replace the class name in above statement. For example, for class: "StepDefs.Java", then statement will look like:
    ```private static final Logger logger = LogManager.getLogger(StepDefs.class);``` 
6. Logger statements can then be used in where in the class. To make things simple, you can chosse to write for all the information logs: ```Log.info("<info messages>")```.
7. And for any where you want to log errors, mention, ```Log.fatal or Log.error```
8. There are other log levels as well like debug, trace etc. For details on this you can check this link: https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm

<details>
  <summary> Click to see code.</summary>
  
```$xslt
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.13.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.13.1</version>
        </dependency>
```
 Example:
 
 ``` logger.info("Page Title validation successfull. Expected and actual text matched. Text: " + actual );```
 
 Log4j2.xml file:
 
 ```$xslt
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn">
    <Properties>
        <!-- Setting for creating log file for each run -->
        <property name="filePattern">${date:yyyy-MM-dd-HH_mm_ss}</property>
    </Properties>

    <Appenders>
        <!-- Setting for creating log file for each run -->
        <File name="File" fileName="app_${filePattern}.log" append="false">
            <PatternLayout
                    pattern="%d{yyyy-MMM-dd HH:mm:ss a} [%t] %-5level %logger{36} - %msg%n" />
        </File>
        <!-- Setting for creating log file on each day basis -->
        <!--Uncomment this if you want ROLLING File i.e. logs will be in the same file for each day.
        <!-- A rolling file Example
        <RollingFile name="fileLogger" fileName="app-info.log" filePattern="app-info-%d{yyyy-MM-dd}.log">
            <PatternLayout>.
                <pattern>[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n</pattern>
            </PatternLayout>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>
        -->

        <Console name="console" target="SYSTEM_OUT">
            <PatternLayout   pattern="[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n" />
        </Console>
    </Appenders>
    <Loggers>

        <Root level="debug" additivity="false">
            <appender-ref ref="File" />
            <appender-ref ref="console" />
        </Root>
    </Loggers>
</Configuration>
        <!--
        <Logger name="com." level="debug" additivity="true">
            <appender-ref ref="fileLogger" level="debug" />
        </Logger>
        -->
```

</details>

### 10. Page Object Model:
Reference Branch: ```10-page-object-model```

1. Page Object Model is used to store all locators of the page in a single class file.
2. All the locators will be marked as private.
3. Declare WebDriver at the top.
4. Create a paramatrized constructor and pass driver object while creating page object model class object.
5. Inside the constructor assign the instance driver object with the passed driver object. ```this.driver = driver```
6. All the instance variables should be marked as private, including web driver and locators.
7. Create public methods to perform operations on these locators.
8. Create public methods to perform validations.

Check below example, for better understanding go to branch and check.

Page Object Model file:

<details>
  <summary> Click to see code.</summary>
  
```$xslt
public class SearchPageObjects {

    private static final Logger logger = LogManager.getLogger(SearchPageObjects.class);

    private WebDriver driver;

    private By search_refinement_categories_segment  = By.id("s-refinements");
    private By product_link_list = By.xpath("//a[@class='a-link-normal a-text-normal']");

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
}

```

</details>

How to use it in Step Defs:

<details>
  <summary> Click to see code.</summary>
  
Declare the objects
```$xslt
    CmnPageObjects cmnPageObjects;
    HomePageObjects homePageObjects;
    SignInPageObjects signInPageObjects;
    SearchPageObjects searchPageObjects;
    ProductDescriptionPageObjects productDescriptionPageObjects;
```

Initialize the objects in the @Before method:

```$xslt
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

```

Call methods using page object:
```    @Given("User navigated to the home application url")
       public void user_navigated_to_the_home_application_url() {
           WebDriverFactory.navigateToTheUrl(base_url);
           scn.log("Browser navigated to URL: " + base_url);
   
           String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
           cmnPageObjects.validatePageTitleMatch(expected);
       }
```

</details>
### 11. Web Driver Manager Methods:
Reference Branch: ```11-webdriver-methods```

1. Used Factory method to return driver to be executed against.
2. Driver can also be sent from mvn command line.
3. If no Browser is sent then chrome is the default Browser.
4. mvn command: ```mvn clean verify``` : this will run on chrome, since no browser is mentioned.
5. mvn command: ```mvn clean verify -Dbrowser=firefox``` :  this will run on firefox
6. mvn command: ```mvn clean verify -Dbrowser=headless``` : this will run on headless browser, i.e. no browser will be opened and tests will be executed on a head less browser

Check the code here:

WebDriverFactory Class:

<details>
  <summary> Click to see code.</summary>
  
```$xslt
public class WebDriverFactory {
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver=null;
    public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
        switch(browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                logger.info("Chrome Browser invoked");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                logger.info("Firefox Browser invoked");
                break;
            case "headless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);
                logger.info("Headless Chrome Browser invoked");
                break;
            default:
                logger.fatal("No such browser is implemented.Browser name sent: " + browser);
                throw new Exception("No such browser is implemented.Browser name sent: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        logger.info("Driver maximized and implicit time out set to 20 seconds");
        return driver;
    }

    public static void navigateToTheUrl(String url){
        driver.get(url);
        logger.info("Browser navigated to the url: " + url);
    }

    public static void quitDriver(){
        driver.quit();
        logger.info("Driver closed");
    }
    public static void switchBrowserToTab(){
        //As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        logger.info("List of windows found: "+handles.size());
        logger.info("Windows handles: " + handles.toString());
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String original = it.next();//gives the parent window id
        String nextTab = it.next();//gives the child window id
        driver.switchTo().window(nextTab); // switch to product Descp
        logger.info("Switched to the new window/tab");
    }

    public static String getBrowserName(){
        String browserDefault = "chrome"; //Set by default
        String browserSentFromCmd = System.getProperty("browser");

        if (browserSentFromCmd==null){
            return browserDefault;
        }else{
            return browserSentFromCmd;
        }
    }

}
```

How to use:
```$xslt
    @Before
    public void setUp(Scenario scn) throws Exception {
        this.scn = scn; //Assign this to class variable, so that it can be used in all the step def methods

        //Get the browser name by default it is chrome
        String browserName = WebDriverFactory.getBrowserName();
        driver = WebDriverFactory.getWebDriverForBrowser(browserName);
        logger.info("Browser invoked.");
      }
```

</details>


### 12. Screen shot capturing on test scenario failures
Reference Branch: ```12-capture-screenshot-on-failure```

1. Screen shot capturing is a important part of test cases failure investigation.
2. When test cases fails, we need to give the evidence to the person who is investigating the report of the test execution.
3. There can be many screen shot capturing strategies. like:
     * Take one screen shot when test case end (pass or fail)
     * Take screen shot after each line of the scenario.
     * Take screen shot as soon as a step fails.
4. Usually taking screen shot when scenario/step fails is more resonable and commonly used strategy.
5. To implement it we will need to know at run time if the test is pass or fail.
6. If the test is pass we will choose not to take a screen shot.
7. If the test fails we will take the screenshot and attach it with the native report.
8. Then can be achieved in cucumber using 'Scenario' Object injected in @After method.
9. So we have added another After method (you can have many after methods)
10.However we need to make sure, that this after method gets executed before above After method, otherwise browser will be closed by above after method and screen shot will not be captured.
11.To run this @After method first, we need to add the argument 'order' to this method's annotation.
12. Giving this method order as 2, means it will always execute first, and then giving order as 1 to the above after method.
    * Order number is the order in which this hook should run. Higher numbers are run first. The default order is 10000.
13. Now since we need to capture the screen shot only after a test is failed, we will put a if condition as check the failure using method 'isFailed'.
14. If test is failed it will take the screen shot and attach the screen shot with the report. For this s.attach method is used. (in old version, method name was embed)
15. You can check the screen shot attached in the target folder html/htmlreport.html as well as in the online cucumber report link of which get displayed at the end of the execution.
    https://reports.cucumber.io/reports/0fcaf4a1-fb4f-41dc-b1a5-4a266291cc96  

<b> Code Implementation: </b>

<details>
  <summary> Click to see code.</summary>
  
  ```
    // Giving this method order as 2, so that quit happens after screen shot capture.
    @After(order=1)
    public void cleanUp(){
        WebDriverFactory.quitDriver();
        scn.log("Browser Closed");
    }

    @After(order=2) // this will execute first, higher the number, sooner it executes
    public void takeScreenShot(Scenario s) {
      if (s.isFailed()) {
          TakesScreenshot scrnShot = (TakesScreenshot)driver;
          byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
          scn.attach(data, "image/png","Failed Step Name: " + s.getName());
      }else{
          scn.log("Test case is passed, no screen shot captured");
      }
    }
  ```

</details>

### 13. Scenario Outline and examples Implementation

1. Scenario Outline is used to iterate same steps but with different data each time.
2. This is an example of Data driven test case. Data Driven approach is similar to Data Provider in TestNG. (If you do not know test ng dnt worry.)
3. But the idea is simple, "Same Steps" but with different Data.
4. For example, we need to test the 'search' functionality but with different types of products.
5. In the below case, we have three product to search, but steps to do that search are same.
6 .In such cases, we will have to use scenario outline - Examples structure.
7. Examples are written in a tabular format.
8. Below example has only one column, but you can add multiple column, multiple row data.
9. For first iteration, "<product_name>" variable will be replaced with first value in the examples table.
10. Once first iteration is completed, the scenario will again start executing given statement and this time 2nd column value from examples will be picked.
11. It will continue to do this, until all the rows are executed.
12. Check this link for more details: https://cucumber.io/docs/gherkin/reference/

<b> Code Implementation! </b>

<details>
  <summary> Click to see code! </summary>
  
  ```
  Scenario Outline: User is able to search multiple products
    Given User navigated to the home application url
    When User Search for product "<product_name>"
    Then Search Result page is displayed
    Examples:
      |product_name|
      | laptop     |
      | earphone   |
      | computer   |

  Scenario Outline: User is able to search multiple products
    Given User navigated to the home application url
    When User Search for product "<product_name>"
    Then Search Result page is displayed and the price is "<price>"
    Examples:
      |product_name| price      |
      | laptop     |  10        |
      | earphone   |  20        |
      | computer   |  30        |

  ```
  
</details>

