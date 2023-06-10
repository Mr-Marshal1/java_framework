package DataProvider;
import PoArchitecture.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestDate {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "userData", dataProviderClass = Provider.class)
    public void loginTest(String username, String password) throws InterruptedException {
        driver.get("http://127.0.0.1:8000");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.clickSignUpLink();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.enterConfirmPassword(password);
        loginPage.clickRegisterButton();
        Thread.sleep(500);

        WebElement messagesElement = driver.findElement(By.cssSelector("ul.messages"));
        Assert.assertTrue(messagesElement.isDisplayed(), "Error message appeared");

    }
    @Test(dataProvider = "userLogin", dataProviderClass = Provider.class)
    public void logTest(String username, String password) throws InterruptedException {
        driver.get("http://127.0.0.1:8000/login/"); // Замените URL на вашу страницу входа

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        loginButton.click();

        WebElement messagesElement = driver.findElement(By.cssSelector("ul.messages"));
        Assert.assertTrue(messagesElement.isDisplayed(), "Error message appeared");

    }
}