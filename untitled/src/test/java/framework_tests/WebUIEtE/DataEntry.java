package framework_tests.WebUIEtE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.UUID;

public class DataEntry {
    private WebDriver driver;
    private String generatedUsername;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test(dependsOnMethods = "WebUIEtE.SignUp.testLogin")
    public void testLogin() throws InterruptedException {
        SignUp signUp = new SignUp();
        signUp.setUp();

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
        Thread.sleep(500);

        signUp.close();
    }

    public String getGeneratedUsername() {
        return generatedUsername;
    }

    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
