package pageObjectManager;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class PageObjectManager {

    WebDriver driver;

    private static HomePage homePage;
    private static LoginPage loginPage;
    private static RegisterPage registerPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage getHomePage(){

        return homePage = new HomePage(driver);
    }
    public LoginPage getLoginPage(){

        return loginPage = new LoginPage(driver);
    }
    public RegisterPage getRegisterPage(){

        return registerPage = new RegisterPage(driver);
    }

}