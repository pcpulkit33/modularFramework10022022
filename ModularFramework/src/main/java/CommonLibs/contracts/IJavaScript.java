package CommonLibs.contracts;

public interface IJavaScript {

	public void executeJavaScript(String scriptToExceute) throws Exception;
	
	public void scrollDown(int x,int y) throws Exception; 
	
	public String executeJavaScriptWithReturnValue(String scriptToExecute) throws Exception;
	
}
