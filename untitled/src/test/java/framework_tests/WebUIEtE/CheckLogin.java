package framework_tests.WebUIEtE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class CheckLogin {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test(dependsOnMethods = "WebUIEtE.DataEntry.testLogin")
    public void testLogin() throws InterruptedException {
        DataEntry DataEntry = new DataEntry();
        WebElement spanElement = driver.findElement(By.id("un"));
        String actualText = spanElement.getText();
        String expectedText = DataEntry.getGeneratedUsername();
        Assert.assertEquals(actualText, expectedText, "unexpected message");
        Thread.sleep(500);
    }
    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
    @AfterClass
    public void Close() {
        driver.quit();
    }
}
