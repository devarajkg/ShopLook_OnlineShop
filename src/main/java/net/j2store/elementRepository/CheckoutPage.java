package net.j2store.elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public final class CheckoutPage {
private WebDriver driver;

	@FindBy(xpath = "//button[@id='button-account']")
	private WebElement accountBtn;
	
	@FindBy(xpath="//div[@class='j2storeOrderSummary']/h3")
	private WebElement orderSummaryText;

	@FindBy(xpath = "//input[@id='first_name']")
	private WebElement firstNameText;
	
	@FindBy(xpath = "//input[@id='last_name']")
	private WebElement lastNameText;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailText;

	@FindBy(xpath = "//input[@id='phone_1']")
	private WebElement phoneNumberText;

	@FindBy(xpath = "//input[@id='phone_2']")
	private WebElement mobileNumberText;

	@FindBy(xpath = "//input[@id='company']")
	private WebElement companyNameText;

	@FindBy(xpath = "//input[@id='tax_number']")
	private WebElement taxNumberText;

	@FindBy(xpath = "//input[@id='address_1']")
	private WebElement addressLineText;

	@FindBy(xpath = "//input[@id='address_2']")
	private WebElement addressLineText1;

	@FindBy(xpath = "//input[@id='city']")
	private WebElement cityNameText;

	@FindBy(xpath = "//input[@id='zip']")
	private WebElement zipCodeText;

	@FindBy(xpath = "//select[@id='country_id']")
	private WebElement countryLink;

	@FindBy(xpath = "//select[@id='zone_id']")
	private WebElement regionLink;

	@FindBy(xpath = "//div[@class='col-md-6']//input[@name='password']")
	private WebElement passwordNameText;

	@FindBy(xpath = "//input[@name='confirm']")
	private WebElement confirmPasswordNameText;

	@FindBy(xpath = "//input[@id='button-register']")
	private WebElement registerButton;
	
	
	private String  paymentModeRadioButtonLink="//label[contains(.,'%s')]/input";
	
	@FindBy(xpath = "//input[@id='button-payment-method']")
	private WebElement paymentMethodLink;
	
	@FindBy(xpath = "//input[@value='Place order']")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//span[@class='cart-product-name']")
	private WebElement cartProductNameText;

	@FindBy(xpath = "//p[@class='j2store-payment-display-name']/strong")
	private WebElement paymentModeText;
	
	@FindBy(xpath = "//div[@class='checkout-heading' and text()='Confirm order']")
	private WebElement confirmOrderHeadingText;
	

	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Reporter.log("Successfully Instance created for Checkout page Object repository", true);
	}
	
	public WebElement getPaymentModeRadioButtonLink(String dynamicData) {
		return driver.findElement(By.xpath(String.format(paymentModeRadioButtonLink, dynamicData)));
	}
	public WebElement getPaymentModeText() {
		return paymentModeText;
	}

	public WebElement getConfirmOrderHeadingText() {
		return confirmOrderHeadingText;
	}


	public WebElement getCartProductNameText() {
		return cartProductNameText;
	}


	public WebElement getAccountBtn() {
		return accountBtn;
	}


	public WebElement getFirstNameText() {
		return firstNameText;
	}

	public WebElement getOrderSummaryText() {
		return orderSummaryText;
	}

	public WebElement getLastNameText() {
		return lastNameText;
	}


	public WebElement getEmailText() {
		return emailText;
	}


	public WebElement getPhoneNumberText() {
		return phoneNumberText;
	}


	public WebElement getMobileNumberText() {
		return mobileNumberText;
	}


	public WebElement getCompanyNameText() {
		return companyNameText;
	}


	public WebElement getTaxNumberText() {
		return taxNumberText;
	}


	public WebElement getAddressLineText() {
		return addressLineText;
	}


	public WebElement getAddressLineText1() {
		return addressLineText1;
	}


	public WebElement getCityNameText() {
		return cityNameText;
	}


	public WebElement getZipCodeText() {
		return zipCodeText;
	}


	public WebElement getCountryLink() {
		return countryLink;
	}


	public WebElement getRegionLink() {
		return regionLink;
	}


	public WebElement getPasswordNameText() {
		return passwordNameText;
	}


	public WebElement getConfirmPasswordNameText() {
		return confirmPasswordNameText;
	}


	public WebElement getRegisterButton() {
		return registerButton;
	}




	public WebElement getPaymentMethodLink() {
		return paymentMethodLink;
	}


	public WebElement getSubmitBtn() {
		return submitBtn;
	}

}
