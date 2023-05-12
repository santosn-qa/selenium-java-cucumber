# selenium-java-cucumber
This is a demonstration of automated testing for the [Stripe Demo Site](https://stripe-payments-demo.appspot.com/)

Find the public repository [here](https://github.com/santosn-qa/selenium-java-cucumber) | Authored by: Nourilee Santos

## frameworks
These tests are developed in Java with:

-   Selenium Java >= v4.9.x - [Selenium Java from Maven Repository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) - 
-   Cucumber >= v7.11.1
    - [Cucumber TestNG](https://mvnrepository.com/artifact/io.cucumber/cucumber-testng) - used for running cucumber with testng
    - [Cucumber Java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java) - used for running cucumber with testng
-   TestNG >= v7.4.0 - [TestNG](https://mvnrepository.com/artifact/org.testng/testng) - used for assertions and reporting
    -  [ExtentReports](https://mvnrepository.com/artifact/com.aventstack/extentreports) - used for reporting
-   WebDriverManager >= v5.3.2
    - [WebDriverManager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager) - used for managing browser drivers

## prerequisites
- Java 18 or higher
- Maven 3.8.3 or higher 
- Chrome 111.0.x or higher
- IDE of your choice (IntelliJ, Eclipse, etc.)

## setup
- Clone the repository
- Open the project in your IDE
- Install dependencies
- Run the test scenarios
- View the test report

## running the test scenarios
The test scenarios can be run in two ways:

### 1. Using the IDE
- Open the project in your IDE
- Navigate to the `src/test/java/runner` directory
- Right click on the `TestRunner.java` file
- Select `Run 'TestRunner'`

### 2. Using the command line
- Open the project in your IDE
- Navigate to the project root directory
- Run the following command:
```shell
mvn clean test
```


## test cases
The cucumber feature file will serve as the test case documentation showing scenarios covered by the test.
In order to understand how to use test cards, I used this [link](https://stripe.com/docs/testing) as a reference.

```feature
Feature: Stripe Payment Flow - Card Payment Validations

    Background:
        Given the user is at the Stripe Demo Site
        And the user auto generates shipping information

    Scenario Outline: Verify process flow for successful payments
        When the user enters the payment information with the following data
            | cardNumber   | cardExpiry   | cvc   |
            | <cardNumber> | <cardExpiry> | <cvc> |
        And the user clicks the Pay button
        Then the payment should be successful

        Examples:
            | cardNumber          | cardExpiry | cvc |
            | 4242 4242 4242 4242 | 12 / 26    | 123 |
            | 5555 5555 5555 4444 | 12 / 26    | 123 |
            | 3782 822463 10005   | 12 / 26    | 123 |


    Scenario Outline: Verify process flow for invalid card information
        When the user enters the payment information with the following data
            | cardNumber   | cardExpiry   | cvc   |
            | <cardNumber> | <cardExpiry> | <cvc> |
        And the user clicks the Pay button
        Then form error should show containing the following message "<errorMessage>"

        Examples:
            | cardNumber          | cardExpiry | cvc | errorMessage                                |
            | 4242 4242 4242 4242 | 0          | 123 | Your card's expiration date is incomplete.  |
            | 4242 4242 4242 4242 | 12 / 99    | 123 | Your card's expiration year is invalid.     |
            | 4242 4242 4242 4242 | 12 / 20    | 123 | Your card's expiration year is in the past. |
            | 4242 4242 4242 4242 | 12 / 26    | 99  | Your card's security code is incomplete.    |
            | 4242 4242 4242 4241 | 12 / 26    | 123 | Your card number is invalid.                |
            
    Scenario Outline: Verify process flow for declined payments
        When the user enters the payment information with the following data
            | cardNumber   | cardExpiry   | cvc   |
            | <cardNumber> | <cardExpiry> | <cvc> |
        And the user clicks the Pay button
        Then the payment should be declined
        And checkout error should show containing the following message "<errorMessage>"

        Examples:
            | cardNumber       | cardExpiry | cvc | errorMessage                                    |
            | 4000000000000002 | 12 / 26    | 123 | Your card has been declined.                    |
            | 4000000000009995 | 12 / 26    | 123 | Your card has insufficient funds.               |
            | 4000000000009987 | 12 / 26    | 123 | Your card has been declined.                    |
            | 4000000000009979 | 12 / 26    | 123 | Your card has been declined.                    |
            | 4100000000000019 | 12 / 26    | 123 | Your card has been declined.                    |
            | 4000000000000127 | 12 / 26    | 123 | Your card's security code is incorrect.         | 
            | 4000000000000069 | 12 / 26    | 123 | Your card has expired.                          |
            | 4000000000000119 | 12 / 26    | 123 | An error occurred while processing your card.   |
            | 4000000000000101 | 12 / 26    | 123 | Your card has been declined.                    |
```
