package pageObjects;

import baseClass.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    BaseClass baseClass;

    //Constructor to initialize the driver
    public HomePage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        baseClass = new BaseClass(driver);
    }

    // locating page objects using the @Findby annotation
    @FindBy(xpath = "//h1[contains(., 'Register')]")
    WebElement lblRegister;

    @FindBy(xpath = "//a[contains(., 'Register')]")
    WebElement lnkRegister;

    @FindBy(xpath = "//a[contains(., 'Login')]")
    WebElement lnkLogin;

    // Click Register from home page
    public void clickRegister() {
        try {
            baseClass.clickObject(lnkRegister);
        } catch (Exception e) {
            System.out.println("Could not click register " + e.getStackTrace());
        }
    }
    // Click Login from home page
    public void clickLogin() {
        try {
            baseClass.clickObject(lnkLogin);

        } catch (Exception e) {
            System.out.println("Could not click login" + e.getStackTrace());
        }
    }
}