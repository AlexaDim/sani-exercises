package heroku_selenium_tests;


import static org.testng.Assert.assertEquals;

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


public class DragAndDropJqueryuiIframe {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public DragAndDropJqueryuiIframe(){
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
	    //options.addArguments("--headless");   
	    options.addArguments("--window-size=1920,1080");   
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-gpu");  
	    options.addArguments("--ignore-certificate-errors");  
		driver=new ChromeDriver(options);
		wait = new WebDriverWait(driver, 20);
		
	}
	
  @Test
  public void dragAndDropJqueryui() {
	  driver.get("https://jqueryui.com/droppable/"); 
	  
	  WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='demo-frame']")));
      driver.switchTo().frame(iframe);
	  
	  WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='draggable']")));
	  WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='droppable']")));
	  
	  Actions act=new Actions(driver);	  	
	  act.dragAndDrop(from, to).build().perform();	
	  
	  assertEquals(to.getText(), "Dropped!", "Text in droppable field not as expected.");
	  
	  driver.quit();
	  
  }
  
}
