package CommonLibs.implementation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import CommonLibs.contracts.IJavaScript;

public class JavaScriptControl implements IJavaScript {

	JavascriptExecutor jsEngine;
	
	public JavaScriptControl(WebDriver driver) {
		
		jsEngine = (JavascriptExecutor) driver;
	}
	
	@Override
	public void executeJavaScript(String scriptToExecute) throws Exception {
		
		jsEngine.executeScript(scriptToExecute);
	}

	@Override
	public void scrollDown(int x, int y) throws Exception {
		
		String jsCommand = String.format("window.scrollBy(%d,%d)", x,y);
		executeJavaScript(jsCommand);
	}

	@Override
	public String executeJavaScriptWithReturnValue(String scriptToExecute) throws Exception {
		
		return jsEngine.executeScript(scriptToExecute).toString();
	}

}
