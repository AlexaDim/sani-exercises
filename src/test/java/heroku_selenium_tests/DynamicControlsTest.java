package heroku_selenium_tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicControlsTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public DynamicControlsTest(){
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
  public void dynamicControlsTest() throws InterruptedException {
	  driver.get("https://the-internet.herokuapp.com/dynamic_controls");  	  
  
	  //originally checkbox is available
	  assertTrue(isCkeckboxPresent(), "Chechbox is not present.");
	  
	  //Remove checkbox
	  WebElement removeAddCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkbox-example\"]/button")));
	  removeAddCheckbox.click();
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]")));  
	  assertFalse(isCkeckboxPresent(), "Chechbox is present.");
	  
      //Add back ckeckbox
	  removeAddCheckbox.click(); 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]")));  
	  assertTrue(isCkeckboxPresent(), "Chechbox is not present.");
	  
	  //--------------------------------------------------------------------------------
	  
	  //originally input is disabled
	  WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-example\"]/input")));
	  assertFalse(input.isEnabled(), "Chechbox is enabled.");

	  //enable input 
	  WebElement enableDisableInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-example\"]/button")));
	  enableDisableInput.click(); 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]")));
	  assertTrue(input.isEnabled(), "Chechbox is disabled.");
	  	  
	  //disabled input
	  enableDisableInput.click(); 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]")));
	  assertFalse(input.isEnabled(), "Chechbox is enabled.");
	  
	  driver.quit();
	  
	  
	  
  }
  
  Boolean isCkeckboxPresent() {
	  try {
		  driver.findElement(By.xpath("//*[@id=\"checkbox\"]"));  //   //*[@id="checkbox"]     //*[@id="checkbox"]/input
		  System.out.println("Element Present");
		  return true;
	      
	    } catch (Exception e) { 
	    	System.out.println("Element Not Present");
	    	return false;
	    }
  }
  
  
}
