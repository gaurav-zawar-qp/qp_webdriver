package tests;

import config.Browser;
import config.PropertyFileReader;
import config.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import webpages.BasicElementWrapper;
import webpages.HandleDropDownAndSelectList;
import webpages.WaitForElementFactory;

public class TestDropDownsAndSelectList {
    WebDriver driver;
    PropertyFileReader reader;
    WaitForElementFactory wait;
    BasicElementWrapper commonElementFactory;
    HandleDropDownAndSelectList dropDownAndSelectList;
    TestListener listener;

    @BeforeTest
    public void setUp(){
        reader = new PropertyFileReader();
        driver = Browser.startBrowser("chrome",reader.getValue("dropdown-select-list"));
        wait = new WaitForElementFactory(driver);
        commonElementFactory = new BasicElementWrapper(driver,wait);
        dropDownAndSelectList = new HandleDropDownAndSelectList(driver,wait,commonElementFactory);
        listener = new TestListener(driver);
    }

    @Test(priority = 1)
    public void testDropdownClick(){
        Assert.assertTrue(dropDownAndSelectList.isURLLoaded());
        dropDownAndSelectList.handleDropDown();
    }

    @Test(priority = 2)
    public void testLookUpTableClick(){
        dropDownAndSelectList.handleLookUpTableQuestion();
    }

    @Test(priority = 3)
    public void testDropdownMatrixClick(){
        dropDownAndSelectList.handleDropDownMatrixQuestion();
    }

    @Test(priority = 4)
    public void testSelectListClick(){
        dropDownAndSelectList.handleSelectList();
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
