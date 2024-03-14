package Rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulsettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.GetReportObject();
	ThreadLocal<ExtentTest> extentThread=new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test= extent.createTest(result.getMethod().getMethodName());
		extentThread.set(test); // unique thread id(ErrorValidationTest)-->test
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentThread.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentThread.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// Take Screenshot  // Attachet it to the report
		String filePath=null;		
		try
		{
		filePath= GetScreenShot(result.getMethod().getMethodName(),driver);
		}
		catch(IOException e)
		{
			e.printStackTrace();		
		}
		extentThread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}
