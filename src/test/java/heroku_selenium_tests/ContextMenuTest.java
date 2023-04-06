package heroku_selenium_tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContextMenuTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public ContextMenuTest(){
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
  public void contextMenuTest() throws InterruptedException, AWTException {
	  driver.get("https://the-internet.herokuapp.com/context_menu"); 

	  Actions actions = new Actions(driver);
	  
	  WebElement hotSpot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hot-spot']")));	
	  actions.contextClick(hotSpot).perform();
	 
	  Thread.sleep(1000);
	  driver.switchTo().alert().accept();
	  
	  Robot robot = new Robot();
      robot.keyPress(KeyEvent.VK_DOWN);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_ENTER);
      Thread.sleep(1000);
      
      String url = driver.getCurrentUrl();
      assertEquals(url, "data:,", "url after Back");
      
	  driver.quit();	  
  }
  
}
