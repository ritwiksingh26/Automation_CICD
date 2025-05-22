package selenium_project.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium_project.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest  = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Auto-generated method stub
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique Thread id (now this will work if running tests parallely)
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Auto-generated method stub
        extentTest.get().log(Status.PASS, "Test has Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        // Auto-generated method stub
        extentTest.get().log(Status.FAIL, "Test failed");
        extentTest.get().fail(result.getThrowable()); 

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String imgPath = null;
        try {
            imgPath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(imgPath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Auto-generated method stub
        extent.flush();
    }
}
