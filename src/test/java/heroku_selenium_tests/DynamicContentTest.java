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

public class DynamicContentTest {
	WebDriver driver;
	WebDriverWait wait;
	String pictureOnePath, pictureTwoPath, pictureThreePath, textOnePath, textTwoPath, textThreePath;
	
	@SuppressWarnings("deprecation")
	public DynamicContentTest(){
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
		
		pictureOnePath = "//*[@id=\"content\"]/div[1]/div[1]/img";
		pictureTwoPath = "//*[@id=\"content\"]/div[2]/div[1]/img";
		pictureThreePath = "//*[@id=\"content\"]/div[3]/div[1]/img";
		
		textOnePath = "//*[@id=\"content\"]/div[1]/div[2]"; 
		textTwoPath = "//*[@id=\"content\"]/div[2]/div[2]"; 
		textThreePath = "//*[@id=\"content\"]/div[3]/div[2]";
	}
	
  @Test
  public void dynamicContentTest() throws InterruptedException {
	  driver.get("https://the-internet.herokuapp.com/dynamic_content");  
	  
	  String pictureOneLandOnPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureOnePath))).getAttribute("src");  
	  String pictureTwoLandOnPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureTwoPath))).getAttribute("src");  
	  String pictureThreeLandOnPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureThreePath))).getAttribute("src");   
	  
	  String textOneLandOnPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textOnePath))).getText(); 
	  String textTwoLandOnPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textTwoPath))).getText();  
	  String textThreeLandOnPage  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textThreePath))).getText();
	  
	  //---initial click---  
	  WebElement clickHere = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/p[2]/a")));
	  clickHere.click();
	  
	  String pictureOneFirstClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureOnePath))).getAttribute("src"); 
	  String pictureTwoFirstClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureTwoPath))).getAttribute("src"); 
	  String pictureThreeFirstClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureThreePath))).getAttribute("src");
	  
	  String textOneFirstClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textOnePath))).getText();
	  String textTwoFirstClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textTwoPath))).getText();
	  String textThreeFirstClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textThreePath))).getText();
	  
	  assertFalse(pictureOneLandOnPage.equals(pictureOneFirstClick) && pictureTwoLandOnPage.equals(pictureTwoFirstClick) && pictureThreeLandOnPage.equals(pictureThreeFirstClick), "Images should not be eqial.");
	  assertFalse(textOneLandOnPage.equals(textOneFirstClick) && textTwoLandOnPage.equals(textTwoFirstClick) && textThreeLandOnPage.equals(textThreeFirstClick), "Texts should not be eqial.");
	  
	  //---second click---
	  clickHere = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/p[2]/a")));
	  clickHere.click();
	  String pictureOneSecondClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureOnePath))).getAttribute("src"); 
	  String pictureTwoSecondClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureTwoPath))).getAttribute("src"); 
	  String pictureThreeSecondClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureThreePath))).getAttribute("src"); 
	  
	  String textOneSecondClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textOnePath))).getText();
	  String textTwoSecondClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textTwoPath))).getText();
	  String textThreeSecondClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textThreePath))).getText();
	  
	  assertTrue(pictureOneSecondClick.equals(pictureOneFirstClick) && pictureTwoSecondClick.equals(pictureTwoFirstClick), "1st and 2nd Images should be eqial.");
	  assertTrue(textOneSecondClick.equals(textOneFirstClick) && textTwoSecondClick.equals(textTwoFirstClick), "1st and 2nd Texts should be eqial.");
	  assertFalse(pictureThreeSecondClick.equals(pictureThreeFirstClick) && textThreeSecondClick.equals(textThreeFirstClick), "3th Images+Text should not be eqial.");
	  
	  //---third click---
	  clickHere = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/p[2]/a")));
	  clickHere.click();
	  String pictureOneThirdClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureOnePath))).getAttribute("src"); 
	  String pictureTwoThirdClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureTwoPath))).getAttribute("src"); 
	  String pictureThreeThirdClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pictureThreePath))).getAttribute("src"); 
	  
	  String textOneThirdClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textOnePath))).getText();
	  String textTwoThirdClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textTwoPath))).getText();
	  String textThreeThirdClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textThreePath))).getText();
	  
	  assertTrue(pictureOneSecondClick.equals(pictureOneThirdClick) && pictureTwoSecondClick.equals(pictureTwoThirdClick), "1st and 2nd Images should be eqial.");
	  assertTrue(textOneSecondClick.equals(textOneThirdClick) && textTwoSecondClick.equals(textTwoThirdClick), "1st and 2nd Texts should be eqial.");
	  assertFalse(pictureThreeSecondClick.equals(pictureThreeThirdClick) && textThreeSecondClick.equals(textThreeThirdClick), "3th Images+Text should not be eqial.");
	  
	  driver.quit();
	  
	  
	  
  }
  
}
