package webpages;

import config.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HandleCheckbox {
    WebDriver driver;
    PropertyFileReader reader;
    WaitForElementFactory wait;
    BasicElementWrapper commonElementFactory;

    public HandleCheckbox(WebDriver driver, WaitForElementFactory wait, BasicElementWrapper commonElementFactory){
        this.driver = driver;
        this.wait = wait;
        this.commonElementFactory = commonElementFactory;
    }

    public boolean isURLLoaded(){
        return commonElementFactory.verifyExitButtonOnTakeSurvey();
    }

    public void clickOnCheckboxQuestion(By element, int index, int timeOutInSeconds){
        WebElement selectManyCheckbox = commonElementFactory.getIndexOfElement(element,index);
        wait.waitForElementToBeClickable(selectManyCheckbox,timeOutInSeconds);
        selectManyCheckbox.click();
    }

    public void clickOnMatrixCheckbox(By element, int index, int timeOutInSeconds){
        WebElement matrixCheckbox = commonElementFactory.getIndexOfElement(element,index);
        wait.waitForElementToBeClickable(matrixCheckbox, timeOutInSeconds);
        matrixCheckbox.click();
    }

    public void clickOnSubmitButton(){
        commonElementFactory.clickOnTakeSurveySubmitButton();
    }

    public boolean isFinishPageLoaded(){
        return commonElementFactory.surveyFinishPageIsDisplayed();
    }

   /* public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium JARS\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.questionpro.com/t/ALzVHZjpIg");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WaitForElementFactory wait = new WaitForElementFactory(driver);
        BasicElementWrapper commonElementFactory = new BasicElementWrapper(driver,wait);
        HandleCheckbox checkbox = new HandleCheckbox(driver,wait,commonElementFactory);
        checkbox.isURLLoaded();
        checkbox.clickOnCheckboxQuestion(checkbox.checkboxQuestion,0,10);
        checkbox.clickOnCheckboxQuestion(checkbox.checkboxQuestion,4,10);
        checkbox.clickOnSubmitButton();
        checkbox.clickOnMatrixCheckbox(checkbox.matrixCheckboxQuestion,4,10);
        checkbox.clickOnMatrixCheckbox(checkbox.matrixCheckboxQuestion,8,10);
        checkbox.clickOnMatrixCheckbox(checkbox.matrixCheckboxQuestion,11,10);
        checkbox.clickOnSubmitButton();
        checkbox.isFinishPageLoaded();
        System.out.println("- Clicked");
    }*/
}
