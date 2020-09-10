# cucumber-selenium-fw

Steps to Do
---
###Project Set Up
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
###Add Feature File
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

###Add Runner File
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
###Generate Step Defs and Add in stepdefs package
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
###Add Selenium Steps in Step Defs Java File
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