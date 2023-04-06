package heroku_selenium_tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public DropDownTest(){
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
	
  //CAN USE SelectDropdown Selenium method
  @Test
  public void dropDownTest() throws InterruptedException {
	  driver.get("https://the-internet.herokuapp.com/dropdown");  
	  int nunberOfOptions = 2;
	  
	  WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dropdown']")));	 
      Thread.sleep(1000);    

      List<WebElement> optiopns = driver.findElements(By.xpath("//option"));
	  
	  for (int j = 0; j < nunberOfOptions; j++) {
		  Thread.sleep(1000);
		  dropdown.click();
		  WebElement optiopn = optiopns.get(j + 1);

		  optiopn.click();
		  assertEquals(optiopn.getAttribute("selected"), "true", "optiopn is not selected.");
	  }	 
	  
	  Thread.sleep(1000);
	  driver.quit();
	  
	  
	  
  }
  
}
