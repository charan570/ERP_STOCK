package Utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	public static void TakesScreen(WebDriver driver, String sname) throws Throwable
{
		// here we are locating the screen shot path and format of the image
	String path = "D:\\Charan\\ERP_STOCK\\screens\\" +sname+ ".png";
	// here we are taking the screen shot
	File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	// here we are copying the file into the path
	FileUtils.copyFile(screen, new File(path));
}
}
