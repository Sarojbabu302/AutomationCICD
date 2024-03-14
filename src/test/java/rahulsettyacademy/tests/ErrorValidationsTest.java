package rahulsettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Rahulshettyacademy.TestComponents.BaseTest;
import Rahulshettyacademy.TestComponents.Retry;
import rahulsettyacademy.pageobjects.CartPage;
import rahulsettyacademy.pageobjects.CheckoutPage;
import rahulsettyacademy.pageobjects.ConfirmationPage;
import rahulsettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
		
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {
		String productName="ZARA COAT 3";
		landingpage.LoginApplication("punam@gmail.com","Punam1234");
		Assert.assertEquals("Incorrect email or password.",landingpage.GetErrorMessage());		
	}
	@Test
	public void ProductErrorValidation() throws IOException {
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue =landingpage.LoginApplication("punamm@gmail.com","Punam@1234");
		List<WebElement>products=productCatalogue.GetProductList();		
		productCatalogue.addProductToCart(productName);		
		CartPage cartPage= productCatalogue.GotoCartPage();		
		Boolean match= cartPage.VerifyProductDispaly("ZARA COAT 33");
		Assert.assertFalse(match);		
	}
	
	

}
