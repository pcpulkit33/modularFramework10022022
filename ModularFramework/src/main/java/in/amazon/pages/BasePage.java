package in.amazon.pages;

import org.openqa.selenium.WebDriver;

import CommonLibs.implementation.CommonElement;
import CommonLibs.implementation.DropDownControl;
import CommonLibs.implementation.MouseControl;

public class BasePage {
	
	 CommonElement elementControl;
	 DropDownControl dropDownControl;
	 MouseControl mouseControl;
	
	public BasePage(WebDriver driver) {
	
		elementControl = new CommonElement();
		dropDownControl = new DropDownControl();
		mouseControl = new MouseControl(driver);
	}

}
