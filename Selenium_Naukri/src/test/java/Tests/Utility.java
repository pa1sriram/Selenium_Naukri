package Tests;
import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Utility {
	
	public static File screenShot(TakesScreenshot ts,String name) {
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		File TargetFile=new File(System.getProperty("user.dir")+"\\ScreenShots\\"+name+".png");
		sourceFile.renameTo(TargetFile);
		return TargetFile;
	}

}
