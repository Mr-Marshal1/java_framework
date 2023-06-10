package framework_tests.WebUIEtE;

//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

@Test(groups = "SignUp")
public class SignUp {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void testLogin() throws InterruptedException {
        driver.get("http://127.0.0.1:8000");
        // Click on Login
        WebElement element = driver.findElement(By.className("log"));
        element.click();
        Thread.sleep(500);
        WebElement element1 = driver.findElement(By.xpath("//a[contains(text(), 'Sign up')]"));
        element1.click();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
