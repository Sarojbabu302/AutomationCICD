package rahulsettyacademy.resources;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	WebDriver driver;
	static ExtentReports extent;
	
	public static ExtentReports GetReportObject()
	{

		// ExtentSparkReporter & ExtentReport
		String path= System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Saroj Kumar Behera");
		return extent;
	}

}
