package selenium_project.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium_project.TestComponents.BaseTest;
import selenium_project.pageobjects.CartPage;
import selenium_project.pageobjects.CheckoutPage;
import selenium_project.pageobjects.ConfirmationPage;
import selenium_project.pageobjects.OrderPage;

import selenium_project.pageobjects.ProductCatalogue;

// new comments added

public class StandAloneTest extends BaseTest{
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException{

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCart();
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        // validations should not go in Page object files, only in test files
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.clickOnCheckout();
        checkoutPage.selectCountry("ind");
        
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirm = confirmationPage.confirmMessage();
        Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"SubmitOrder"}, dataProvider = "getData", groups = {"Purchase"})
    public void OrderHistoryTest(HashMap<String, String> input)
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrder();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("product")));
    }

    @DataProvider
    public Object[][] getData() throws IOException
    {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\selenium_project\\data\\PurchaseOrder.json");

        return new Object[][]{ {data.get(0)}, {data.get(1)} };
    }

    

    // HashMap<String,String> map = new HashMap<String,String>();
        // map.put("email", "ritwiksingh@example.com");
        // map.put("password", "Ritwik@123");
        // map.put("product", "ZARA COAT 3");

        // HashMap<String,String> map1 = new HashMap<String,String>();
        // map1.put("email", "shaan@example.com");
        // map1.put("password", "Testpractice@123");
        // map1.put("product", "ADIDAS ORIGINAL");
    
}

