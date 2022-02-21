package CommonLibs.implementation;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import CommonLibs.contracts.IScreenshot;

public class ScreenshotControl implements IScreenshot {

	TakesScreenshot camera;

	public ScreenshotControl(WebDriver driver) {

		camera = (TakesScreenshot) driver;
	}

	@Override
	public void captureAndSaveScreenshot(String screenshotFilename) throws Exception {

		screenshotFilename = screenshotFilename.trim();  // avoiding the white spaces

		File imgFile, tempFile;  // Creating files  

		imgFile = new File(screenshotFilename); // initializing files

		if (imgFile.exists()) {
			throw new Exception("File already exists.. use some other name");  //checking the duplicacy of file name. 
		}

		tempFile = camera.getScreenshotAs(OutputType.FILE); // method which takes screenshot and have a default extension.

		FileUtils.moveFile(tempFile, imgFile); //move temporary file to imgFile.
	}

}
