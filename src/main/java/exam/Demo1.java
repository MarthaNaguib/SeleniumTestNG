package exam;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Demo1
{
    private static WebDriver driver;

    public static void main( String[] args )
    {
        System.out.println( "Open Chrome" );
//        System.setProperty("webdriver.chrome.driver", "/home/martha/chromedriver_linux64/chromedriver");
        WebDriverManager.chromedriver().setup();


        // You can also configure Chrome options if needed
        ChromeOptions options = new ChromeOptions();
        // Add any desired options here

        // Initialize the WebDriver instance with options
        driver = new ChromeDriver(options);
        driver.get("https://amazon.com");

        //find by locator

        driver.findElement(By.className(""));

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
