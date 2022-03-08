import baseClass.BaseClass;
import javafx.scene.layout.Priority;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import utilities.ReadConfig;
import utilities.Reporting;
import webDriverManager.WebDriverManager;

import javax.xml.bind.SchemaOutputResolver;

public class MomentumRegistrationPageTCs {

    static final Logger narrator = LogManager.getLogger(MomentumRegistrationPageTCs.class);
    WebDriver driver;
    WebDriverManager webDriverManager;
    ReadConfig readConfig;
    BaseClass baseClass;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    PageObjectManager pageObjectManager;
    Reporting reporting;

    public MomentumRegistrationPageTCs() throws Exception {

        webDriverManager = new WebDriverManager();
        readConfig = new ReadConfig("config");
        driver = webDriverManager.getWebDriver(readConfig.getBrowser());
        baseClass = new BaseClass(driver);
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        registerPage = pageObjectManager.getRegisterPage();
        loginPage = pageObjectManager.getLoginPage();
        reporting = new Reporting();

    }

    @Test(priority = 0)
    public void verifyTitle() throws Exception {
        webDriverManager.launchMomentumE2E();
        Thread.sleep(2000);

        String expTitle = "My Form";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle, actTitle);
        baseClass.CaptureScreenshot(driver, "verifyTitle");
    }

    @Test(priority = 1)
    public void registerNewUser() throws Exception {
        homePage.clickRegister();
        registerPage.captureUsername1();
        registerPage.captureEmailAddress1();
        registerPage.capturePassword1();
        baseClass.CaptureScreenshot(driver, "Registration Form is captured");
        registerPage.clickSubmit();
        String validateMsg = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
        Assert.assertEquals("Registration successful", validateMsg);
        baseClass.CaptureScreenshot(driver, "Registration is Successful");
    }

    @Test(priority = 2)
    public void resetRegistrationForm() throws Exception {
        homePage.clickRegister();
        registerPage.captureUsername1();
        registerPage.captureEmailAddress1();
        registerPage.capturePassword1();
        baseClass.CaptureScreenshot(driver, "Registration Form before Reset");
        registerPage.clickReset();
        //validated only on username, to check if the username is removed
        String validateMsg = driver.findElement(By.xpath("//input[@name='username']")).getAttribute("");
        Assert.assertEquals(null,validateMsg);
        baseClass.CaptureScreenshot(driver, "Registration Form after reset");
    }
    @Test(priority = 2)
    public void unableToRegisterWithExistingEmailAddress() throws Exception {
        registerPage.captureUsername1();
        registerPage.existingEmailAddress1();
        registerPage.capturePassword1();
        baseClass.CaptureScreenshot(driver, "Registration Form before Reset");
        registerPage.clickSubmit();
        String validateMsg = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
        Assert.assertEquals("Email already used", validateMsg);
        baseClass.CaptureScreenshot(driver, "Email already used");
    }
}
