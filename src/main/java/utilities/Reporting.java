package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Reporting extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    public void onStart(ITestContext tr){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Assessment FrontEnd - E2E" + timeStamp + ".html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/" +reportName);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
        htmlReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("host name", "localhost");
        extent.setSystemInfo("Organization", "Momentum");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User", "Paxen Thantsha");
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle("Assessment FrontEnd - E2E"); // Report title
        htmlReporter.config().setReportName("Assessment FrontEnd - E2E Functional Testing Report"); // Report name
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Chart location
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr) {
        try {
            logger = extent.createTest(tr.getTestClass().getName() + " :: " + tr.getMethod().getMethodName()); // New entry in the report
            logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Green means passed
            String logText = "<b>Test Method " + tr.getMethod().getMethodName() + "Successful</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        }catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public void onTestFailure(ITestResult tr) {
        String methodName = tr.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(tr.getThrowable().getStackTrace());

        String screenshotPath = System.getProperty("user.dir")+ "\\Screenshots\\" +tr.getName() + ".png"; // Screenshots of type png
        try {
            logger = extent.createTest(tr.getTestClass().getName() +tr.getName() + " :: " + tr.getMethod().getMethodName()); // New entry in the report
            logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
            logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // Red means failed
        } catch (IOException e) {
            e.printStackTrace();
            extent.attachReporter();
        } catch (NullPointerException e) {
            logger.info(exceptionMessage);
        }

        String logText = "<b>test Method " + methodName + "Failed</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);

        try {
            File f = new File(screenshotPath);
            if (f.exists()) {
                try {
                    logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public void onTestSkipped(ITestResult tr) {
        logger = extent.createTest(tr.getName()); // New entry in the report
        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); // Orange means skipped
    }

    public void onFinish(ITestContext testContext) {
        if (extent != null) {
            extent.flush();
        }
    }
}