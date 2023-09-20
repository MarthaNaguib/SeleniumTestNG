package exam;

import models.Dataset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import providers.XmlDatasetProvider;

import static constants.CommonConstants.*;

public class LoginTestDynamic extends BaseTest
{
    @Test(dataProvider = DATA_PROVIDER_NAME, dataProviderClass = XmlDatasetProvider.class)
    public void LoginTest(Dataset aInDataset) throws InterruptedException
    {
        System.out.println("Executing Sample Test...");

        driver.get(MAIN_URL);
        WebElement username = driver.findElement(By.id("username"));
        //click into the field first to make it active.
        username.click();
        username.sendKeys(aInDataset.getValue("username"));

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(aInDataset.getValue("password"));

        WebElement button = driver.findElement(By.cssSelector("button.radius"));
        button.click();

        Thread.sleep(1000);
        if (aInDataset.getName().equals("failure"))
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
}
