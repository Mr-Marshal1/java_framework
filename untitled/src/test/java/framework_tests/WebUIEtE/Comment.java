package framework_tests.WebUIEtE;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

public class Comment {
    private WebDriver driver;
    private String generatedUsername;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test(dependsOnMethods = "WebUIEtE.Laptop.testLogin")
    public void testLogin() throws InterruptedException {
        WebElement comment = driver.findElement(By.cssSelector("input[placeholder='Write your message here..']"));
        comment.sendKeys("Good Product");
        comment.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // Check if comment is sent
        WebElement commentWrapper = driver.findElement(By.className("comment-wrapper"));
        List<WebElement> divElements = commentWrapper.findElements(By.tagName("div"));
        WebElement lastDivElement = divElements.get(0);
        WebElement pElement = lastDivElement.findElement(By.tagName("p"));
        String pText = pElement.getText();
        String expectedText1 = "Good Product";
        boolean isTextContainsWord = pText.contains(expectedText1);

        Assert.assertTrue(isTextContainsWord, "Text text contains word: " + pText);
    }


    public String getGeneratedUsername() {
        return generatedUsername;
    }
    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
    @AfterClass
    public void Close() {
        driver.quit();
    }
}
