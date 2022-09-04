package net.j2store.genericUtiltiy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class WebDriverUtility {

	private WebDriverWait wait;
	private WebDriver driver;
	private Select select;
	private JavascriptExecutor js;
	/**
	 * This method is used to initialize and launch the browser
	 * @param browser
	 * @return 
	 * 
	 */
	public  WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			Reporter.log("Successfully Chrome Browser Launced", true);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			Reporter.log("Successfully Firefox Browser Launced", true);
			break;
            default:
			Reporter.log("please specify proper browser key", true);
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			Reporter.log("Successfully Chrome Browser Launced by default", true);
			break;
		}
		return driver;
	}

	/**
	 * This method is used to navigate to the application
	 * @param url
	 */
	public  void navigateApp(String url) {
		driver.get(url);
		Reporter.log("Successfully Application opened", true);
	}

	/**
	 * This method is used to return/fetch the title of the webpage
	 * @param driver
	 * @return
	 */
	public  String getTitle() {
		String title = driver.getTitle();
		Reporter.log("Successfully fetched the title of the webpage", true);
		return title;
	}

	/**
	 *  This method is used to return/fetch the url of the webpage
	 * @return
	 */
	public  String getURL() {
	String url = driver.getCurrentUrl();
	Reporter.log("Successfully fetched the url of the webpage", true);
	return url;
	}

	/**
	 * This method is used to maximize the browser
	 * 
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
		Reporter.log("Successfully Browser Maximized", true);
	}

	/**
	 * This method is used to implicitly wait till page Load
	 * @param longTimeOut
	 */
	public  void waitTillPageLoad(long longTimeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeOut));
		Reporter.log("Successfully Implicitly wait intiallized", true);
	}

	

	/**
	 * This method is used to mouse hover on the element
	 * @param moreLink
	 */
	public  void mouseHoverOntheElement(WebElement element, String elementName) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		Reporter.log("Successfully mouse hovered on the "+elementName, true);
	}

	
	/**
	 * This method is used to close the browser instance
	 * @param driver
	 */
	public void quitBrowser() {
		driver.quit();
		Reporter.log("Successfully Browser closed", true);
	}


	/**
	 * This method is used to wait the control till the particular element is visible
	 * @param driver
	 * @param timeOut
	 * @param element
	 */
	public  void waitUntillElementVisible( WebElement element, String elementName) {
		wait.until(ExpectedConditions.visibilityOf(element));
		Reporter.log("Successfully controlled waited till the "+elementName+" element visible", true);
	}

	/**
	 * This method is used to intiallize wait instance
	 * @param driver
	 * @param timeOut
	 */
	public  void intiallizeExplicitlyWait(long timeOut) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		Reporter.log("Successfully Intiallized the explicit wait/webdriver wait", true);
	}

	

	/**
	 * This method is used to handle dropdown by visible text
	 * @param visibleText
	 * @param element
	 */
	public  void selectDropDown(String visibleText, WebElement element) {
		select=new Select(element);
		select.selectByVisibleText(visibleText);
		Reporter.log("Successfully selected "+visibleText+" from the dropdown", true);
	}

	
	/**
	 * This method is used to initialize the javascriptexecutor interface variable
	 * @param driver
	 */
	public void intiallizeJs() {
		js=(JavascriptExecutor) driver;
		Reporter.log("Successfully Intiallized the Javascript executor", true);
	}

	
	/**
	 * This method is used to scroll till the top of the page
	 */
	public void scroolToTop() {
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		Reporter.log("Successfully Webpage scrolled to top of the webpage", true);
	}

	/**
	 * This method is used to scroll till the Bottom of the page
	 */
	public void scroolToBottom() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Reporter.log("Successfully Webpage scrolled to bottom of the webpage", true);
	}

	/**
	 * This method is used to scroll till the element is alligned to top of the webpage
	 * @param element
	 */
	public void scrollTillElement(WebElement element, String elementName) {
		js.executeScript("arguments[0].scrollIntoView()",element);
		Reporter.log("Successfully Webpage scrolled till the "+elementName+" element aligned top of the webpage", true);
	}


	/**
	 * This method is used to take the screen shot
	 * @param fileName
	 * @param driver
	 * @return 
	 * @throws IOException
	 */
	public String takeScreenShot(String fileName, JavaUtility jutil) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst=new File("./screenshot/"+fileName+"_"+jutil.getDateTimeInFormat()+".png");
		FileUtils.copyFile(src, dst);
		Reporter.log("Successfully taken the screenshot of the webpage at failed statement", true);
		return dst.getAbsolutePath();
	}


}
