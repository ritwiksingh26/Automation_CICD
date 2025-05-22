package selenium_project.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium_project.pageobjects.CartPage;
import selenium_project.pageobjects.OrderPage;

public class AbstractComponents {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponents(WebDriver driver) 
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = ".btn.btn-custom[routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = ".btn.btn-custom[routerlink='/dashboard/myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(By FindBy) 
    {
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }

    public void waitForWebElementToAppear(WebElement WebFindBy)
    {
        wait.until(ExpectedConditions.visibilityOf(WebFindBy));
    }
    
    public void waitForElementToDisappear(By ele)
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));
    }

    public CartPage goToCart()
    {
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrder()
    {
        orderHeader.click();
        return new OrderPage(driver);
    }
}
