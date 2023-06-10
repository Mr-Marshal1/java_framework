package framework_tests.WebUIEtE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class Laptop {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test(dependsOnMethods = "WebUIEtE.CheckLogin.testLogin")
    public void testLogin() throws InterruptedException {
        WebElement hpclick = driver.findElement(By.xpath("//a[contains(text(), 'HP')]"));
        hpclick.click();
        WebElement hpLaptop = driver.findElement(By.xpath("//a[contains(text(), 'Ноутбук HP Pavilion 14-dv0047ua')]"));
        hpLaptop.click();
    }
    private String random() {
        return UUID.randomUUID().toString().substring(0, 8).replace("-", "");
    }
    @AfterClass
    public void Close() {
        driver.quit();
    }
}
