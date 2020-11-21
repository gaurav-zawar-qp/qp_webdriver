package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public By loginButton = By.cssSelector("a[class='login-link']");
    public By emailAddressInputBox = By.id("EmailAddress");
    public String websiteTitle = "Free Online Survey Software and Tools | QuestionPro®";

    public HomePage(WebDriver driver){
        System.out.println("Inside Homepage Constructor");
        this.driver = driver;
    }

    public boolean isWebsiteLoaded(){
        System.out.println("Is Website Loaded");
        String title = driver.getTitle();
        return websiteTitle.equalsIgnoreCase(title);
    }

    public void clickOnLoginButton(){
        System.out.println("Click on Log In button");
        driver.findElement(loginButton).click();
    }

    public boolean isLoginPageLoaded(){
        System.out.println("Verify Login page is loaded");
        return driver.findElement(emailAddressInputBox).isDisplayed();
    }
}
