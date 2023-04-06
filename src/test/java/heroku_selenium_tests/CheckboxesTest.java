package heroku_selenium_tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckboxesTest {
	WebDriver driver;
	
	public CheckboxesTest(){
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
	    //options.addArguments("--headless");   
	    options.addArguments("--window-size=1920,1080");   
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-gpu");  
	    options.addArguments("--ignore-certificate-errors");  
	    options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		
	}
	
  @Test
  public void checkboxesTest() throws InterruptedException {
      driver.get("https://the-internet.herokuapp.com/checkboxes"); 
      
      int nunberOfCheckboxes = 2;
      Thread.sleep(1000);

      List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));
	  
	  for (int j = 0; j < nunberOfCheckboxes; j++) {
		  Thread.sleep(1000);
		  WebElement checkbox = checkboxes.get(j);
		  
		  if (checkbox.isSelected() == false)
			  checkbox.click();
		  
		  assertTrue(checkbox.isSelected()== true, "Checkbox is not selected.");
	  }	 
	  
	  Thread.sleep(1000);
	  driver.quit();	  	 
  }
}
