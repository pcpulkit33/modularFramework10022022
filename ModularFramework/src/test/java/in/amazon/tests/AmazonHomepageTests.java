package in.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class AmazonHomepageTests extends BaseTest {
	
	
	@Test
	public void VerifySearchProduct() throws Exception {
		
		String product = "Apple Watch";
		String category = "Electronics"	;	
		
		extentReportUtils.createTest("TC-001 - Verify search product with product as - " + product + " categoy as - "+ category);
		
		String result = homepage.searchProduct(product, category);
		
		extentReportUtils.addLog(Status.INFO, result);
		
		String expectedResult = "1-16 of 41 results for \"Apple watch\"";   //assertion
		Assert.assertEquals(result, expectedResult);
		
	}
}
