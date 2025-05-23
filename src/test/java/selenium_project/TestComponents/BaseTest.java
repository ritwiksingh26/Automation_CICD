package selenium_project.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium_project.pageobjects.LandingPage;

public class BaseTest {

    public WebDriver driver; 
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws FileNotFoundException, IOException
    {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Ritwik\\Desktop\\Java Selenium\\demo_project\\src\\main\\java\\selenium_project\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        // String browserName = prop.getProperty("browser");
        // mvn test -Dbrowser=Firefox (command to select browser from terminal / otherwise get it from GlobalData.properties)

        if(browserName.contains("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900)); //fullscreen
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            // WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else
        {
            System.out.println("No browser found, Unable to start the browser");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String,String>> getJsonDataToMap(String Filepath) throws IOException{
        //JSON to String
        String jsonContent = FileUtils.readFileToString(new File(Filepath), "UTF-8");
        //string to HashMap - Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws FileNotFoundException, IOException
    {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException
    {
        Thread.sleep(1000);
        try
        {
            if (driver != null)
            {
                driver.close();
            }
        }
        catch (Exception e)
        {
            System.err.println("Warning: Error while closing the browser - " + e.getMessage());
        }
    }
}

