package tests;

import config.Browser;
import config.PropertyFileReader;
import config.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import webpages.HomePage;
import webpages.LogIntoQuestionPro;
import webpages.WaitForElementFactory;

public class LoginTest {
    WebDriver driver;
    public String websiteTitle = "Free Online Survey Software and Tools | QuestionPro®";
    HomePage homePage;
    LogIntoQuestionPro login;
    PropertyFileReader reader;
    TestListener listener;
    WaitForElementFactory wait;

    @BeforeTest
    public void setUp(){
        System.out.println("- Inside setUp Method");
        reader = new PropertyFileReader();
        driver = Browser.startBrowser("chrome",reader.getValue("url"));
        wait = new WaitForElementFactory(driver);
        homePage = new HomePage(driver);
        login = new LogIntoQuestionPro(driver,wait);
        listener = new TestListener(driver);
    }

    @Test(priority = 1)
    public void clickOnSignInTest(){
        System.out.println("Inside LogIn test");
        homePage.isWebsiteLoaded();
        homePage.clickOnLoginButton();
        homePage.isLoginPageLoaded();
    }

    @Test(priority = 2)
    public void logIntoQuestionProTest(){
        System.out.println("Inside Login to QuestionPro");
        login.login(reader.getValue("username"),reader.getValue("password"));
        login.isLoginSuccess();
    }

    @AfterMethod
    public void runAfterMethod(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            listener.onTestFailure(result);
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        System.out.println("Inside tearDown");
        driver.quit();
        System.out.println("All executed");
    }
}
