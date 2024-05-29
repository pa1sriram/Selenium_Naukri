import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver= new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.naukri.com/nlogin/login");
		
		WebElement username=driver.findElement(By.xpath("//input[@id=\"usernameField\"]"));
		
		username.sendKeys("pavansriramjakkampudi@gmail.com");
		
		WebElement password=driver.findElement(By.xpath("//input[@id=\"passwordField\"]"));
		
		password.sendKeys("NAUKRIpowerp@1");
		
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
		
		driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		
		driver.quit();
	}

}
