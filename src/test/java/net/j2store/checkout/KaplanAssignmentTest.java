package net.j2store.checkout;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import net.j2store.genericUtiltiy.BaseClass;
import net.j2store.genericUtiltiy.VerificationStrategy;

public class KaplanAssignmentTest extends BaseClass{

	@Test
	public  void assignmentTest() {
		String itemName=excelUtility.getDataFromExcel("Shop", "ItemName");
		String blogName=excelUtility.getDataFromExcel("Blog", "PostName");
		String productName=excelUtility.getDataFromExcel("Blog", "ProductName");
		List<Map<String, String>> customerDetails = excelUtility.getMapDataFromExcel("NewCustomer");

		bussinessLibrary.verifyWebPage(VerificationStrategy.EXACT_TITLE, "Home")
		.clickItemByShopCategory(itemName)
		.openBlog().verifyWebPage(VerificationStrategy.EXACT_TITLE, "Blog")
		.selectRequiredBlog(blogName)
		.addRecentProductIntoCartFromBlogPost(productName)
		.openCheckOutPage(productName)
		.checkOutAsNewCustomer(customerDetails).verifyWebPage(VerificationStrategy.PARTIAL_TEXT, checkoutPage.getCartProductNameText(), productName)
		.confirmOrder();
	}
}
