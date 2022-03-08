import baseClass.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.assertions.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import utilities.ReadConfig;
import utilities.Reporting;
import webDriverManager.WebDriverManager;

public class MomentumLoginPageTCs {

    static final Logger narrator = LogManager.getLogger(MomentumLoginPageTCs.class);
    WebDriver driver;
    WebDriverManager webDriverManager;
    ReadConfig readConfig;
    BaseClass baseClass;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    PageObjectManager pageObjectManager;
    Reporting reporting;

    public MomentumLoginPageTCs() throws Exception {

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
    public void loginSuccessful() throws Exception {
        webDriverManager.launchMomentumE2E();
        homePage.clickLogin();
        loginPage.existingEmailAddress1();
        loginPage.capturePassword1();
        loginPage.clickSubmit();
        String validateMsg = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
        Assert.assertEquals("login successful", validateMsg);
        baseClass.CaptureScreenshot(driver, "Login successful");
    }

    @Test(priority = 1)
    public void invalidEmail() throws Exception {
        loginPage.clickLogout();
        loginPage.captureEmailAddress1();
        loginPage.capturePassword1();
        baseClass.CaptureScreenshot(driver, "Registration Form before submit");
        loginPage.clickSubmit();
        String validateMsg = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
        Assert.assertEquals("Invalid email or password", validateMsg);
        baseClass.CaptureScreenshot(driver, "Unable to login");
    }

    @Test(priority = 2)
    public void invalidPassword() throws Exception {
        loginPage.clickLoginAgain();
        loginPage.existingEmailAddress1();
        loginPage.captureNonRegisteredPassword();
        baseClass.CaptureScreenshot(driver, "Registration Form before submit");
        loginPage.clickSubmit();
        String validateMsg = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
        Assert.assertEquals("Invalid email or password", validateMsg);
        baseClass.CaptureScreenshot(driver, "Unable to login");
    }

    @Test(priority = 3)
    public void invalidEmailAndPassword() throws Exception {
        loginPage.clickLoginAgain();
        loginPage.captureEmailAddress1();
        loginPage.captureNonRegisteredPassword();
        baseClass.CaptureScreenshot(driver, "Registration Form before Reset");
        registerPage.clickSubmit();
        String validateMsg = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
        Assert.assertEquals("Invalid email or password", validateMsg);
        baseClass.CaptureScreenshot(driver, "Unable to login");
    }
    @Test(priority = 4)
    public void resetLoginForm() throws Exception {
        loginPage.clickLoginAgain();
        loginPage.captureEmailAddress1();
        loginPage.capturePassword1();
        baseClass.CaptureScreenshot(driver, "Registration Form before reset");
        loginPage.clickReset();
        //validated only on email, to check if the email is removed
        String validateMsg = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("");
        Assert.assertEquals(null,validateMsg);
        baseClass.CaptureScreenshot(driver, " Reset is successful");
    }
}

