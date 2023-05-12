package org.example.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.pages.PaymentPage;

import java.util.List;

import static org.testng.Assert.*;

public class PaymentSteps {

    private final PaymentPage paymentPage;

    public PaymentSteps() {
        this.paymentPage = new PaymentPage();
    }

    @Given("^the user is at the Stripe Demo Site$")
    public void iAmOnThePaymentPage() {
        paymentPage.navigateToPage();
    }

    @Given("^the user auto generates shipping information$")
    public void iClickGenerate() {
        paymentPage.clickGenerateLink();
    }


    @When("^the user enters the payment information with the following data$")
    public void iEnterThePaymentInformation(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        for (List<String> columns : rows) {
            paymentPage.enterPaymentInfo(columns.get(0), columns.get(1), columns.get(2));
        }
    }

    @And("^the user clicks the Pay button$")
    public void iClickThePayButton() {
        paymentPage.clickPayButton();
    }

    @Then("^form error should show containing the following message \"([^\"]*)\"$")
    public void theFormErrorMessageShouldBe(String message) {
        assertTrue(paymentPage.isFormErrorMessage(message));
    }

    @Then("^checkout error should show containing the following message \"([^\"]*)\"$")
    public void theCheckoutErrorMessageShouldBe(String message) {
        assertTrue(paymentPage.isCheckOutErrorMessage(message));
    }


    @Then("^the payment should be successful$")
    public void thePaymentShouldBeSuccessful() {
        assertTrue(paymentPage.isPaymentSuccessful());
    }

    @Then("^the payment should be declined$")
    public void thePaymentShouldBeDeclined() {
        assertTrue(paymentPage.isPaymentFailed());
    }

}
