package selenium_project.stepDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium_project.TestComponents.BaseTest;
import selenium_project.pageobjects.CartPage;
import selenium_project.pageobjects.CheckoutPage;
import selenium_project.pageobjects.ConfirmationPage;
import selenium_project.pageobjects.LandingPage;
import selenium_project.pageobjects.ProductCatalogue;

public class stepDefinitionImpl extends BaseTest{

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;

    @Given("I am on the Ecommerce website")
    public void i_am_on_the_Ecommerce_website() throws FileNotFoundException, IOException 
    {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) throws IOException 
    {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add the product (.+) to the cart$")
    public void i_add_the_product_to_the_cart(String productName) throws InterruptedException
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) throws IOException
    {
        cartPage = productCatalogue.goToCart();
        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.clickOnCheckout();
        checkoutPage.selectCountry("ind");
        confirmationPage = checkoutPage.placeOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string) throws InterruptedException
    {    
        String confirm = confirmationPage.confirmMessage();
        Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String string) throws IOException
    {    
        Assert.assertEquals(string, landingPage.getErrorMessage());
    }
}

//tidy gherkin to create all the methods for cucumber step definitions 