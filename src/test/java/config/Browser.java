package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    static WebDriver driver;
    static String chromeDriverPath = "C:\\Selenium JARS\\chromedriver_win32\\chromedriver.exe" ;
    static String firefoxDriverPath = "C:\\Selenium JARS\\geckodriver.exe";

    public static WebDriver startBrowser (String browser, String url){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",chromeDriverPath);
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
            driver = new FirefoxDriver();
        }else {
            System.out.println("Browser cannot be launched");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        return driver;
    }
}
