package pageObjects;
import baseClass.BaseClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    BaseClass baseClass;

    //Constructor to initialize the driver
    public RegisterPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        baseClass = new BaseClass(driver);
    }
    //Generate random string
    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return (generatedString);
    }

    // locating page objects using the @Findby annotation
    @FindBy(xpath = "//button[contains(., 'Reset')]")
    WebElement btnReset;

    @FindBy(xpath = "//button[contains(., 'Submit')]")
    WebElement btnSubmit;

    @FindBy(xpath = "//*[@id=\"mylink\"]")
    WebElement linkLogin;

    @FindBy(xpath = "/html/body/div[1]/h2")
    WebElement lblSuccessful;

    //Identify and send data
    public void captureUsername1() {
        String uname = randomString()+"Name";
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(uname);
    }
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
    // Click reset from register page
    public void clickReset() {
        try {
            baseClass.clickObject(btnReset);
        } catch (Exception e) {
            System.out.println("Could not click reset button" + e.getStackTrace());
        }
    }
    // Click submit from register page
    public void clickSubmit() {
        try {
            baseClass.clickObject(btnSubmit);
        } catch (Exception e) {
            System.out.println("Could not click submit textbox" + e.getStackTrace());
        }
    }
    // Click Login from register page
    public void clickLoginOnReg() {
        try {
            baseClass.clickObject(linkLogin);
        } catch (Exception e) {
            System.out.println("Could not click password textbox" + e.getStackTrace());
        }
    }
}
