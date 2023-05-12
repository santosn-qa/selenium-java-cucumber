package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {

    private static final String PAGE_URL = "https://stripe-payments-demo.appspot.com/";

    private By generateLink = By.xpath("//span[@id='generate']");
    private By cardNumberInput = By.xpath("//input[@name='cardnumber']");
    private By cardExpirationInput = By.xpath("//input[@name='exp-date']");
    private By cardCvcInput = By.xpath("//input[@name='cvc']");
    private By payButton = By.xpath("//button[@class='payment-button']");
    private By formErrorMessage = By.xpath("//div[@id='card-errors']");
    private By checkOutErrorMessage = By.xpath("//p[@class='error-message']");
    private By successPayment = By.xpath("//main[@class='checkout success']");
    private final By errorPayment = By.xpath("//main[@class='checkout error']");

    public PaymentPage() {
        // Invoke the constructor of the superclass (BasePage)
        super();
    }

    public void navigateToPage() {
        navigateTo(PAGE_URL);
    }

    public String getPageTitle() {
        return getTitle();
    }

    public void clickGenerateLink() {
        clickElement(generateLink);
    }

    public void enterPaymentInfo(String cardNumber, String cardExpiration, String cardCvc) {
        WebElement iFrame = findElementBy(By.xpath("//div[@id='card-element']//iframe"));
        driver.switchTo().frame(iFrame);

        setElementText(cardNumberInput, cardNumber);
        setElementText(cardExpirationInput, cardExpiration);
        setElementText(cardCvcInput, cardCvc);

        driver.switchTo().defaultContent();
    }

    public void clickPayButton() {
        clickElement(payButton);
    }

    public boolean isFormErrorMessage(String message) {
        return getElementText(formErrorMessage).contains(message);
    }

    public boolean isCheckOutErrorMessage(String message) {
        return getElementText(checkOutErrorMessage).contains(message);
    }

    public boolean isPaymentSuccessful() {
        return isDisplayed(successPayment);
    }

    public boolean isPaymentFailed() {
        return isDisplayed(errorPayment);
    }

}