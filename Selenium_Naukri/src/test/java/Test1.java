import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// Disable bookmarks bar using Chrome options
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-bookmarks-bar");
        
	    //Creating driver instance
	    ChromeDriver driver= new ChromeDriver(options);
        
		//Implicit wait wait which applicable for all web elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//Maximize window size
		driver.manage().window().maximize();
		//driver.manage().window().minimize();
		
		//Launch Naukri web portal
		driver.get("https://www.naukri.com/nlogin/login");
		
		System.out.println(driver.getTitle());
		
		WebElement username=driver.findElement(By.xpath("//input[@id=\"usernameField\"]"));
		//Enter user name
		username.sendKeys("pavansriramjakkampudi@gmail.com");
		WebElement password=driver.findElement(By.xpath("//input[@id=\"passwordField\"]"));
		//Enter Password
		password.sendKeys("NAUKRIpowerp@1");
		//Select login key to login
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
		
		//Explicit Wait
		WebDriverWait myWait=new WebDriverWait(driver,Duration.ofSeconds(10));
		myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name-wrapper']//div[1]")));
		
		System.out.println("logged in as "+driver.findElement(By.xpath("//div[@class='name-wrapper']//div[1]")).getText());
		if((driver.findElement(By.xpath("//div[@class='name-wrapper']//div[1]")).getText()).equals("Pavan Sri Ram Jakkampudi")) {
			System.out.println("login successfull");
			System.out.println("Profile updation date: "+driver.findElement(By.xpath("//div[@class='other-info-wrapper']//p")).getText());
		}
		else {
			System.out.println("login failed");
		}
		//View profile
		driver.findElement(By.xpath("//a[text()=\"View\"]")).click();
		
		//Select update resume	
		//driver.findElement(By.xpath("//input[@value=\"Update resume\"]")).sendKeys("E:\\wipro\\servlets\\Selenium_Naukri\\src\\test\\resources\\Pavan's Resume.pdf");
		//if tag is input and type is file we will used send key method other wise we will go with RObot class as shown below
		driver.findElement(By.xpath("//input[@value=\"Update resume\"]")).click();
		
		// Get the absolute path of the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Specify the relative path of the file to upload
        String relativeFilePath = "Pavan_Resume.pdf";

        // Combine the current directory path with the relative file path
        String absoluteFilePath = currentDirectory + File.separator + relativeFilePath;

        // Create a StringSelection object with the absolute file path
        StringSelection stringSelection = new StringSelection(absoluteFilePath);

	 // Set the contents of the clipboard to the file path
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        
     // Create a Robot object to simulate keyboard events
        Robot robot = new Robot();
        
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
        
        StringSelection fileName = new StringSelection(relativeFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileName, null);
        
     //   Simulate pressing Enter to confirm the file selection dialog
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
        
        System.out.println(driver.findElement(By.xpath("//div//span[@id=\"attachCVMsgBox\"]//p[2]")).getText());
			
		//Select Burger button for logout option
		driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
		Thread.sleep(5000);
		//Select logout
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		
		Thread.sleep(5000);
		
		driver.quit();
	}

}
