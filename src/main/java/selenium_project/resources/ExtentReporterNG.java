package selenium_project.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    static ExtentReports extent;
    public static ExtentReports getReportObject()
    {
        String path = System.getProperty("user.dir") + "\\ExtentreportsDemo\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        //ExtentReports - this is main class responsible for reports and ExtentSparkReporter attaches the configured report to ExtentReport
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        //Assigning tester name in the report using ExtentReports
        extent.setSystemInfo("Tester", "Ritwik Singh");
        return extent;
    }
}
