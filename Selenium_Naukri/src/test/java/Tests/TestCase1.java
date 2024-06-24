package Tests;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase1 {

	public static String updateResume(WebDriver driver,WebDriverWait myWait) throws InterruptedException, AWTException {
		
		//Select update resume	
		//driver.findElement(By.xpath("//input[@value=\"Update resume\"]")).sendKeys("E:\\wipro\\servlets\\Selenium_Naukri\\src\\test\\resources\\Pavan's Resume.pdf");
		//if tag is input and type is file we will used send key method other wise we will go with RObot class as shown below
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value=\"Update resume\"]"))).click();
//		driver.findElement(By.xpath("//input[@value=\"Update resume\"]")).click();
		
		// Get the absolute path of the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Specify the relative path of the file to upload
        String relativeFilePath ="Pavan_Resume.pdf";
        // Combine the current directory path with the relative file path
        String absoluteFilePath = currentDirectory + File.separator + relativeFilePath;
        // Create a StringSelection object with the absolute file path
        StringSelection stringSelection = new StringSelection(absoluteFilePath);
	 // Set the contents of the clipboard to the file path
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Thread.sleep(10000);
     // Create a Robot object to simulate keyboard events
        Robot robot = new Robot();
        Thread.sleep(10000);
     // Simulate pressing Enter to confirm the file selection dialog
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
     // Pause for a moment to allow the file to be selected
        Thread.sleep(10000);
        
     // Simulate pressing Control+V to paste the file path into the dialog
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(10000);
        
     // Simulate pressing Enter again to confirm the file upload
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        String result=driver.findElement(By.xpath("//div//span[@id=\"attachCVMsgBox\"]//p[2]")).getText();
        
        System.out.println(result);
        
        return result;
		
	}

}
