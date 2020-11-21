package tests;

import config.Browser;
import config.PropertyFileReader;
import config.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import webpages.BasicElementWrapper;
import webpages.HandleRadioButtons;
import webpages.WaitForElementFactory;

public class TestRadioButton {
    WebDriver driver;
    TestListener listener;
    PropertyFileReader reader;
    WaitForElementFactory wait;
    BasicElementWrapper commonElementFactory;
    HandleRadioButtons radioElement;
    By radioButtons = By.xpath("//span[@class=\"control-label\"]");

    @BeforeTest
    public void beforeTest(){
        reader = new PropertyFileReader();
        driver = Browser.startBrowser("chrome",reader.getValue("radio-button-url"));
        wait = new WaitForElementFactory(driver);
        listener = new TestListener(driver);
        commonElementFactory = new BasicElementWrapper(driver,wait);
        radioElement = new HandleRadioButtons(driver, commonElementFactory, wait);
    }

    @Test
    public void testClickOnRadioButton(){
        Assert.assertTrue(radioElement.isUrlLoaded());
        System.out.println("- Page Loaded successfully");
        radioElement.clickOnRadioButton(radioButtons,0);
        radioElement.clickOnRadioButton(radioButtons,4);
        radioElement.clickOnSubmitButton();
        Assert.assertTrue(radioElement.finishPageIsDisplayed());
    }

    @AfterMethod
    public void runAfterMethod(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            listener.onTestFailure(result);
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        driver.quit();
    }
}
