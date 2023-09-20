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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static constants.CommonConstants.*;

public class CredentialWithResultXML extends BaseTest
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

    @DataProvider(name = "LoginData")
    private Object[][] getData()
    {
        try
        {
            // Load the XML file
//            File xmlFile = new File(
//                    "/home/martha/Downloads/Self Study/exam/src/test/java/loginTestData.xml");
//            File xmlFile = new File(
//                    "src/test/java/loginTestData.xml");
            File xmlFile = new File(XML_PATH);
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(xmlFile);

            // Find the <testData> section.
            NodeList testDataNodes = document.getElementsByTagName("testData");

            Object[][] data = new Object[testDataNodes.getLength()][3];

            Element testDataElement = null;
            // Extract data from XML and populate the array

            for (int i = 0; i < testDataNodes.getLength(); i++)
            {
                testDataElement = (Element) testDataNodes.item(i);
                String id = testDataElement.getAttribute("id");
                String username = testDataElement.getElementsByTagName("username").item(0).getTextContent();
                String password = testDataElement.getElementsByTagName("password").item(0).getTextContent();
                data[i][0] = username;
                data[i][1] = password;
                data[i][2] = id;

            }



            return data;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Test(dataProvider = "LoginData")
    public void LoginTest(String usernameString, String passString, String result)
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
        if (result.equals("failure"))
        {
            WebElement errorMessage = driver.findElement(By.id("flash"));

            // Assert that the error message contains the expected text
            String expectedErrorMessage = "Your username is invalid!";
            String actualErrorMessage = errorMessage.getText();
            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                    "Error message not found or does not match.");
        }
        else
        {
            // Assert that the current URL is the expected URL
            String currentURL = driver.getCurrentUrl();
            String expectedURL = SUCCESS_URL;
            Assert.assertEquals(currentURL, expectedURL,
                    "URLs do not match. Login failed.");
        }

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
