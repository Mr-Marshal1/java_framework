package FactoryAndWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    private WebDriver driver;

    @FindBy(css = "input[placeholder='Write your message here..']")
    private WebElement commentInput;

    @FindBy(className = "comment-wrapper")
    private WebElement commentWrapper;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addComment(String comment) {
        commentInput.sendKeys(comment);
        commentInput.sendKeys(Keys.ENTER);
    }

    public String getLastCommentText() {
        List<WebElement> divElements = commentWrapper.findElements(By.tagName("div"));
        WebElement lastDivElement = divElements.get(0);
        WebElement pElement = lastDivElement.findElement(By.tagName("p"));
        return pElement.getText();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
