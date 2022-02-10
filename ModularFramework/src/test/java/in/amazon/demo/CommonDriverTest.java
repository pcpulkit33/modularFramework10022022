package in.amazon.demo;

import org.testng.annotations.Test;

import CommonLibs.implementation.CommonDriver;

public class CommonDriverTest {

	CommonDriver cmndriver;
	
	@Test	
	public void verifyCommonDriver() throws Exception {
		
		cmndriver = new CommonDriver("chrome");
		
		cmndriver.setPageloadTimeout(90);
		cmndriver.setElementDetectionTimeout(20);
		cmndriver.navigateToUrl("https://amazon.in");
		
		String title = cmndriver.getTitle();
		System.out.println(title);
		
		cmndriver.closeBrowser();
	}
	
}
