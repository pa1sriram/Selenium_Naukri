import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// Disable bookmarks bar using Chrome options
//		ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-bookmarks-bar");
        
	    //Creating driver instance
		ChromeDriver driver= new ChromeDriver();
		
		JavascriptExecutor js=driver;
        
		//Implicit wait wait which applicable for all web elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Maximize window size
		driver.manage().window().maximize();
		//driver.manage().window().minimize();
		
		//Launch Naukri web portal
		driver.get("https://www.naukri.com/nlogin/login");
		
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
		System.out.println("logged in as "+myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='name-wrapper']//div[1]"))).getText());
		
//		System.out.println("logged in as "+driver.findElement(By.xpath("//div[@class='name-wrapper']//div[1]")).getText());
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
        
        System.out.println(driver.findElement(By.xpath("//div//span[@id=\"attachCVMsgBox\"]//p[2]")).getText());
        
        driver.findElement(By.xpath("//div[@class='widgetHead']//span[@class='edit icon'][text()='editOneTheme']")).click();
        
     
        WebElement summery=driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']"));
        String ResumeHead=summery.getText();
        
        if(ResumeHead.contains("way.")) {
        	ResumeHead=ResumeHead.replace("way.","way");
        }
        else if(ResumeHead.contains("way")){
        	ResumeHead=ResumeHead.replace("way","way.");
        }
        else {
        	
        }
        
        myWait.until(ExpectedConditions.visibilityOf(summery)).clear();
        
       // myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='resumeHeadlineTxt']"))).sendKeys(ResumeHead);
        myWait.until(ExpectedConditions.visibilityOf(summery)).sendKeys(ResumeHead);
        driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
        
        System.out.println(driver.findElement(By.xpath("//div[@id=\"lazyResumeHead\"]//div//div//div//div[@class=\"cnt\"]//p[2]")).getText());
		
//        key Skills updation
        driver.findElement(By.xpath("//div[@class='widgetHead typ-16Bold']/span[2]")).click();
        List<WebElement> skills=driver.findElements(By.xpath("//div[@class=\"waves-effect chip\"]/span"));
        
        for(WebElement skill: skills) {
        	if(skill.getText().equals("Java")) {
        		driver.findElement(By.xpath("//div[@title='Java']//a[@class='material-icons close'][normalize-space()='Cross']"));
        		break;
        	}
        }
        
        driver.findElement(By.xpath("//input[@id=\"keySkillSugg\"]")).sendKeys("Java");
        
        List<WebElement> skillList=driver.findElements(By.xpath("//li//div[@class=\"Sbtn\"]"));
        
        for(WebElement skill:skillList) {
        	if(skill.getText().equals("Java")) {
        		skill.click();
        	}
        }
        
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//button[@id=\"saveKeySkills\"]")));
        driver.findElement(By.xpath("//button[@id=\"saveKeySkills\"]")).click();
        
        System.out.println(driver.findElement(By.xpath("//div[@id=\"lazyKeySkills\"]//div[@class=\"cnt\"]/p[2]")).getText());
        
		//Select Burger button for logout option
		driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
		//Select logout
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		
		driver.getTitle();
		
		driver.quit();
	}

}
