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
import org.openqa.selenium.JavascriptExecutor;

public class DragAndDropHerokuapp {
	WebDriver driver;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public DragAndDropHerokuapp(){
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
  public void dragAndDropHerokuapp() {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  
	  driver.get("https://the-internet.herokuapp.com/drag_and_drop"); 
	 
	  WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='column-a']")));
	  WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='column-b']")));
	  
	  String java_script =
			  "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe" +
			                  "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun" +
			                  "ction(format,data){this.items[format]=data;this.types.append(for" +
			                  "mat);},getData:function(format){return this.items[format];},clea" +
			                  "rData:function(format){}};var emit=function(event,target){var ev" +
			                  "t=document.createEvent('Event');evt.initEvent(event,true,false);" +
			                  "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('" +
			                  "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" +
			                  "'drop',tgt);emit('dragend',src);";

	  js.executeScript(java_script, from, to);
	  
	  assertEquals(to.getText(), "A", "Text in droppable field not as expected.");
	  
	  driver.quit();
	  
//	  Actions act=new Actions(driver);	  	
//	  act.dragAndDrop(from, to).build().perform();	
	  
//	  Actions actions = new Actions(driver);
//	  org.openqa.selenium.interactions.Action dragAndDrop = actions.clickAndHold(from)
//			  .moveToElement(to)
//			  .release(to)
//			  .build();
//	  dragAndDrop.perform();
	  
	  
	  
  }
  
}
