package Rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver InitializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\rahulsettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browseraName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		/*if(browseraName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
		//String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		if(browseraName.contains("headless")) {
			options.addArguments("headless");
		}		
		driver=new ChromeDriver(options);
		//OK-- driver.manage().window().setSize(new Dimension(1440,900)); // Hepl's to run in full screen*/
		
		if(browseraName.equalsIgnoreCase("chrome"))
		{
		//String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();		
		driver=new ChromeDriver();
		
		//LandingPage landingpage=new LandingPage(driver);	
		}
		else if(browseraName.equalsIgnoreCase("firefox"))
		{
			System.getProperty("webdriver.gecko.driver","/user/saroj//documents//geckodriver");
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> GetJsonDataToMap(String filePath) throws IOException
	{
		// read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8 );
		// string to HashMap converting
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
		{});
		return data;
	}

	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LunchApplication() throws IOException	
	{
		driver=InitializeDriver();
		landingpage=new LandingPage(driver);
		landingpage.GoTo();
		return landingpage;
	}
	
	public String GetScreenShot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		//File DestFile=new File(fileWithPath);
		//FileUtils.copyFile(source, DestFile);
		File file=new File(System.getProperty("user.dir") + "//reports" + testcaseName + ".png");
		return System.getProperty("user.dir") + "//reports" + testcaseName + ".png";
		//return fileWithPath;
	}
	
	@AfterMethod(alwaysRun=true)
	public void TearDown()
	{
		driver.close();
	}

}
