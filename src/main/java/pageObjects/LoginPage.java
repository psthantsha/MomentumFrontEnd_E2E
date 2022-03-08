package pageObjects;
import baseClass.BaseClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    BaseClass baseClass;

    //Constructor to initialize the driver
    public LoginPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        baseClass = new BaseClass(driver);
    }
    //Generate random string
    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return (generatedString);
    }
    // locating page objects using the @Findby annotation
    @FindBy(xpath = "//h1[contains(., 'Login')]")
    WebElement lblLogin;

    // locating page objects using the @Findby annotation
    @FindBy(xpath = "//button[contains(., 'Reset')]")
    WebElement btnReset;

    @FindBy(xpath = "//button[contains(., 'Submit')]")
    WebElement btnSubmit;

    @FindBy(xpath = "//*[@id=\"mylink\"]")
    WebElement linkLogin;

    @FindBy(xpath = "//a[contains(., 'login again')]")
    WebElement lnkLoginAgain;

    @FindBy(xpath = "//a[contains(., 'logout')]")
    WebElement lnkLogout;

    @FindBy(xpath = "/html/body/div[1]/h2")
    WebElement lblSuccessful;

    public void regSuccess(String msg) {
        try {
            baseClass.getObjectText(lblSuccessful, msg);
        } catch (Exception e) {
            System.out.println("Could not click reset button" + e.getStackTrace());
        }
    }
    //Identify webElements and send data
    public void captureEmailAddress1() {
        String email = randomString() + "@gmail.com";
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    }

    public void existingEmailAddress1() {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test1@test.com");
    }

    public void capturePassword1() {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
    }
    public void captureNonRegisteredPassword() {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Pa12345");
    }
    // Click Reset from login page
    public void clickReset() {
        try {
            baseClass.clickObject(btnReset);
        } catch (Exception e) {
            System.out.println("Could not click reset button" + e.getStackTrace());
        }
    }
    // Click Submit from login page
    public void clickSubmit() {
        try {
            baseClass.clickObject(btnSubmit);
        } catch (Exception e) {
            System.out.println("Could not click submit button" + e.getStackTrace());
        }
    }
    // Click Login on register page from home page
    public void clickLoginOnReg() {
        try {
            baseClass.clickObject(linkLogin);
        } catch (Exception e) {
            System.out.println("Could not click login on register page" + e.getStackTrace());
        }
    }
    // Click Login again after failed attempted login
    public void clickLoginAgain() {
            try {
                baseClass.clickObject(lnkLoginAgain);
            } catch (Exception e) {
                System.out.println("Could not click login again" + e.getStackTrace());
            }
    }
    // Click Logout from login page
    public void clickLogout() {
        try {
            baseClass.clickObject(lnkLogout);
        } catch (Exception e) {
            System.out.println("Could not click logout" + e.getStackTrace());
        }
    }
}