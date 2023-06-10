package FactoryAndWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(className = "log")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(), 'Sign up')]")
    private WebElement signUpLink;

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password1")
    private WebElement passwordInput;

    @FindBy(name = "password2")
    private WebElement confirmPasswordInput;

    @FindBy(css = "input[value='Register']")
    private WebElement registerButton;

    @FindBy(id = "un")
    private WebElement usernameElement;

    private CommonElementWrapper loginButtonWrapper;
    private CommonElementWrapper signUpLinkWrapper;
    private CommonElementWrapper usernameInputWrapper;
    private CommonElementWrapper passwordInputWrapper;
    private CommonElementWrapper confirmPasswordInputWrapper;
    private CommonElementWrapper registerButtonWrapper;
    private CommonElementWrapper usernameElementWrapper;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loginButtonWrapper = new CommonElementWrapper(loginButton);
        signUpLinkWrapper = new CommonElementWrapper(signUpLink);
        usernameInputWrapper = new CommonElementWrapper(usernameInput);
        passwordInputWrapper = new CommonElementWrapper(passwordInput);
        confirmPasswordInputWrapper = new CommonElementWrapper(confirmPasswordInput);
        registerButtonWrapper = new CommonElementWrapper(registerButton);
        usernameElementWrapper = new CommonElementWrapper(usernameElement);
    }

    public void clickLoginButton() {
        loginButtonWrapper.click();
    }

    public void clickSignUpLink() {
        signUpLinkWrapper.click();
    }

    public void enterUsername(String username) {
        usernameInputWrapper.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInputWrapper.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInputWrapper.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        registerButtonWrapper.click();
    }

    public String getActualUsername() {
        return usernameElementWrapper.getText();
    }
}
