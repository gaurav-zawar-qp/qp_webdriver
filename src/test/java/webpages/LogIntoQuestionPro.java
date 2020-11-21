package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogIntoQuestionPro {
    WebDriver driver;
    WaitForElementFactory wait;
    public By emailAddressInputBox = By.id("EmailAddress");
    public By passwordInputBox = By.id("Password");
    public By logInButton = By.xpath("//input[@value='LOG IN']");
    public By licensePageFooter = By.xpath("//div[@id='licenseLevel']");

    public LogIntoQuestionPro(WebDriver driver, WaitForElementFactory wait){
        this.driver = driver;
        this.wait=wait;
    }

    public void login(String username, String password) {
        this.setEmailAddress(username);
        this.setPassword(password);
        this.clickOnLoginButton();
    }

    private void setEmailAddress(String emailAddress){
        wait.waitForElementToBeVisible(driver.findElement(emailAddressInputBox),10);
        driver.findElement(emailAddressInputBox).clear();
        driver.findElement(emailAddressInputBox).click();
        driver.findElement(emailAddressInputBox).sendKeys(emailAddress);
    }

    private void setPassword(String password){
        wait.waitForElementToBeVisible(driver.findElement(passwordInputBox),10);
        driver.findElement(passwordInputBox).clear();
        driver.findElement(passwordInputBox).click();
        driver.findElement(passwordInputBox).sendKeys(password);
    }

    private void clickOnLoginButton() {
        driver.findElement(logInButton).click();
    }

    public boolean isLoginSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(licensePageFooter));
        return driver.findElement(licensePageFooter).isDisplayed();
    }
}
