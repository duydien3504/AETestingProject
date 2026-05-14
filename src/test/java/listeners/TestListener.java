package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import core.PlaywrightFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;
import utils.ExtentTestManager;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentReportManager.initReport();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());

        ExtentTestManager.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.get().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());

        if (PlaywrightFactory.getPage() != null) {
            try {
                String base64Screenshot = PlaywrightFactory.takeScreenShot();
                ExtentTestManager.get().fail("Screenshot Error:",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        ExtentTestManager.clear(); 
    }
}
