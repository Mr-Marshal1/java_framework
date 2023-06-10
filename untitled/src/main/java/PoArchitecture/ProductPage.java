package PoArchitecture;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addComment(String comment) {
        WebElement commentInput = driver.findElement(By.cssSelector("input[placeholder='Write your message here..']"));
        commentInput.sendKeys(comment);
        commentInput.sendKeys(Keys.ENTER);
    }

    public String getLastCommentText() {
        WebElement commentWrapper = driver.findElement(By.className("comment-wrapper"));
        List<WebElement> commentDivs = commentWrapper.findElements(By.tagName("div"));
        WebElement lastCommentDiv = commentDivs.get(0);
        WebElement commentText = lastCommentDiv.findElement(By.tagName("p"));
        return commentText.getText();
    }
}
