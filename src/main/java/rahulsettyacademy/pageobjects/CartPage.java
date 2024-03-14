package rahulsettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rahulsettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	@FindBy(xpath="//div[@class='cartSection']/h3")
	private List<WebElement>cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean VerifyProductDispaly(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage GoToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	

}
