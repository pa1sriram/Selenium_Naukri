import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ApplyJob {
//	ChromeOptions options;
	ChromeDriver driver;
  @Test(priority=1)
  public void login() {
	  driver= new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.manage().window().maximize();
	  driver.get("https://www.naukri.com/nlogin/login");
	  Utility.screenShot(driver,"launch");
  }
  
  @Test(priority=10)
  public void logout(){
	//Select Burger button for logout option
	driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
	//Select logout
	driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
			
	System.out.println(driver.getTitle());
			
	driver.quit();
  }
  
}
