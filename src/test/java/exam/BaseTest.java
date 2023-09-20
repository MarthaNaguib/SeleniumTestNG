package exam;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest
{
    WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        System.out.println("Setting up WebDriver...");
        // Use WebDriverManager to set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // I can also configure Chrome options if needed
        ChromeOptions options = new ChromeOptions();

        // Initialize the WebDriver instance with options
        driver = new ChromeDriver(options);

    }

    @AfterMethod
    public void tearDown()
    {
        System.out.println("Tearing down WebDriver...");
        // Close the WebDriver session
        if (driver != null)
        {
            driver.quit();
        }
    }
}
