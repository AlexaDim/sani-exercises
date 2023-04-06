package heroku_selenium_tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public WindowsTest(){
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
	    //options.addArguments("--headless");   
	    options.addArguments("--window-size=1920,1080");   
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-gpu");  
	    options.addArguments("--ignore-certificate-errors");  
	    options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		wait = new WebDriverWait(driver, 20);		
	}
	
  @Test
  public void windowsTest() throws InterruptedException {
	  driver.get("https://the-internet.herokuapp.com/windows");  

	  String originalWindow = driver.getWindowHandle();
	  
	  WebElement clickHere = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/a")));	
	  clickHere.click();

	  for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
 
	  WebElement there = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/h3")));	
	  assertEquals(there.getText(), "New Window", "New window text not as exepcted"); 
	   
	  driver.switchTo().window(originalWindow); 
	  WebElement here = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/h3")));	
	  assertEquals(here.getText(), "Opening a new window", "Original window text not as exepcted");

	  driver.quit(); 
	  
  }
  
}
