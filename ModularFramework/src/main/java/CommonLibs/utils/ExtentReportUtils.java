package CommonLibs.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtils {

	ExtentReports extentReport;

	ExtentHtmlReporter htmlReporter;

	ExtentTest extentTest;

	public ExtentReportUtils(String htmlReportFilename) {

		htmlReporter = new ExtentHtmlReporter(htmlReportFilename);

		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);

	}

	public void createTest(String TestcaseName) {

		extentTest = extentReport.createTest(TestcaseName);
	}

	public void addLog(Status status, String comment) {
		
		extentTest.log(status, comment);
	}

	public void addScreenshotToTheReport(String ScreenshotFilename) throws Exception {

		extentTest.addScreenCaptureFromPath(ScreenshotFilename);
	}

	public void closeReport() {

		extentReport.flush();
	}

}
