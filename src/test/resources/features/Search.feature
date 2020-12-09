@ui @search
Feature: User is able to use search feature


  Scenario: User is able to search for various products and add each type of products with different prices
    Given User navigated to the home application url
    When User add the products with defined price range and quantity listed below
      | ITEM     | PRICE_LESS_THAN | QUANTITY |
      | laptop   | 40000           | 1        |
      | earphone | 1000            | 2        |
      | mouse    | 2000            | 1        |
    Then User cart is updated with the products and quantity
