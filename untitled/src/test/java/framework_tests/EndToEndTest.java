package framework_tests;

import PoArchitecture.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.UUID;

public class EndToEndTest {
    private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void testElements() throws InterruptedException {
        driver.get("http://127.0.0.1:8000");

        // Login click and registration
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

        // Check id
        WebElement usernameElement = driver.findElement(By.id("un"));
        String actualUsername = usernameElement.getText();
        Assert.assertEquals(actualUsername, generatedUsername, "Unexpected username");
        Thread.sleep(500);

        // Laptops
        HomePage homePage = new HomePage(driver);
        homePage.clickCategory("HP");
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.clickProduct("Ноутбук HP Pavilion 14-dv0047ua");


        // Generate Comment
        ProductPage productPage = new ProductPage(driver);
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

    }

    @AfterClass
    public void exit() {
        {
            driver.quit();
        }
    }
    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
}
