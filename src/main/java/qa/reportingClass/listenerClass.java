package qa.reportingClass;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import qa.baseClass.baseTest;

public class listenerClass implements ITestListener {

	ExtentReports extent = extentReports.extentReport();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());

		// Directly get driver (ThreadLocal)
		WebDriver driver = baseTest.getDriver();

		String filePath = null;

		try {
			if (driver != null) {
				filePath = new baseTest().takeScreenShot(result.getMethod().getMethodName());

				System.out.println("Screenshot captured: " + filePath);
			} else {
				System.out.println("Driver is NULL");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Attach screenshot
		if (filePath != null) {
			test.addScreenCaptureFromPath(filePath);
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
