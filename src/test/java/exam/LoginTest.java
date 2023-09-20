package exam;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static constants.CommonConstants.MAIN_URL;
import static constants.CommonConstants.SUCCESS_URL;

public class LoginTest extends BaseTest
{
//    private WebDriver driver;
//
//    @BeforeMethod
//    public void setUp()
//    {
//        System.out.println("Setting up WebDriver...");
//        // Use WebDriverManager to set up ChromeDriver
//        WebDriverManager.chromedriver().setup();
//
//        // I can also configure Chrome options if needed
//        ChromeOptions options = new ChromeOptions();
//
//        // Initialize the WebDriver instance with options
//        driver = new ChromeDriver(options);
//
//    }

    @Test
    public void TestSuccessLogin()
    {
        System.out.println("Executing Sample Test...");

        driver.get(MAIN_URL);
        WebElement username = driver.findElement(By.id("username"));

        //click into the field first to make it active.
        username.click();
        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys("SuperSecretPassword!");

        WebElement button = driver.findElement(By.cssSelector("button.radius"));
        button.click();

        // Assert that the current URL is the expected URL
        String currentURL = driver.getCurrentUrl();
        String expectedURL = SUCCESS_URL;
        Assert.assertEquals(currentURL, expectedURL,
                "URLs do not match. Login failed.");

    }

//    @AfterMethod
//    public void tearDown()
//    {
//        System.out.println("Tearing down WebDriver...");
//        // Close the WebDriver session
//        if (driver != null)
//        {
//            driver.quit();
//        }
//    }
}
