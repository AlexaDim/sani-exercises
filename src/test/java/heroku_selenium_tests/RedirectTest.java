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

public class RedirectTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public RedirectTest(){
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
  public void redirectTest() {
      driver.get("https://the-internet.herokuapp.com/redirector"); 
	  
	  WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='redirect']")));
	  link.click();
	  
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='example']/h3[contains(.,'Status Codes')]")));
	  String url = driver.getCurrentUrl();
	  assertEquals(url, "https://the-internet.herokuapp.com/status_codes", "url of redirected page not as expected.");
	  
	  driver.quit();
  }
}
