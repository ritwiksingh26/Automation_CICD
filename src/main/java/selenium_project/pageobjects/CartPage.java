package selenium_project.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_project.AbstractComponent.AbstractComponents;

public class CartPage extends AbstractComponents{
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;

    @FindBy(css= ".cartSection h3")
    List<WebElement> productTitles;

    public Boolean VerifyProductDisplay(String product_name)
    {
        Boolean match = productTitles.stream().anyMatch(prodcut -> prodcut.getText().equals(product_name));
        return match;
    }

    public CheckoutPage clickOnCheckout()
    {
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }
}
