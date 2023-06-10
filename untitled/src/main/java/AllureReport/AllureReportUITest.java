package AllureReport;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class AllureReportUITest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    @Step("UI Test")
    public void openLoginPage() throws InterruptedException {
        // Step 1
        driver.get("http://127.0.0.1:8000");

        WebElement element = driver.findElement(By.className("log"));
        element.click();
        WebElement element1 = driver.findElement(By.xpath("//a[contains(text(), 'Sign up')]"));
        element1.click();
        Thread.sleep(1000);

        // Step 2
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
        Thread.sleep(1000);

        // Step 3
        WebElement spanElement = driver.findElement(By.id("un"));
        String actualText = spanElement.getText();
        String expectedText = generatedUsername;
        Assert.assertEquals(actualText, expectedText, "unexpected message");
        Thread.sleep(1000);

        // Step 4
        WebElement hpclick = driver.findElement(By.xpath("//a[contains(text(), 'HP')]"));
        hpclick.click();
        WebElement hpLaptop = driver.findElement(By.xpath("//a[contains(text(), 'Ноутбук HP Pavilion 14-dv0047ua')]"));
        hpLaptop.click();
        Thread.sleep(1000);

        // Step 5
        WebElement comment = driver.findElement(By.cssSelector("input[placeholder='Write your message here..']"));
        comment.sendKeys("Good Product");
        comment.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // Attachments
        takeScreenshot();
        attachHtmlCode("<html><body>HTML Code</body></html>");
        attachRequest("Request");
        attachResponse("Response");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }

    @Attachment(value="Screenshot", type="image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment("HTML Code")
    public static ByteArrayInputStream attachHtmlCode(String htmlCode) {
        return new ByteArrayInputStream(htmlCode.getBytes());
    }

    @Attachment("Request")
    public static String attachRequest(String request) {
        return request;
    }

    @Attachment("Response")
    public static String attachResponse(String response) {
        return response;
    }
}