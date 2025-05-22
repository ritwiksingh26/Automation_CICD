package selenium_project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_project.AbstractComponent.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(css = "button[class*='ta-item']:nth-child(3)")
    WebElement countryDropdown;

    @FindBy(css = ".btnn.action__submit")
    WebElement finalSubmit;

    @FindBy(css = ".hero-primary")
    WebElement confirmationElement;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String country)
    {
        countryInput.sendKeys(country);
        waitForElementToAppear(results);
        countryDropdown.click();
    }

    public ConfirmationPage placeOrder()
    {
        finalSubmit.click();
        return new ConfirmationPage(driver);
    }

}
