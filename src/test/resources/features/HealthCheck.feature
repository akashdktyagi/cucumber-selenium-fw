@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  # Background in Cucumber is used to define a step or series of steps that are common to all the tests in the feature file.
  # It allows you to add some context to the scenarios for a feature where it is defined.
  # A Background is much like a scenario containing a number of steps.
  # But it runs before each and every scenario were for a feature in which it is defined.
  # In Our example, we have a Given statement which being repeated in all the Scenarios. i.e. User navigated to the Home application url
  # So We will move it up and write this statement at the top under Background.
  # When Feature file is executed, Background statement is going to get executed before each Test Case automatically.
  # Background is similar to having a Before hook, but instead of defining before in the code, it is used in Fearure file.
  # Also, notice that we have removed Given statement from all the below test cases.
  Background: Navigation to the URL
    Given User navigated to the home application url

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    When User Search for product "Laptop"
    Then Search Result page is displayed

    @t
  Scenario: User is click on the Product and check the Product Details
    And User Search for product "earphone"
    When User click on any product
    Then Product Description is displayed in new tab

  # Scenario Outline is used to iterate same steps but with different data each time.
  # This is an example of Data driven test case. Data Driven approach is similar to Data Provider in TestNG. (If you do not know test ng dnt worry.)
  # But the idea is simple, "Same Steps" but with different Data.
  # For example, we need to test the 'search' functionality but with different types of products.
  # In the below case, we have three product to search, but steps to do that search are same.
  # In such cases, we will have to use scenario outline - Examples structure.
  # Examples are written in a tabular format.
  # Below example has only one column, but you can add multiple column, multiple row data.
  # For first iteration, "<product_name>" variable will be replaced with first value in the examples table.
  # Once first iteration is completed, the scenario will again start executing given statement and this time 2nd column value from examples will be picked.
  # It will continue to do this, until all the rows are executed.
  # Check this link for more details: https://cucumber.io/docs/gherkin/reference/
  Scenario Outline: User is able to search multiple products
    When User Search for product "<product_name>"
    Then Search Result page is displayed
    Examples:
      |product_name|
      | laptop     |
      | earphone   |
      | computer   |


