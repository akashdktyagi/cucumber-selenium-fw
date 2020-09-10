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