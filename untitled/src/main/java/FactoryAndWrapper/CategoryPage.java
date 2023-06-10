package FactoryAndWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {
    @FindBy(xpath = "//a[contains(text(), 'Ноутбук HP Pavilion 14-dv0047ua')]")
    private WebElement hpLaptopLink;

    public CategoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickProduct(String product) {
        hpLaptopLink.click();
    }
}
