package exam;

import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1Test {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up WebDriver...");
        // Use WebDriverManager to setup ChromeDriver
//        System.setProperty("webdriver.chrome.driver", "/home/martha/chromedriver_linux64/chromedriver");
        WebDriverManager.chromedriver().setup();


        // You can also configure Chrome options if needed
        ChromeOptions options = new ChromeOptions();
        // Add any desired options here

        // Initialize the WebDriver instance with options
        driver = new ChromeDriver(options);

//        WebDriver driver = new ChromeDriver();
    }

    @Test
    public void sampleTest() {
        System.out.println("Executing Sample Test...");
        // Your test logic goes here
        driver.get("https://www.google.com");
        // Add your test assertions and actions here
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Tearing down WebDriver...");
        // Close the WebDriver session
        if (driver != null) {
            driver.quit();
        }
    }
}
