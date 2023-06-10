package PoArchitecture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class CategoryPage {
    private WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProduct(String productName) {
        WebElement productElement = driver.findElement(By.xpath("//a[contains(text(), '" + productName + "')]"));
        productElement.click();
    }
}
