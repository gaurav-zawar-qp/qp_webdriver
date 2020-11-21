package webpages;

import config.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandleDropDownAndSelectList {
    WebDriver driver;
    WaitForElementFactory wait;
    BasicElementWrapper commonElementFactory;
    PropertyFileReader reader;

    public HandleDropDownAndSelectList(WebDriver driver, WaitForElementFactory wait, BasicElementWrapper commonElementFactory){
        this.driver = driver;
        this.wait = wait;
        this.commonElementFactory = commonElementFactory;
    }

    public boolean isURLLoaded(){
        return commonElementFactory.verifyExitButtonOnTakeSurvey();
    }

    public void handleDropDown(){
        WebElement elementToClick = driver.findElement(By.cssSelector("button[class='btn dropdown-toggle btn-default']"));
        wait.waitForElementToBeClickable(elementToClick,10);
        elementToClick.click();
        List<WebElement> getList = driver.findElements(By.cssSelector("a[role='option']"));
        getList.get(10).click();
        wait.waitForElementToBeVisible(driver.findElement(By.cssSelector("input[class='form-input other-input']")),10);
        driver.findElement(By.cssSelector("input[class='form-input other-input']")).sendKeys("Other selected");
        commonElementFactory.clickOnTakeSurveySubmitButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void handleLookUpTableQuestion(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        wait.waitForElementToBeVisible(driver.findElement(By.cssSelector("fieldset[class='Q2 survey-question-wrapper  has-separator']")),10);
        driver.findElement(By.cssSelector("input[class='form-input ']")).sendKeys("California");
        commonElementFactory.clickOnTakeSurveySubmitButton();
        wait.waitForElementToBeVisible(driver.findElement(By.cssSelector("fieldset[class='Q3 survey-question-wrapper  has-separator']")),10);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    public void handleDropDownMatrixQuestion(){
        try {
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            List<WebElement> listOfDropDowns = driver.findElements(By.cssSelector("button[class='btn dropdown-toggle btn-default']"));
            listOfDropDowns.get(0).sendKeys("Strongly disagree", Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            listOfDropDowns.get(1).sendKeys("Disagree",Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            listOfDropDowns.get(2).sendKeys("Agree",Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            commonElementFactory.clickOnTakeSurveySubmitButton();
            wait.waitForElementToBeClickable(driver.findElement(By.cssSelector("fieldset[class='Q4 survey-question-wrapper  has-separator']")),10);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }

    public void handleSelectList(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[class='btn dropdown-toggle bs-placeholder btn-default']")).click();
        wait.waitForElementToBeClickable(driver.findElement(By.cssSelector("a[role='option']")),10);
        List<WebElement> element = driver.findElements(By.cssSelector("a[role='option']"));
        element.get(1).click();
        element.get(3).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//html")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        commonElementFactory.clickOnTakeSurveySubmitButton();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(commonElementFactory.surveyFinishPageIsDisplayed());
    }
}
