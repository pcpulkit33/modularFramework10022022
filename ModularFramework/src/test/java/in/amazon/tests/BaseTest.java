package in.amazon.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import CommonLibs.implementation.CommonDriver;
import CommonLibs.utils.ConfigFileUtils;
import CommonLibs.utils.DateUtils;
import CommonLibs.utils.ExtentReportUtils;
import in.amazon.pages.AmazonHomepage;

public class BaseTest {

	WebDriver driver;
	
	CommonDriver cmnDriver;
	AmazonHomepage homepage;

	String executionStartTime;
	String currentWorkingDirectory;

	String htmlReportFilename;
	ExtentReportUtils extentReportUtils;
	
	String configFilename;
	Properties configproperties;

	@BeforeSuite
	public void preSetup() throws Exception {

		initializeDefaultVariables();

		initializeReport();

	}

	@AfterSuite
	public void postCleanUp() throws Exception {

		closeReport();

	}

	@BeforeMethod
	public void setUp() throws Exception {
		
		invokeBrowser();
		
		initializePages();
		
	}


	@AfterMethod
	public void cleanUp(ITestResult testResult) throws Exception {

		if (testResult.getStatus() == ITestResult.SUCCESS) {
			extentReportUtils.addLog(Status.PASS, "All test step passed...");
		} else if (testResult.getStatus() == ITestResult.FAILURE) {
			extentReportUtils.addLog(Status.FAIL, "One or more test step failed...");
		} else {
			extentReportUtils.addLog(Status.SKIP, "One or more test step is skipped...");
		}

		cmnDriver.closeAllBrowser();
	}

	private void initializeReport() {

		htmlReportFilename = String.format("%s/reports/Amazon-Report-%s.html", currentWorkingDirectory,
				executionStartTime);

		extentReportUtils = new ExtentReportUtils(htmlReportFilename);

	}

	private void initializeDefaultVariables() throws Exception {

		executionStartTime = DateUtils.getcurrentTime();
		
		currentWorkingDirectory = System.getProperty("user.dir");
		
		configFilename = String.format("%s/config/configProperties", currentWorkingDirectory);
				
		configproperties = ConfigFileUtils.configFileReader(configFilename);
	}

	private void closeReport() {

		extentReportUtils.closeReport();

	}

	private void invokeBrowser() throws Exception {

		extentReportUtils.createTest("Setup - Setting up before a test case");

		String browserType = configproperties.getProperty("browserType");
		extentReportUtils.addLog(Status.INFO, "Browser invoked is " + browserType);
		cmnDriver = new CommonDriver(browserType);

		int pageloadTimeout = Integer.parseInt(configproperties.getProperty("pageLoadTimeout"));
		extentReportUtils.addLog(Status.INFO, "Pageload timeout set is " + pageloadTimeout);
		cmnDriver.setPageloadTimeout(pageloadTimeout);

		int implicitwait = Integer.parseInt(configproperties.getProperty("elementDetectionTimeout"));
		extentReportUtils.addLog(Status.INFO, "Element detection timeout is " + implicitwait);
		cmnDriver.setElementDetectionTimeout(implicitwait);

		String baseurl =  configproperties.getProperty("baseUrl");
		extentReportUtils.addLog(Status.INFO, "Base url is" + baseurl);
		cmnDriver.navigateToUrl(baseurl);
		
		driver = cmnDriver.getDriver();
		
	}
	
	private void initializePages() {

		homepage = new AmazonHomepage(driver);
		
	}
	
}
