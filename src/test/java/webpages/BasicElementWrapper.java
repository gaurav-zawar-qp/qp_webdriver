package webpages;

import config.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasicElementWrapper {
    WebDriver driver;
    PropertyFileReader reader;
    List<WebElement> listOfElements;
    WebElement element;
    WaitForElementFactory wait;
    By exitSurveyLink = By.xpath("//a[@title='Exit Survey']");
    By submitButton = By.id("SurveySubmitButtonElement");
    By finishPageText = By.id("finishText");

    public BasicElementWrapper(WebDriver driver, WaitForElementFactory wait){
        this.driver = driver;
        this.wait = wait;
    }

    private WebElement finishPage(){
        return driver.findElement(finishPageText);
    }

    private WebElement submitButton(){
        return driver.findElement(submitButton);
    }

    private WebElement exitSurveyLink(){
        return driver.findElement(exitSurveyLink);
    }

    public boolean verifyExitButtonOnTakeSurvey(){
        wait.waitForElementToBeClickable(exitSurveyLink(),10);
        return exitSurveyLink().isDisplayed();
    }

    public void clickOnTakeSurveySubmitButton(){
        wait.waitForElementToBeClickable(submitButton(),10);
        submitButton().click();
    }

    public boolean surveyFinishPageIsDisplayed(){
        wait.waitForElementToBeVisible(finishPage(),10);
        return finishPage().isDisplayed();
    }

    public WebElement getIndexOfElement(By element, int index){
        listOfElements = driver.findElements(element);
        return listOfElements.get(index);
    }

    public void clickOnRadioButton(By radioButton, int index){
        WebElement element = getIndexOfElement(radioButton,index);
        element.click();
        if (element.isSelected()) {
            System.out.println("- Radio button is already selected");
        }else {
            try {
                element.click();
            }catch (Exception e){
                System.out.println("- Radio button is not clickable");
            }
        }
    }

    public void clickOnCheckbox(By checkboxButton, int index){
        WebElement element = getIndexOfElement(checkboxButton,index);
        element.click();

        if (element.isSelected()){
            System.out.println("- Checkbox is already selected");
        }else {
            try {
                element.click();
            }catch (Exception e){
                System.out.println("- Checkbox button is not clickable");
            }
        }
    }
}
