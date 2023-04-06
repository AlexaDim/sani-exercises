package heroku_selenium_tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddRemoveElementTest {
	WebDriver driver;
	WebDriverWait wait;
	Boolean buttonsAreRemoved;
	
	@SuppressWarnings("deprecation")
	public AddRemoveElementTest(){
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
		buttonsAreRemoved = false;
		
	}
	
  @Test
  public void addRemoveElementTest() throws InterruptedException {
      driver.get("https://the-internet.herokuapp.com/add_remove_elements/"); 
      
      int unberOfElement = 3;
      Thread.sleep(1000);
	  WebElement addElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@onclick='addElement()']")));
	  for (int i = 1; i <= unberOfElement; i++) {
		  Thread.sleep(1000);
		  addElement.click();
	  }
	  
	  Thread.sleep(1000);
	  
	  WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='elements']")));	  
	  List<WebElement> deleteElements = elements.findElements(By.xpath("./child::*"));
	  
	  for (int j = 0; j < unberOfElement; j++) {
		  Thread.sleep(1000);
		  deleteElements.get(j).click();
	  }	  
  
	  buttonsAreRemoved = isElementPresent();	  
	  assertTrue(buttonsAreRemoved, "Button is not removed.");
	  
	  driver.quit();	  	 
  }
  
  Boolean isElementPresent() {
	  try {
		  driver.findElement(By.xpath("//*[@onclick='deleteElement()']"));
		  System.out.println("Element Present");
		  return false;
	      
	    } catch (Exception e) { 
	    	System.out.println("Element Not Present");
	    	return true;
	    }
  }
  
}
