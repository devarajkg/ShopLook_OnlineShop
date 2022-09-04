package net.j2store.elementRepository;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import net.j2store.genericUtiltiy.JavaUtility;
import net.j2store.genericUtiltiy.VerificationStrategy;
import net.j2store.genericUtiltiy.WebDriverUtility;

public final class BussinessLibrary {

	private WebDriverUtility webDriverUtility;
	private HomePage homePage;
	private BlogPage blogPage;
	private CheckoutPage checkoutPage;
	private int randomNumber;
	private JavaUtility javaUtility=new JavaUtility();
	
	/**
	 * It is a constructor, by using this we will intiallize all the non static variable
	 * @param webDriverUtility
	 * @param homePage
	 * @param blogPage
	 * @param checkoutPage
	 * @param randomNumber
	 */
	public BussinessLibrary(WebDriverUtility webDriverUtility,HomePage homePage, 
			BlogPage blogPage,CheckoutPage checkoutPage, int randomNumber) 
	{
		this.blogPage=blogPage;
		this.homePage=homePage;
		this.checkoutPage=checkoutPage;
		this.webDriverUtility=webDriverUtility;
		this.randomNumber=randomNumber;
		Reporter.log("Successfully Instance created for Bussiness Library class", true);
	}

	/**
	 * This method is used to mousehover on the shopTab and click on specified item
	 * @return
	 */
	public BussinessLibrary clickItemByShopCategory(String itemName) {
		webDriverUtility.mouseHoverOntheElement(homePage.getShopTab(), "Shop tab");	
		homePage.getItemsLink(itemName).click();
		Reporter.log("Successfully clicked on the "+itemName+" element", true);
		return this;
	}

	/**
	 * This method is used to open Blog
	 * @return
	 */
	public BussinessLibrary openBlog() {
		homePage.getBlogTab().click();
		Reporter.log("Successfully Clicked on blog Tab", true);
		return this;
	}

	/**
	 * This method is used to select the specified Blog Post
	 * @param blogName
	 * @return
	 */
	public BussinessLibrary selectRequiredBlog(String blogName) {
		blogPage.getBlogPostLink(blogName).click();
		Reporter.log("Successfully navigated "+blogName+" blog post page", true);
		return this;
	}

	/**
	 * This method is used to add the product into cart from the recent product list from the blog post
	 * @param productName
	 * @return
	 */
	public BussinessLibrary addRecentProductIntoCartFromBlogPost(String productName) {
		webDriverUtility.scrollTillElement(blogPage.getRecentProductsText(), "Recent Product Text");
		blogPage.getAddCartLink(productName).click();
		Reporter.log("Successfully added the "+productName+" product into the cart", true);
		webDriverUtility.scroolToTop();
		return this;
	}

	/**
	 * This method is used to mouse hover on the checkout tab and click on the checkout button
	 * @param productName
	 * @return
	 */
	public BussinessLibrary openCheckOutPage(String productName) {
		webDriverUtility.mouseHoverOntheElement(homePage.getCartlink(), "Cart tab");
		homePage.getCheckOutLink().click();
		Reporter.log("Successfully navigated the checkout page for the "+productName+" product", true);
		return this;
	}

/**
 * This method is used to check out as new Customer by entering all specified data in excel and select payment method as bank transfer
 * @param customerDetails
 * @return
 */
	public BussinessLibrary checkOutAsNewCustomer(List<Map<String, String>> customerDetails) {
		String decodedPassword=javaUtility.getDecodedPassword(customerDetails.get(0).get("Password"));
		checkoutPage.getAccountBtn().click();
		Reporter.log("Successfully clicked on the continue as new customer button", true);
		checkoutPage.getFirstNameText().sendKeys(customerDetails.get(0).get("FirstName"));
		checkoutPage.getLastNameText().sendKeys(customerDetails.get(0).get("LastName"));
		checkoutPage.getEmailText().sendKeys(customerDetails.get(0).get("Email")+randomNumber+"@gmail.com");
		checkoutPage.getPhoneNumberText().sendKeys(customerDetails.get(0).get("PhoneNumber"));
		checkoutPage.getMobileNumberText().sendKeys(customerDetails.get(0).get("MobileNumber"));
		checkoutPage.getCompanyNameText().sendKeys(customerDetails.get(0).get("CompanyName"));
		checkoutPage.getTaxNumberText().sendKeys(customerDetails.get(0).get("TaxNumber"));
		checkoutPage.getAddressLineText().sendKeys(customerDetails.get(0).get("Address1"));
		checkoutPage.getAddressLineText1().sendKeys(customerDetails.get(0).get("Address2"));
		checkoutPage.getCityNameText().sendKeys(customerDetails.get(0).get("CityName"));
		checkoutPage.getZipCodeText().sendKeys(customerDetails.get(0).get("ZipCode"));
		webDriverUtility.selectDropDown(customerDetails.get(0).get("CountryName"), checkoutPage.getCountryLink());
		webDriverUtility.selectDropDown(customerDetails.get(0).get("RegionName"), checkoutPage.getRegionLink());
		checkoutPage.getPasswordNameText().sendKeys(decodedPassword);
		checkoutPage.getConfirmPasswordNameText().sendKeys(decodedPassword);
		Reporter.log("Successfully entered all the Registration details into all the textfields", true);
		checkoutPage.getRegisterButton().click();
		Reporter.log("Successfully clicked on the Continue button for registration", true);
		webDriverUtility.waitUntillElementVisible(checkoutPage.getPaymentMethodLink(), "PaymentMethod Button");
		checkoutPage.getPaymentMethodLink().click();
		Reporter.log("Successfully clicked on the payment method button", true);
		webDriverUtility.waitUntillElementVisible(checkoutPage.getOrderSummaryText(), "Order Summary Text");
		return this;
	}

	/**
	 * This method is used to confirm the order
	 * @return
	 */
	public BussinessLibrary confirmOrder() {
		webDriverUtility.scrollTillElement(checkoutPage.getConfirmOrderHeadingText(), "Confirm Order Header");
		webDriverUtility.waitUntillElementVisible(checkoutPage.getSubmitBtn(), "Place Order Button");
		checkoutPage.getSubmitBtn().click();
		Reporter.log("Successfully click on the confirm the order button", true);
		return this;
	}


	/**
	 * This method is used to verify or assert the expected data based on strategy
	 * @param strategy
	 * @param expectedData
	 * @return
	 */
	public BussinessLibrary verifyWebPage(VerificationStrategy strategy, String expectedData) {
		String[] splitArr = strategy.toString().split("_");
		String urlORTitle=splitArr[1];
		String partialOrExact=splitArr[0];
		if(urlORTitle.equalsIgnoreCase("url") && partialOrExact.equalsIgnoreCase("exact")) {
			Assert.assertTrue(webDriverUtility.getURL().equals(expectedData));
			Reporter.log("Assertion Pass ---> "+expectedData+" match with the current page url", true);
		}
		else if(urlORTitle.equalsIgnoreCase("url") && partialOrExact.equalsIgnoreCase("partial")) {
			Assert.assertTrue(webDriverUtility.getURL().contains(expectedData));
			Reporter.log("Assertion Pass ---> "+expectedData+" match with the current page url", true);
		}
		else if(urlORTitle.equalsIgnoreCase("title") && partialOrExact.equalsIgnoreCase("exact")) {
			Assert.assertTrue(webDriverUtility.getTitle().equals(expectedData));
			Reporter.log("Assertion Pass ---> "+expectedData+" match with the current page title", true);
		}
		else if(urlORTitle.equalsIgnoreCase("title") && partialOrExact.equalsIgnoreCase("partial")) {
			Assert.assertTrue(webDriverUtility.getTitle().contains(expectedData));
			Reporter.log("Assertion Pass ---> "+expectedData+" match with the current page title", true);
		}
		return this;
	}

	/**
	 * This method is used to verify or assert the expected data with element text
	 * @param strategy
	 * @param element
	 * @param expectedData
	 * @return
	 */
	public BussinessLibrary verifyWebPage(VerificationStrategy strategy, WebElement element, String expectedData) {
		String[] splitArr = strategy.toString().split("_");
		String partialOrExact=splitArr[0];

		if(partialOrExact.equalsIgnoreCase("exact")) {
			Assert.assertTrue(element.getText().equals(expectedData));
			Reporter.log("Assertion Pass ---> "+expectedData+" match with the current element text", true);
		}
		else if(partialOrExact.equalsIgnoreCase("partial")) {
			Assert.assertTrue(element.getText().contains(expectedData));
			Reporter.log("Assertion Pass ---> "+expectedData+" match with the current element text", true);
		}

		return this;
	}

}
