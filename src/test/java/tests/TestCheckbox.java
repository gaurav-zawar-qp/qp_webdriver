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
import webpages.HandleCheckbox;
import webpages.WaitForElementFactory;

public class TestCheckbox {
    WebDriver driver;
    PropertyFileReader reader;
    WaitForElementFactory wait;
    BasicElementWrapper commonElementFactory;
    HandleCheckbox checkBox;
    TestListener listener;
    By checkboxQuestion = By.xpath("//span[@class=\"control-label\"]");
    By matrixCheckboxQuestion = By.cssSelector("label[class='controls control-selection']");

    @BeforeTest
    public void beforeTest(){
        reader = new PropertyFileReader();
        driver = Browser.startBrowser("chrome",reader.getValue("checkbox-url"));
        listener = new TestListener(driver);
        wait = new WaitForElementFactory(driver);
        commonElementFactory = new BasicElementWrapper(driver,wait);
        checkBox = new HandleCheckbox(driver,wait,commonElementFactory);
    }

    @Test(priority = 1)
    public void testClickOnSelectManyQuestion(){
        Assert.assertTrue(checkBox.isURLLoaded());
        checkBox.clickOnCheckboxQuestion(checkboxQuestion,0,10);
        checkBox.clickOnCheckboxQuestion(checkboxQuestion,4,10);
        checkBox.clickOnSubmitButton();
    }

    @Test(priority = 2)
    public void testClickOnCheckboxMatrixQuestion(){
        checkBox.clickOnMatrixCheckbox(matrixCheckboxQuestion,4,10);
        checkBox.clickOnMatrixCheckbox(matrixCheckboxQuestion,8,10);
        checkBox.clickOnMatrixCheckbox(matrixCheckboxQuestion,11,10);
        checkBox.clickOnSubmitButton();
        Assert.assertTrue(checkBox.isFinishPageLoaded());
    }

    @AfterMethod
    public void runAfterMethod(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            listener.onTestFailure(result);
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
