package net.j2store.elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;



public final class BlogPage {
	private WebDriver driver;
	
	private String blogPostLink="//a[@title='%s']";
	
	@FindBy(xpath = "//span[text()='Recent products']")
	private WebElement recentProductsText;
	
	private String addCartLink="//a[contains(text(),'%s')]/parent::h4/following-sibling::div[@class='product_cart_block']//input[@value='Add to cart']";
	
	@FindBy(xpath="//p[@class='j2store-product-name']/strong")
	private WebElement productNameINCart;
	
	public BlogPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Reporter.log("Successfully Instance created for BlogPage Object repository", true);
	}

	public WebElement getBlogPostLink(String dynamicData) {
		return driver.findElement(By.xpath(String.format(blogPostLink, dynamicData)));
	}
	
	public WebElement getRecentProductsText() {
		return recentProductsText;
	}

	public WebElement getAddCartLink(String dynamicData) {
		return driver.findElement(By.xpath(String.format(addCartLink, dynamicData)));
	}

	public WebElement getProductNameINCart() {
		return productNameINCart;
	}

	
}
