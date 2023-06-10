package FactoryAndWrapper;

import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;

public class ProductPageDecorator extends ProductPage {
    public ProductPageDecorator(WebDriver driver) {
        super(driver);
    }

    public void addCommentWithTimestamp(String comment) {
        String timestamp = getCurrentTimestamp();
        String commentWithTimestamp = "[" + timestamp + "] " + comment;
        addComment(commentWithTimestamp);
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().toString();
    }
}