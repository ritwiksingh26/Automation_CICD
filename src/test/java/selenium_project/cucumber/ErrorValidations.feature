@tag
Feature: Error Validations
    I want to use this template for my Feature file
    
    Background:
    Given I am on the Ecommerce website
    
    @ErrorValidation
    Scenario Outline: Positive test of validating the order
        Given I am on the Ecommerce website
        When Logged in with username <name> and password <password>
        Then "Incorrect email or password." message is displayed
    
         Examples:
             | name                     | password         | productName |
             | ritwiksingh@example.com  | Ritwik@1         | ZARA COAT 3 |