package rahulsettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rahulsettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	@FindBy(xpath="//div[@class='cartSection']/h3")
	private List<WebElement>cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	public List<WebElement> productNames;
	

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public Boolean VerifyOrderDispaly(String productName)
	{
		Boolean match=productNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
}
