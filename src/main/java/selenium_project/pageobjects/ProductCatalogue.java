package selenium_project.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_project.AbstractComponent.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
    WebDriver driver;
    public ProductCatalogue(WebDriver driver)
    {
        // Initialize the WebDriver
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    //Only works with driver.findelement and not webelement.findelement
    @FindBy(css =".mb-3")
    List<WebElement> products;

    By products_by = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".mb-3 button.btn.w-10");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ng-tns-c31-21");

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(products_by);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod = getProductList().stream().filter(product -> 
        product.findElement(By.cssSelector("b")).getText().equals(productName))
        .findFirst()
        .orElse(null);
        return prod;

    }

    public void addProductToCart(String productName) throws InterruptedException
    {
        WebElement prodCart = getProductByName(productName);
        prodCart.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        Thread.sleep(1500);
        // waitForElementToDisappear(spinner);
    }

}
