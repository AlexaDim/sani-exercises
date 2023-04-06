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

public class NestedIframeTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public NestedIframeTest(){
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
  public void nestedIframeTest() {
      driver.get("https://the-internet.herokuapp.com/nested_frames"); 
	  
      WebElement topFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='frame-top']")));
      driver.switchTo().frame(topFrame);
	  
      //LEFT------------------
      WebElement leftFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='frame-left']")));
      driver.switchTo().frame(leftFrame);
     
      WebElement left = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
      assertEquals(left.getText(), "LEFT", "Text in LEFT not as expected.");
	  
      driver.switchTo().defaultContent(); //Return to the top level 
      driver.switchTo().frame(topFrame); //Go to top frame again
      
      //MIDDLE-------------
      WebElement middleFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='frame-middle']")));
      driver.switchTo().frame(middleFrame);
      WebElement middle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
      assertEquals(middle.getText(), "MIDDLE", "Text in MIDDLE not as expected.");
      
      driver.switchTo().defaultContent(); //
      driver.switchTo().frame(topFrame);  //or driver.switchTo().parentFrame()
      
      //RIGHT--------------
      WebElement rightFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='frame-right']")));
      driver.switchTo().frame(rightFrame);
      WebElement right = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
      assertEquals(right.getText(), "RIGHT", "Text in RIGHT not as expected.");
      
      driver.switchTo().defaultContent();
	  
	  //BOTTOM---------------
      WebElement bottomFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='frame-bottom']")));
      driver.switchTo().frame(bottomFrame);
      WebElement bottom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
      assertEquals(bottom.getText(), "BOTTOM", "Text in BOTTOM not as expected.");
	    
	  driver.quit();
  }
}
