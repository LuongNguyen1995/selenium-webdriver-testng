package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
 
	@Test
	public void TC_01() {
		
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//kiểm tra các phần tử sau hiển thị trên trang
		//email
		WebElement emailID = driver.findElement(By.xpath("//input[@id='mail']"));
		Assert.assertTrue(emailID.isDisplayed());
		
		if(emailID.isDisplayed()) {
			System.out.println("Element is Display");
		}
		}

	
	public void TC_02() {
		
		driver.get("https://www.facebook.com/");
		WebElement loginButton =  driver.findElement(By.name("login"));
		System.out.println(loginButton.getCssValue("font-size"));
		System.out.println(loginButton.getCssValue("background-color"));
		System.out.println(loginButton.getCssValue("line-height"));
	

	}

	
	public void TC_03() {
		driver.get("https://www.facebook.com/");
		WebElement loginButton =  driver.findElement(By.name("login"));
		String loginButtonTagname = loginButton.getTagName();
		
		//Other step
		//Đầu ra của step này là đầu vào của step kia
		loginButton = driver.findElement(By.xpath("//" + loginButtonTagname +"[@id=loginbutton]"));
		
	}
	
	public void TC_04() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.id("email")).sendKeys("auto@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("email")).submit();
		
	
	
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	
}