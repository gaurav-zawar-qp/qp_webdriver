package config;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    WebDriver driver;
    String filePath;
    PropertyFileReader reader;

    public TestListener(WebDriver driver){
        this.driver = driver;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("*** Error "+iTestResult.getName()+" test has failed");
        String methodName = iTestResult.getName().trim();
        ITestContext context = iTestResult.getTestContext();
        context.getAttribute("driver");
        try {
            takeScreenshot(methodName,filePath);
            System.out.println("Screenshot Captured");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public void takeScreenshot(String methodName, String filePath) throws IOException {
        reader = new PropertyFileReader();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screenshot in destination directory with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File(reader.getValue("screenshot-filepath")+methodName+".png"));
    }
}
