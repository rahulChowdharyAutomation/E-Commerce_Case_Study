package qa.reportingClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReports {

	public static ExtentReports extentReport() {
		String reportFile = System.getProperty("user.dir") + "/Reporting/report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportFile);
		reporter.config().setReportName("Automation Reports");
		reporter.config().setDocumentTitle("QA Case Study Reports");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}

}
