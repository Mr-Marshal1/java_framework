package UITest;

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

public class FireFoxTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = "firefox";
        driver = DriverManager.getDriver(browser);
    }

    @Test
    public void testElements() throws InterruptedException {
        driver.get("http://127.0.0.1:8000");


        // Click on Login
        WebElement element = driver.findElement(By.className("log"));
        element.click();
        Thread.sleep(500);
        WebElement element1 = driver.findElement(By.xpath("//a[contains(text(), 'Sign up')]"));
        element1.click();

        // Enter data and confirm
        String generatedUsername = random();

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys(generatedUsername);

        String generatedPassword = random();
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(generatedPassword);

        WebElement passwordConfirm = driver.findElement(By.name("password2"));
        passwordConfirm.sendKeys(generatedPassword);

        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        registerButton.click();
        Thread.sleep(500);

        // Checking login
        WebElement spanElement = driver.findElement(By.id("un"));
        String actualText = spanElement.getText();
        String expectedText = generatedUsername;
        Assert.assertEquals(actualText, expectedText, "unexpected message");
        Thread.sleep(500);

        // Laptop
        WebElement hpclick = driver.findElement(By.xpath("//a[contains(text(), 'HP')]"));
        hpclick.click();
        WebElement hpLaptop = driver.findElement(By.xpath("//a[contains(text(), 'Ноутбук HP Pavilion 14-dv0047ua')]"));
        hpLaptop.click();

        // Comment
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

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
}
