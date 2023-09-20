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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static constants.CommonConstants.MAIN_URL;
import static constants.CommonConstants.SUCCESS_URL;

public class LoginDataProviders2DArray extends BaseTest
{
//    private WebDriver driver;
//
//    @BeforeMethod
//    public void setUp()
//    {
//        System.out.println("Setting up WebDriver...");
//        // Use WebDriverManager to setup ChromeDriver
//        WebDriverManager.chromedriver().setup();
//
//        // I can also configure Chrome options if needed
//        ChromeOptions options = new ChromeOptions();
//
//        // Initialize the WebDriver instance with options
//        driver = new ChromeDriver(options);
//
//    }

    @DataProvider(name = "ValidData")
    public Object[][] testValidData()
    {
        Object[][] lObjects = new Object[1][2];
        lObjects[0][0] = "tomsmith";
        lObjects[0][1] = "SuperSecretPassword!";
        return lObjects;
    }

    @DataProvider(name = "InvalidData")
    public Object[][] testInvalidData()
    {
        Object[][] lObjects = new Object[1][2];
        lObjects[0][0] = "martha";
        lObjects[0][1] = "SuperSecretPassword!";
        return lObjects;
    }

    @Test(dataProvider = "InvalidData")
    public void TestUnsuccessfulLogin(String usernameString, String passString)
            throws InterruptedException
    {
        System.out.println("Executing Sample Test...");

        driver.get(MAIN_URL);
        WebElement username = driver.findElement(By.id("username"));
        //click into the field first to make it active.
        username.click();
        username.sendKeys(usernameString);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passString);

        WebElement button = driver.findElement(By.cssSelector("button.radius"));
        button.click();

        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.id("flash"));

        // Assert that the error message contains the expected text
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Error message not found or does not match.");


    }

    @Test(dataProvider = "ValidData")
    public void TestSuccessLogin(String usernameString, String passString)
            throws InterruptedException
    {
        System.out.println("Executing Sample Test...");

        driver.get(MAIN_URL);
        WebElement username = driver.findElement(By.id("username"));
        //click into the field first to make it active.
        username.click();
        username.sendKeys(usernameString);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passString);

        WebElement button = driver.findElement(By.cssSelector("button.radius"));
        button.click();

        Thread.sleep(1000);
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
