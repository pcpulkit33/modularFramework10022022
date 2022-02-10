package CommonLibs.contracts;

import java.util.Set;

public interface IWindow {
	
	public void switchToChildWindow(String windowHandle) throws Exception;
	
	public void switchToChildWindow(int childWindowIndex) throws Exception;
	
	public String getWindowHandle() throws Exception; 
		
	public Set<String> getWindowHandles() throws Exception;  
	
}
