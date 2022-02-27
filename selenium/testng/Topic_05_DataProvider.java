package testng;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class Topic_05_DataProvider {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	

	@Test(dataProvider = "login")
	public void TC_02_Login_To_System(String userName, String password) {
		
		//Flow mình cần thao tác qua các bộ dữ liệu
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys(userName);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		
		//Mua hàng
		
		//Thanh toán
		
		//Verify
		
		//....
		
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	

	@Test(dataProvider = "login")
	public void TC_01_Register_To_System(String emailAddress, String password) {
		System.out.println(emailAddress);
		System.out.println(password);
	}
	
	@DataProvider (name = "login")
	public Object[][] loginData(Method method) {
		Object[][] obj = null;
		if (method.getName().contains("Login")) {
			obj = new Object[][] { 
				new Object[] { "selenium_11_01@gmail.com", "111111" }, 
				new Object[] { "selenium_11_02@gmail.com", "111111" }, 
				new Object[] { "selenium_11_03@gmail.com", "111111" }, 
				
			};
		} else if(method.getName().contains("Register")){
			obj = new Object[][] { 
				new Object[] { "selenium"+getRandomNumber()+"@gmail.com", "111111" }, 
				new Object[] { "selenium"+getRandomNumber()+"@gmail.com", "111111" }, 
				new Object[] { "selenium"+getRandomNumber()+"@gmail.com", "111111" }, 

				};
		}
		return obj;
	}

	//@DataProvider (name = "register")
	public Object[][] registerData() {

		return new Object[][] { 
			new Object[] { "selenium"+getRandomNumber()+"@gmail.com", "111111" }, 
			new Object[] { "selenium"+getRandomNumber()+"@gmail.com", "111111" }, 
			new Object[] { "selenium"+getRandomNumber()+"@gmail.com", "111111" }, 

			};
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
}
