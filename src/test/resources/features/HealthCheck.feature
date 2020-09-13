@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    Given User navigated to the home application url
    When User Search for product "Laptop"
    Then Search Result page is displayed

    @t
  Scenario: User is click on the Product and check the Product Details
    Given User navigated to the home application url
    And User Search for product "earphone"
    When User click on any product
    Then Product Description is displayed in new tab

# Before Refactoring i.e. before using @Before annotation to invoke browser
# Notice that now: 'Given User opened browser' this step is not needed
#  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
#    Given User opened browser
#    And User navigated to the home application url
#    When User Search for product "Laptop"
#    Then Search Result page is displayed
#
#  @t
#  Scenario: User is click on the Product and check the Product Details
#    Given User opened browser
#    And User navigated to the home application url
#    And User Search for product "earphone"
#    When User click on any product
#    Then Product Description is displayed in new tab
