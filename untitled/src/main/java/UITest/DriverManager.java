package UITest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class DriverManager {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver(String browser) {
        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            String selectedBrowser = (browser != null) ? browser.toLowerCase() : "firefox";

            switch (selectedBrowser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                default:
                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\MARSHAL\\Desktop\\Framework\\untitled\\drivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
            }

            driverThreadLocal.set(driver);
        }

        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
