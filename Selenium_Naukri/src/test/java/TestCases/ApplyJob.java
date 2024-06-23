package TestCases;
import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Tests.TestCase1;
import Tests.TestCase2;
import Tests.TestCase3;
import Tests.Utility;

public class ApplyJob {
//	ChromeOptions options;
	WebDriver driver;
	WebDriverWait myWait;
	
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	public void launch(String webDrivers) {
		switch(webDrivers){
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser");
			return;//return statement will stop execution of setup and test
		}
//		driver= new ChromeDriver();
		myWait= new WebDriverWait(driver,Duration.ofSeconds(10000));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/nlogin/login");
		Utility.screenShot((TakesScreenshot)driver,"launch");
	}
  @Test(priority=0,groups={"sanity","functional"},dataProvider="dp")
  	public void login(String email,String Password) {
		WebElement username=driver.findElement(By.xpath("//input[@id=\"usernameField\"]"));
		//Enter user name
		username.sendKeys(email);
		WebElement password=driver.findElement(By.xpath("//input[@id=\"passwordField\"]"));
		//Enter Password
		password.sendKeys(Password);
		//Select login key to login
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"View\"]")).click();
  }
  
  @Test(priority=1,dependsOnMethods= {"login"},groups={"functional","nonFunctionla"})
  public void udateResume() throws InterruptedException, AWTException{
	  TestCase1.updateResume(driver,myWait);
  }
  @Test(priority=2,dependsOnMethods= {"login"},groups={"functional"})
  public void updateSummery() {
	  TestCase2.updateSummery(driver,myWait);
  }
  @Test(priority=3,dependsOnMethods= {"login"},groups={"functional"})
  public void updateSkills(){
	  String validate=TestCase3.updateKeySkills(driver,myWait);
	  Assert.assertEquals("Key Skills have been successfully saved.",validate);
  }
  
  @Test(priority=4,dependsOnMethods= {"login"},groups={"sanity","functional"})
  public void logout(){
	//Select Burger button for logout option
	driver.findElement(By.xpath("//img[@alt=\"naukri user profile img\"]")).click();
	//Select logout
	driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
			
	System.out.println(driver.getTitle());
	
  }
  
  @DataProvider(name="dp")
  Object[][] loginData(){
	  Object data[][]= {{"pavansriramjakkampudi@gmail.com","NAUKRIpowerp@1"},
	  					};
	  return data;
  }
	  
  @AfterClass(alwaysRun = true)
  void quit() {
	  driver.quit();
  }
  
}
