package FactoryAndWrapper;
import org.openqa.selenium.WebElement;

public class CommonElementWrapper {
    private WebElement element;

    public CommonElementWrapper(WebElement element) {
        this.element = element;
    }

    public void click() {
        element.click();
    }

    public void sendKeys(String text) {
        element.sendKeys(text);
    }

    public String getText() {
        return element.getText();
    }

}
