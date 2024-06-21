package Tests;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase3 {
	
	public static String updateKeySkills(ChromeDriver driver,WebDriverWait myWait) {
		 //key Skills updation
			JavascriptExecutor js=driver;
	        driver.findElement(By.xpath("//div[@class='widgetHead typ-16Bold']/span[2]")).click();
	        List<WebElement> skills=driver.findElements(By.xpath("//div[@class=\"waves-effect chip\"]/span"));
	        
	        for(WebElement skill: skills) {
	        	if(skill.getText().equals("Java")) {
	        		driver.findElement(By.xpath("//div[@title='Java']//a[@class='material-icons close'][normalize-space()='Cross']"));
	        		break;
	        	}
	        }
	        
	        myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"keySkillSugg\"]"))).sendKeys("Java");
	        
	        List<WebElement> skillList=driver.findElements(By.xpath("//li//div[@class=\"Sbtn\"]"));
	        
	        for(WebElement skill:skillList) {
	        	if(skill.getText().equals("Java")) {
	        		myWait.until(ExpectedConditions.visibilityOf(skill)).click();
	        	}
	        }
	        
	        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//button[@id=\"saveKeySkills\"]")));
	        driver.findElement(By.xpath("//button[@id=\"saveKeySkills\"]")).click();
	        String validate=driver.findElement(By.xpath("//div[@id=\"lazyKeySkills\"]//div[@class=\"cnt\"]/p[2]")).getText();
	        System.out.println(validate);
	        
	        Utility.screenShot(driver,"Skills");
			return validate;
	        
	}

}
