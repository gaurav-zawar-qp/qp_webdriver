package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HandleRadioButtons {
    BasicElementWrapper commonElementFactory;
    WebDriver driver;
    WaitForElementFactory wait;

    public HandleRadioButtons(WebDriver driver, BasicElementWrapper commonElementFactory, WaitForElementFactory wait){
        this.driver = driver;
        this.commonElementFactory = commonElementFactory;
        this.wait = wait;
    }

    public boolean isUrlLoaded(){
        return commonElementFactory.verifyExitButtonOnTakeSurvey();
    }

    public void clickOnRadioButton(By element, int index){
        commonElementFactory.clickOnRadioButton(element,index);
    }

    public void clickOnSubmitButton(){
        commonElementFactory.clickOnTakeSurveySubmitButton();
    }

    public boolean finishPageIsDisplayed(){
        return commonElementFactory.surveyFinishPageIsDisplayed();
    }
}
