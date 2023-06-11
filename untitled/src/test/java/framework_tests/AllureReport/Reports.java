package framework_tests.AllureReport;

import UITest.DriverManager;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
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
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Reports {
    private WebDriver driver;
    private String generatedUsername;

    @BeforeClass
    public void setUp() {
        String browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void UITestStart() throws InterruptedException {
        driver.get("http://127.0.0.1:8000");

        clickLoginButton();
        takeScreenshot("LoginPage");

        clickSignUpLink();
        takeScreenshot("SignUpPage");

        enterUserData();
        takeScreenshot("UserDataEntered");

        registerUser();
        takeScreenshot("RegistrationComplete");

        checkLogin();
        takeScreenshot("LoggedIn");

        selectLaptop();
        takeScreenshot("LaptopSelected");

        leaveComment();
        takeScreenshot("CommentAdded");

        checkCommentSent();
        takeScreenshot("CommentSent");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Step("Click on Login button")
    private void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.className("log"));
        loginButton.click();
    }

    @Step("Click on Sign up link")
    private void clickSignUpLink() {
        WebElement signUpLink = driver.findElement(By.xpath("//a[contains(text(), 'Sign up')]"));
        signUpLink.click();
    }

    @Step("Enter user data")
    private void enterUserData() {
        generatedUsername = random();
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys(generatedUsername);

        String generatedPassword = random();
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(generatedPassword);

        WebElement passwordConfirm = driver.findElement(By.name("password2"));
        passwordConfirm.sendKeys(generatedPassword);
    }

    @Step("Register user")
    private void registerUser() {
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        registerButton.click();
    }

    @Step("Check login")
    private void checkLogin() {
        WebElement spanElement = driver.findElement(By.id("un"));
        String actualText = spanElement.getText();
        String expectedText = generatedUsername;
        Assert.assertEquals(actualText, expectedText, "unexpected message");
    }

    @Step("Select laptop")
    private void selectLaptop() {
        WebElement hpClick = driver.findElement(By.xpath("//a[contains(text(), 'HP')]"));
        hpClick.click();

        WebElement hpLaptop = driver.findElement(By.xpath("//a[contains(text(), 'Ноутбук HP Pavilion 14-dv0047ua')]"));
        hpLaptop.click();
    }

    @Step("Leave comment")
    private void leaveComment() {
        WebElement comment = driver.findElement(By.cssSelector("input[placeholder='Write your message here..']"));
        comment.sendKeys("Good Product");
        comment.sendKeys(Keys.ENTER);
    }

    @Step("Check if comment is sent")
    private void checkCommentSent() {
        WebElement commentWrapper = driver.findElement(By.className("comment-wrapper"));
        List<WebElement> divElements = commentWrapper.findElements(By.tagName("div"));
        WebElement lastDivElement = divElements.get(0);
        WebElement pElement = lastDivElement.findElement(By.tagName("p"));
        String pText = pElement.getText();
        String expectedText = "Good Product";
        boolean isTextContainsWord = pText.contains(expectedText);
        Assert.assertTrue(isTextContainsWord, "Text text contains word: " + pText);
    }

    private void takeScreenshot(String screenshotName) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("screenshots/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachScreenshotToReport(screenshotName);
    }

    @Attachment("{screenshotName}")
    private byte[] attachScreenshotToReport(String screenshotName) {
        try {
            File screenshot = new File("screenshots/" + screenshotName + ".png");
            return Files.toByteArray(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
}