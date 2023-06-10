package DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class Provider {
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][] {
                {"user1", "passwor"},
                {"user2", "password2"},
                {"user3", "password3"},
                {"user4", "password4"},
                {"user5", "password5"}
        };
    }

    @DataProvider(name = "userLogin")
    public Object[][] userLogin() {
        return new Object[][] {
                {"user1", "12345678"},
                {"user2", "1234567"},
                {"user3", "123456"},
                {"user4", "12345"},
                {"user5", "1234"}
        };
    }
}
