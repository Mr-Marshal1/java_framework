import PoArchitecture.CategoryPage;
import PoArchitecture.HomePage;
import PoArchitecture.LoginPage;
import PoArchitecture.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BO {
    private WebDriver driver;

    public BO(WebDriver driver) {
        this.driver = driver;
    }

    public void registerAndAddComment(String username, String password, String category, String product, String comment) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        loginPage.clickSignUpLink();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.enterConfirmPassword(password);
        loginPage.clickRegisterButton();
        Thread.sleep(500);

        WebElement usernameElement = driver.findElement(By.id("un"));
        String actualUsername = usernameElement.getText();
        Assert.assertEquals(actualUsername, username, "Unexpected username");
        Thread.sleep(500);

        HomePage homePage = new HomePage(driver);
        homePage.clickCategory(category);

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.clickProduct(product);

        ProductPage productPage = new ProductPage(driver);
        productPage.addComment(comment);
        Thread.sleep(3000);

        String actualCommentText = productPage.getLastCommentText();
        Assert.assertTrue(actualCommentText.contains(comment), "Comment text does not contain the expected value");
    }
}
