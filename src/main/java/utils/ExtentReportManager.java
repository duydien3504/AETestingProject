package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports initReport() {
        if(extent == null) {
            String reportPath = Paths.get("reports/TestReport.html").toString();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("AE Testing Report");
            sparkReporter.config().setReportName("UI & API Test Results");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Enviroment", "QA");
            extent.setSystemInfo("Author", "AE Testing Team");
        }
        return extent;
    }
}
