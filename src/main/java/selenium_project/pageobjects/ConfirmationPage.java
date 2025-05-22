package selenium_project.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_project.AbstractComponent.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationElement;

    public String confirmMessage()
    {
        return confirmationElement.getText();
    }


}
