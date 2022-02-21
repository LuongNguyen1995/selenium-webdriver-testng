package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Wait_Part_3_ImplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		} else  {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Timeout_Less_Than_Find_Element_Display() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button")).click();
		
		//After click - take 5s to Helloworld display 
		//Wait until hello world display
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
		System.out.println(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText());
		
		//isDisplay ko liên quan đến implicitWait. Có hiển thị trên UI : return true, k hiển thị thì return false
	}
	
	@Test
	public void TC_02_Timeout_Less_Equal_Element_Display() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button")).click();
		
		//After click - take 5s to Helloworld display 
		//Wait until hello world display
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
		System.out.println(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText());

	}
	
	@Test
	public void TC_03_Timeout_Greate_Than_Element_Display() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button")).click();
		
		//After click - take 5s to Helloworld display 
		//Wait until hello world display
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
		System.out.println(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText());

	}

	@Test
	public void TC_04() {
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}