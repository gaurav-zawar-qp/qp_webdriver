package webpages;

import config.Browser;
import config.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DragNDropAndSliderTest {
    WebDriver driver;
    WaitForElementFactory wait;
    BasicElementWrapper commonElementFactory;
    PropertyFileReader reader;

    public DragNDropAndSliderTest(WebDriver driver, WaitForElementFactory wait, BasicElementWrapper commonElementFactory){
        this.driver = driver;
        this.wait = wait;
        this.commonElementFactory = commonElementFactory;
    }

    public void isURLLoaded(){
        commonElementFactory.verifyExitButtonOnTakeSurvey();
    }

    public void sortTheOptions() {
        List<WebElement> sortingList = driver.findElements(By.xpath("//ul[contains(@id,'target')]/li"));
        Actions builder = new Actions(driver);
        Action sortTheList = builder.clickAndHold(sortingList.get(0)).moveToElement(sortingList.get(2)).release(sortingList.get(2)).build();
        sortTheList.perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String verifyTextOfElement = driver.findElement(By.xpath("//ul[contains(@id,'target')]/li[3]//span[2]")).getText();
        System.out.println(verifyTextOfElement);
        Assert.assertTrue(verifyTextOfElement.equalsIgnoreCase("Skiing"));
        commonElementFactory.clickOnTakeSurveySubmitButton();
    }

    public void handleDragNDropQuestion(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.waitForElementToBeVisible(driver.findElement(By.cssSelector("li[aria-label='Skiing']")),10);
        WebElement target = driver.findElement(By.xpath("//ul[contains(@id,'target')]"));
        List<WebElement> elementList = driver.findElements(By.xpath("//ul[contains(@id,'source')]/li"));
        Iterator<WebElement> iterator = elementList.iterator();

        //Iterator example for drag and drop
        while (iterator.hasNext()){
            Actions builder = new Actions(driver);
            Action dragNDrop = builder.clickAndHold(iterator.next()).moveToElement(target).release(target).build();
            dragNDrop.perform();
        }
        //foreach example
        /*for (WebElement element:elementList){
            Actions builder = new Actions(driver);
            Action dragNDrop = builder.clickAndHold(element).moveToElement(target).release(target).build();
            dragNDrop.perform();
        }*/
    }

    public void handleTextSliderQuestion(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement Q2 = driver.findElement(By.cssSelector("fieldset[class='Q2 survey-question-wrapper  has-separator']"));
        wait.waitForElementToBeVisible(Q2,10);
        List<WebElement> list = driver.findElements(By.cssSelector("a[class='ui-slider-handle ui-state-default ui-corner-all']"));
        /*for (int i=0;i<list.size();i++){
            WebElement slider = list.get(i);
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        commonElementFactory.clickOnTakeSurveySubmitButton();*/
        Actions builder = new Actions(driver);
        Action action = builder.dragAndDropBy(list.get(0),150,0)
                .dragAndDropBy(list.get(1),300,0)
                .dragAndDropBy(list.get(2),400,0)
                .build();
        action.perform();
        list = driver.findElements(By.xpath("//div[@class='text-slide-value']"));
        Assert.assertTrue(list.get(0).getText().equalsIgnoreCase("Disagree"));
        Assert.assertTrue(list.get(1).getText().equalsIgnoreCase("Neutral"));
        Assert.assertTrue(list.get(2).getText().equalsIgnoreCase("Agree"));
        commonElementFactory.clickOnTakeSurveySubmitButton();
    }

    public void handleNumericSlider(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement Q3 = driver.findElement(By.cssSelector("fieldset[class='Q3 survey-question-wrapper  has-separator']"));
        wait.waitForElementToBeVisible(Q3,10);
        List<WebElement> elementList = driver.findElements(By.cssSelector("a[class='ui-slider-handle ui-state-default ui-corner-all']"));
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(elementList.get(0),300,0)
                .dragAndDropBy(elementList.get(1),400,0)
                .dragAndDropBy(elementList.get(2),450,0)
                .build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        commonElementFactory.clickOnTakeSurveySubmitButton();
        commonElementFactory.surveyFinishPageIsDisplayed();
    }

    /*public static void main(String[] args) {
        PropertyFileReader reader = new PropertyFileReader();
        WebDriver driver = Browser.startBrowser("chrome",reader.getValue("drag-n-drop-slider"));
        WaitForElementFactory wait = new WaitForElementFactory(driver);
        BasicElementWrapper commonElementFactory = new BasicElementWrapper(driver,wait);
        DragNDropAndSliderTest dragNDropAndSliderTest = new DragNDropAndSliderTest(driver,wait,commonElementFactory);
        dragNDropAndSliderTest.isURLLoaded();
        dragNDropAndSliderTest.handleDragNDropQuestion();
        dragNDropAndSliderTest.sortTheOptions();
        dragNDropAndSliderTest.handleTextSliderQuestion();
        dragNDropAndSliderTest.handleNumericSlider();
    }*/
}
