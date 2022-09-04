package net.j2store.elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class HomePage {
	WebDriver driver;
	@FindBy(xpath = "//a[@itemprop='url' and text()='Shop ']")
	private WebElement shopTab;
	
	private String itemsLink="//a[@itemprop='url' and text()='%s ']";
	
	@FindBy(xpath = "//a[@itemprop='url' and text()='Blog ']")
	private WebElement blogTab;
	
	@FindBy(xpath = "//div[@class='j2store-cart-info-link']")
	private WebElement cartlink;
	
	@FindBy(xpath = "//a[@class='btn btn-success btn-large']")
	private WebElement checkOutLink;
	
	@FindBy(xpath = "//div[@class='banner-content']/h1")
	private WebElement bannerContentText;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Reporter.log("Successfully Instance created for HomePage Object repository", true);
	}
	

	public WebElement getBannerContentText() {
		return bannerContentText;
	}


	public void setBannerContentText(WebElement bannerContentText) {
		this.bannerContentText = bannerContentText;
	}


	public WebElement getShopTab() {
		return shopTab;
	}

	public WebElement getItemsLink(String dynamicData) {
		return driver.findElement(By.xpath(String.format(itemsLink, dynamicData)));
	}

	public WebElement getBlogTab() {
		return blogTab;
	}

	public WebElement getCartlink() {
		return cartlink;
	}

	public WebElement getCheckOutLink() {
		return checkOutLink;
	}
	
	 
	

}
