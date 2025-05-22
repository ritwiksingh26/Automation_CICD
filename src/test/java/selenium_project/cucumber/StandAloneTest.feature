@tag
Feature: Purchase the order from Ecommerce Website
 I want to use this template for my Feature file

 Background:
 Given I am on the Ecommerce website

 @Regression
 Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to the cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER" message is displayed on ConfirmationPage

    Examples:
        | name                     | password         | productName |
        | ritwiksingh@example.com  | Ritwik@123     | ZARA COAT 3 |
      
      