package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Wait_Part_5_ExplicitWait {
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
		explicitWait = new WebDriverWait(driver,15);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Invisible_Before_Element_Hidden() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button")).click();
		
		//Wait for loading icon biến mất (5s)
		//Case 1 : Tìm thằng elemenet trước, sau đó mới truyền vào invisibility of
		//explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div#loading"))));
		
		//Case 2 (100% luôn đúng) : Tìm thằng invisibilityofelement trước, vì thằng tìm element đã có trong invisibilityofelement rồi
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		//After click - take 5s to Helloworld display 
		//Wait until hello world display
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
		System.out.println(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText());
	}
	
	//@Test
	public void TC_02_Visibile_After_Element_Appear() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[text()='Hello World!']")));
		
		//After click - take 5s to Helloworld display 
		//Wait until hello world display
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());	
		System.out.println(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText());
	}
	
	@Test
	public void TC_03_Date_Time_Picker_BT06() {
		//Vẫn đang lỗi chưa chạy ngon được
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		//Chờ đến khi ô ngày 25 có thể được click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']")));
		//Click day = 25
		driver.findElement(By.xpath("//a[text()='25']")).click();
		//Wait for loading calendar
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv")));
		//Wait cho đến khi ngày 25 được chọn
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='25']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='25']")).isDisplayed());
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Panel1")));
		
		//Verify đã chọn thành công
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Friday, February 25, 2022");
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