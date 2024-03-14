package rahulsettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Rahulshettyacademy.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsettyacademy.AbstractComponents.AbstractComponents;
import rahulsettyacademy.pageobjects.CartPage;
import rahulsettyacademy.pageobjects.CheckoutPage;
import rahulsettyacademy.pageobjects.ConfirmationPage;
import rahulsettyacademy.pageobjects.OrderPage;
import rahulsettyacademy.pageobjects.ProductCatalogue;

public class StandAloneOrderTest extends BaseTest {
	
	String productName="ZARA COAT 3";	
	@Test(dataProvider="GetData",groups= {"Purchase"},retryAnalyzer=Rahulshettyacademy.TestComponents.Retry.class)
	//@AfterTest(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void SubmitOrder(HashMap<String,String> input) throws IOException {	
		ProductCatalogue productCatalogue =landingpage.LoginApplication(input.get("email"),input.get("password"));
		List<WebElement>products=productCatalogue.GetProductList();		
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalogue.GotoCartPage();		
		// Cart Pages
		Boolean match= cartPage.VerifyProductDispaly(input.get("product"));
		Assert.assertTrue(match);		
		// Checkout
		CheckoutPage checkoutpage=cartPage.GoToCheckout();		
		checkoutpage.SelectCountry("india");
		checkoutpage.SubmitOrder();
		ConfirmationPage confirmationPage=checkoutpage.SubmitOrder();		
		// Final Checkout			
		String confirmMessage=confirmationPage.GetConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
	}
	@Test(dependsOnMethods={"SubmitOrder"})
	public void OrderHistoryTest()
	{
		//ZARA COAT 3
		ProductCatalogue productCatalogue =landingpage.LoginApplication("punamm@gmail.com","Punam@1234");
		OrderPage orderPage= productCatalogue.GotoOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDispaly(productName));
	}
	@DataProvider
	public Object[][] GetData() throws IOException
	{
		/*HashMap<String,String>map=new HashMap<String,String>();
		map.put("email", "punamm@gmail.com");
		map.put("password", "Punam@1234");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String>map1=new HashMap<String,String>();
		map.put("email", "sarojkumar@gmail.com");
		map.put("password", "Punam@1234");
		map.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String,String>>data=GetJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\rahulsettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(1)},{data.get(1)}};
	}
	
	/*public String GetScreenShot(String testcaseName)
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir") + "//reports" + testcaseName + ".png");
		return System.getProperty("user.dir") + "//reports" + testcaseName + ".png";
	}*/   // Moved to BaseTest.java

}
