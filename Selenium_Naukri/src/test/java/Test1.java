import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
	
	public static void main(String[] args) throws InterruptedException, AWTException{
        
		ChromeOptions options= new ChromeOptions();
		
//		options.addArguments("--headless=new");
		options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
		options.addArguments("--test-type");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-first-run");
		options.addArguments("--no-default-browser-check");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--start-maximized");
	    //Creating driver instance
		ChromeDriver driver= new ChromeDriver(options);
        
		//Implicit wait wait which applicable for all web elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		//Maximize window size
		driver.manage().window().maximize();
		//driver.manage().window().minimize();

		//Launch Naukri web portal
		driver.get("https://www.naukri.com/nlogin/login");
		Utility.screenShot(driver,"launch");

		
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
		
		TestCase1.updateResume(driver,myWait);
		TestCase2.updateSummery(driver,myWait);
		TestCase3.updateKeySkills(driver,myWait);
		
		Actions action= new Actions(driver);
		
		WebElement job=driver.findElement(By.xpath("//a[@title=\"Recommended Jobs\"]//div"));
		
		WebElement recommendedJobs=driver.findElement(By.xpath("//a[@href='/mnjuser/recommendedjobs']//div[text()='Recommended jobs']"));
		
		action.moveToElement(job).moveToElement(recommendedJobs).click().perform();
		
		//Select Burger button for logout option
		driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
		//Select logout
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		
		System.out.println(driver.getTitle());
		
		driver.quit();
	}

}
