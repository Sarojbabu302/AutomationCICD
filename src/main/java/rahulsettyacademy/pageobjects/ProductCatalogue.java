package rahulsettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css=".mb-3")
	List<WebElement>products;
	By productsBY=By.cssSelector(".mb-3");
	public List<WebElement> GetProductList()
	{
		WaitForElementToAppear(productsBY);
		return products;
	}
	public WebElement GetProductName(String productName)
	{
		WebElement prod= GetProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName))		 
		.findFirst().orElse(null);
		return prod;
	}
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating")
	WebElement spinner;
	public void addProductToCart(String productName)
	{
		WebElement prod=GetProductName(productName);
		prod.findElement(addToCart).click();
		WaitForElementToAppear(toastMessage);
		WaitForElementToDisappear(spinner);
	}
}
