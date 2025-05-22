package selenium_project.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_project.AbstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents{
    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        // Initialize the WebDriver
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginButton;

    @FindBy(css="div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
