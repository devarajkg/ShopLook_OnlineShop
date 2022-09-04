package net.j2store.genericUtiltiy;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public final class JavaUtility {
	/**
	 * This method is used to Convert String value to long datatype
	 * @param value
	 * @return
	 */
	public  long stringToLong(String value) {
		long convertedData = Long.parseLong(value);
		Reporter.log("Successfully String Value Converted into Long Value", true);
		return convertedData;
	}

	/**
	 * This method is used to get the random Number
	 * @param limit
	 * @return
	 */
	public  int getRandomNumber(int limit) {
		int ran=new Random().nextInt(limit);
		Reporter.log("Successfully Random Number Generated ---> Random number is : "+ran, true);
		return ran;

	}


	/**
	 * This method is used to wait untill element clickable
	 * @param element
	 * @param polingTime
	 * @param duration
	 * @throws InterruptedException
	 */
	public void customWait(WebElement element, long polingTime, int duration) {
		int count=0;
		while(count<=duration) {
			try {
				element.click();
				break;
			}
			catch(Exception e) {
				try {
					Thread.sleep(polingTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				count++;
			}
		}
	}


	/**
	 * This method used to decode the Base64 encoded data
	 * @param encodedData
	 * @return
	 */
	public String getDecodedPassword(String encodedData) {
		String decodedData = new String(Base64.getDecoder().decode(encodedData.getBytes()));
		Reporter.log("Successfully data decoded", true);
		return decodedData;
	}
	

	/**
	 * This method is used to fetch current date in format
	 * @return
	 */
	public  String getDateTimeInFormat() {
		String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss").format(new Date());
		Reporter.log("Successfully current date formatted in yyyy_MM_dd_HH_mm_sss, Current date in format is : "+date, true);
		return date;
	}

}
