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
import webpages.BasicElementWrapper;
import webpages.DragNDropAndSliderTest;
import webpages.WaitForElementFactory;

public class TestDragNDropAndSlider {
    WebDriver driver;
    BasicElementWrapper commonElementFactory;
    WaitForElementFactory wait;
    PropertyFileReader reader;
    DragNDropAndSliderTest dragNDropAndSliderTest;
    TestListener listener;

    @BeforeTest
    public void setUp(){
        reader = new PropertyFileReader();
        driver = Browser.startBrowser("chrome",reader.getValue("drag-n-drop-slider"));
        wait = new WaitForElementFactory(driver);
        commonElementFactory = new BasicElementWrapper(driver,wait);
        dragNDropAndSliderTest = new DragNDropAndSliderTest(driver,wait,commonElementFactory);
        listener = new TestListener(driver);
    }

    @Test(priority = 1)
    public void performDragAndDropAction(){
        dragNDropAndSliderTest.isURLLoaded();
        dragNDropAndSliderTest.handleDragNDropQuestion();
    }

    @Test(priority = 2)
    public void performSortingOfOptions(){
        dragNDropAndSliderTest.sortTheOptions();
    }

    @Test(priority = 3)
    public void testMoveTextSlider(){
        dragNDropAndSliderTest.handleTextSliderQuestion();
    }

    @Test(priority = 4)
    public void testMoveNumericSlider(){
        dragNDropAndSliderTest.handleNumericSlider();
    }

    @AfterMethod
    public void runAfterMethod(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            listener.onTestFailure(result);
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
