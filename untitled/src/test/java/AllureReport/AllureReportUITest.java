package AllureReport;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
@Listeners(AllureTestNg.class)
public class AllureReportUITest {
    private static WebDriver driver;
    private static String generatedUsername;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(options);
    }

    @Test
    public void uiTest() throws InterruptedException {
        openLoginPage();
        performSignUp();
        verifyUsername();
        selectProduct();
        leaveComment();
    }

    @Step("Step 1: Open login page")
    private void openLoginPage() {
        driver.get("http://127.0.0.1:8000");
        WebElement element = driver.findElement(By.className("log"));
        element.click();
        WebElement element1 = driver.findElement(By.xpath("//a[contains(text(), 'Sign up')]"));
        element1.click();
        takeScreenshot();
    }

    @Step("Step 2: Perform sign up")
    private void performSignUp() throws InterruptedException {
        WebElement logElement = driver.findElement(By.className("log"));
        logElement.click();
        WebElement signUpElement = driver.findElement(By.xpath("//a[contains(text(), 'Sign up')]"));
        signUpElement.click();
        Thread.sleep(1000);

        generatedUsername = random();

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

        takeScreenshot();
    }

    @Step("Step 3: Verify username")
    private void verifyUsername() {
        WebElement usernameSpan = driver.findElement(By.id("un"));
        String actualUsernameText = usernameSpan.getText();
        Assert.assertEquals(actualUsernameText, generatedUsername, "Unexpected username");
        takeScreenshot();
    }

    @Step("Step 4: Select product")
    private void selectProduct() throws InterruptedException {
        WebElement hpClick = driver.findElement(By.xpath("//a[contains(text(), 'HP')]"));
        hpClick.click();
        WebElement hpLaptop = driver.findElement(By.xpath("//a[contains(text(), 'Ноутбук HP Pavilion 14-dv0047ua')]"));
        hpLaptop.click();
        Thread.sleep(1000);

        takeScreenshot();
    }

    @Step("Step 5: Leave comment")
    private void leaveComment() throws InterruptedException {
        WebElement comment = driver.findElement(By.cssSelector("input[placeholder='Write your message here..']"));
        comment.sendKeys("Good Product");
        comment.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

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

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment("HTML Code")
    public String attachHtmlCode(String htmlCode) {
        return htmlCode;
    }

    @Attachment("Request")
    public String attachRequest(String request) {
        return request;
    }

    @Attachment("Response")
    public String attachResponse(String response) {
        return response;
    }
}