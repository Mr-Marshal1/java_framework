package PoArchitecture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCategory(String categoryName) {
        WebElement categoryElement = driver.findElement(By.xpath("//a[contains(text(), '" + categoryName + "')]"));
        categoryElement.click();
    }
}
