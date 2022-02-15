package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonLibs.implementation.CommonElement;
import CommonLibs.implementation.DropDownControl;

public class AmazonHomepagePOM {
	
	private WebElement searchBox;
	
	private WebElement searchButton;
	
	private WebElement searchCategory;
	
	private CommonElement elementControl;
	private DropDownControl dropDownControl;
	
	public AmazonHomepagePOM(WebDriver driver) {
		
		searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		
		searchButton = driver.findElement(By.id("nav-search-submit-button"));
		
		searchCategory = driver.findElement(By.id("searchDropdownBox"));
		
		elementControl = new CommonElement();
		dropDownControl = new DropDownControl();
		
	}

	public void searchProduct (String product, String category) throws Exception {
		
		elementControl.setText(searchBox, product);
		
		dropDownControl.selectViaVisibleText(searchCategory, category);
		
		elementControl.clickElement(searchButton);
	}
	
}
