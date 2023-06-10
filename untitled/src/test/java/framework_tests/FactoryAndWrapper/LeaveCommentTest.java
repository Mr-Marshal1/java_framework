package framework_tests.FactoryAndWrapper;
import FactoryAndWrapper.*;

import FactoryAndWrapper.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

public class LeaveCommentTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void PageFactory() throws InterruptedException {
        // Login
        driver.get("http://127.0.0.1:8000");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.clickSignUpLink();
        String generatedUsername = random();
        loginPage.enterUsername(generatedUsername);
        String generatedPassword = random();
        loginPage.enterPassword(generatedPassword);
        loginPage.enterConfirmPassword(generatedPassword);
        loginPage.clickRegisterButton();
        Thread.sleep(500);
        String actualUsername = loginPage.getActualUsername();
        Assert.assertEquals(actualUsername, generatedUsername, "Unexpected username");
        Thread.sleep(500);

        // Laptop
        HomePage homePage = new HomePage(driver);
        homePage.clickCategory("HP");
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.clickProduct("Ноутбук HP Pavilion 14-dv0047ua");

        // Comment
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
        String comment = "Good Product";
        productPage.addComment(comment);
        Thread.sleep(3000);
        String actualCommentText = productPage.getLastCommentText();
        String expectedCommentText = "Good Product";
        Assert.assertTrue(actualCommentText.contains(expectedCommentText), "Comment text does not contain the expected value");

        // Check if it's sent
        // Check if comment is sent
        WebElement commentWrapper = driver.findElement(By.className("comment-wrapper"));
        List<WebElement> divElements = commentWrapper.findElements(By.tagName("div"));
        WebElement lastDivElement = divElements.get(0);
        WebElement pElement = lastDivElement.findElement(By.tagName("p"));
        String pText = pElement.getText();
        String expectedText1 = "Good Product";
        boolean isTextContainsWord = pText.contains(expectedText1);

        Assert.assertTrue(isTextContainsWord, "Text text contains word: " + pText);



        // Decorator
        ProductPageDecorator decoratedProductPage = new ProductPageDecorator(driver);
        decoratedProductPage.addCommentWithTimestamp("Great product!");
        String lastComment = decoratedProductPage.getLastCommentText();
        System.out.println("Last comment: " + lastComment);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
}
