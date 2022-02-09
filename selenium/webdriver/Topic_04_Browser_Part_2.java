package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_2 {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_Url() {
		
		//Mở ra 1 Url
		driver.get("http://live.techpanda.org/"); 
		
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Click vào Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify Url
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		
	}

	@Test
	public void TC_02_Title() {
		//Mở ra 1 Url
		driver.get("http://live.techpanda.org/"); 
				
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
						
		//Verify Title
		String titleLogin = driver.getTitle();
		Assert.assertEquals(titleLogin,"Customer Login");
		
		//Click vào Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify Title
		String titleCreateAcount = driver.getTitle();
		Assert.assertEquals(titleCreateAcount, "Create New Customer Account");
		
	}

	@Test
	public void TC_03_Navigation_Back_Forward() {
		
		//Mở ra 1 Url
		driver.get("http://live.techpanda.org/"); 
				
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
		
		//Click vào Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify Url của register Page
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		
		//back về trang trước
		driver.navigate().back();
		
		
		//Verify Url của trang login
		String loginPageUrl = driver.getCurrentUrl();		
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");		
		
		//Forward tới trang register
		driver.navigate().forward();

		//verify title của trang login
		String titleCreateAcount = driver.getTitle();
		Assert.assertEquals(titleCreateAcount, "Create New Customer Account");		
	}

	private void sleepInSecond(int i) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void TC_04_Page_Source_Code() {
		//Mở ra 1 Url
		driver.get("http://live.techpanda.org/"); 
				
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
		
		//Verify Login page chứa text Login or Create an Account
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));	
		
		//Click vào Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify register Page chứa text Create an Account
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));	
				
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecon (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
}