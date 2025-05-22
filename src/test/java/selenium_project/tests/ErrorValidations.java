package selenium_project.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import selenium_project.TestComponents.BaseTest;
import selenium_project.TestComponents.RetryAnalyzer;
import selenium_project.pageobjects.CartPage;
import selenium_project.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginErrorValidation() throws IOException{

        String product_name = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("ritwik@example.com", "Ritwik123");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productVerificationValidations() throws InterruptedException
    {
        String product_name = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("ritwiksingh@example.com", "Ritwik@123");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(product_name);
        CartPage cartPage = productCatalogue.goToCart();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 4");
        Assert.assertFalse(match);
    }
}
