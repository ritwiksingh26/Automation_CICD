package selenium_project.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_project.AbstractComponent.AbstractComponents;

public class OrderPage extends AbstractComponents{
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css= "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public Boolean VerifyOrderDisplay(String product_name)
    {
        Boolean match = productNames.stream().anyMatch(prodcut -> prodcut.getText().equalsIgnoreCase(product_name));
        return match;
    }
}
