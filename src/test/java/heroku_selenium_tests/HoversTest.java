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

public class HoversTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public HoversTest(){
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
  public void hoversTest() throws InterruptedException {
	  driver.get("https://the-internet.herokuapp.com/hovers");  
	  
	  Actions action = new Actions(driver);
	  
	  WebElement userOne = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/img")));	
	  action.moveToElement(userOne).perform();
	  WebElement userOneName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5")));
	  assertEquals(userOneName.getText(), "name: user1", "Name 1 in not as exepcted");
	  
	  WebElement userTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/img")));	
	  action.moveToElement(userTwo).perform();
	  WebElement userTwoName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/h5")));
	  assertEquals(userTwoName.getText(), "name: user2", "Name 2 in not as exepcted");
	  
	  WebElement userThree = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[3]/img")));	
	  action.moveToElement(userThree).perform();
	  WebElement userThreeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[3]/div/h5")));	
	  assertEquals(userThreeName.getText(), "name: user3", "Name 3 in not as exepcted");
	  
	  driver.quit();  
	  
  }
  
}
