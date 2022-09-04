package net.j2store.genericUtiltiy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import net.j2store.elementRepository.BlogPage;
import net.j2store.elementRepository.BussinessLibrary;
import net.j2store.elementRepository.CheckoutPage;
import net.j2store.elementRepository.HomePage;
/**
 * This class contains all the basic configuration activity
 * @author devaraj reddy
 *
 */
public class BaseClass {
	
	public WebDriver driver;
	public HomePage homePage;
	public ExcelUtility excelUtility;
	public JavaUtility javaUtility;
	public WebDriverUtility webDriverUtility;
	public int randomNumber;
	public String browser;
	public String url;
	public long longTimeOut;
	public BlogPage blogPage;
	public 	CheckoutPage checkoutPage;
	public BussinessLibrary bussinessLibrary;


	/**
	 * In this annotation 
	 * create the instance for all generic utility
	 * launch browser
	 * do the browser setting (maximize, implicit wait, action class initialization, explicit wait instance initialization)
	 * create the instance for common object repository class
	 * @throws IOException 
	 */

	@BeforeClass(groups = "baseclass", alwaysRun = true)
	public void setup() throws IOException {
		excelUtility=new ExcelUtility();
		javaUtility=new JavaUtility();
		webDriverUtility=new WebDriverUtility();
		Reporter.log("Generic utility Intiallization sucessfully", true);

		excelUtility.openExcel(IconstantPath.EXCEL_PATH);
		randomNumber = javaUtility.getRandomNumber(1000);
		//fetch the data from command line
		browser="chrome";
		url=System.getProperty("url");
		
		driver=webDriverUtility.launchBrowser(browser);

		//navigate to the application
		webDriverUtility.navigateApp(url);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.waitTillPageLoad(10);
		webDriverUtility.intiallizeExplicitlyWait(10);
		webDriverUtility.intiallizeJs();

		//create the instance for common object repository class
		homePage = new HomePage(driver);
		blogPage = new BlogPage(driver);
		checkoutPage = new CheckoutPage(driver);
		bussinessLibrary=new BussinessLibrary(webDriverUtility, homePage, 
				blogPage, checkoutPage, randomNumber);
	}


	/**
	 * In this annotation 
	 * we will close the browser instance
	 * close the excel workbook
	 */
	@AfterClass(groups = "baseclass", alwaysRun = true)
	public void tearDown() {
		//close the browser
		webDriverUtility.quitBrowser();
		//close the excel workbook
		excelUtility.closeExcel();
	}
}
