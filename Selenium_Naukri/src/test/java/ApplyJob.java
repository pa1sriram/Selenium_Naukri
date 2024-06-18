import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApplyJob {
//	ChromeOptions options;
	ChromeDriver driver= new ChromeDriver();
	WebDriverWait myWait= new WebDriverWait(driver,Duration.ofSeconds(10));
  @BeforeClass
  public void login() {
//	  driver= new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.manage().window().maximize();
	  driver.get("https://www.naukri.com/nlogin/login");
	  Utility.screenShot(driver,"launch");
	  Utility.screenShot(driver,"launch");
		WebElement username=driver.findElement(By.xpath("//input[@id=\"usernameField\"]"));
		//Enter user name
		username.sendKeys("pavansriramjakkampudi@gmail.com");
		WebElement password=driver.findElement(By.xpath("//input[@id=\"passwordField\"]"));
		//Enter Password
		password.sendKeys("NAUKRIpowerp@1");
		//Select login key to login
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"View\"]")).click();
  }
  
  @Test(priority=1)
  public void udateResume() throws InterruptedException, AWTException{
	  TestCase1.updateResume(driver,myWait);
  }
  
  @AfterClass
  public void logout(){
	//Select Burger button for logout option
	driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
	//Select logout
	driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
			
	System.out.println(driver.getTitle());
			
	driver.quit();
  }
  
}
