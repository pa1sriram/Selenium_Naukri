package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase2 {

	public static void updateSummery(ChromeDriver driver, WebDriverWait myWait) {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//div[@class='widgetHead']//span[@class='edit icon'][text()='editOneTheme']")).click();
		
		Utility.screenShot(driver,"exception");
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
        
		Utility.screenShot(driver,"Summery");
		
	}
}
